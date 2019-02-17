package com.caixa.notaderelease.api.resource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caixa.notaderelease.api.dto.Summary;
import com.caixa.notaderelease.api.enums.ProfileEnum;
import com.caixa.notaderelease.api.enums.StatusEnum;
import com.caixa.notaderelease.api.model.ChangeStatus;
import com.caixa.notaderelease.api.model.Ticket;
import com.caixa.notaderelease.api.model.User;
import com.caixa.notaderelease.api.response.Response;
import com.caixa.notaderelease.api.security.jwt.JwtTokenUtil;
import com.caixa.notaderelease.api.service.TicketService;
import com.caixa.notaderelease.api.service.UserService;



@RestController
@RequestMapping("/api/ticket")
@CrossOrigin(origins = "*")
public class TicketResource {
	
	
	@Autowired
	private TicketService ticketService;
	
    @Autowired
    protected JwtTokenUtil jwtTokenUtil;
    
    @Autowired
	private UserService userService;
    
    /////////////PERFEITO
    @PostMapping()
	@PreAuthorize("hasAnyRole('CUSTOMER')")
	public ResponseEntity<Response<Ticket>> create(HttpServletRequest request, @RequestBody Ticket ticket,
			BindingResult result) {
		Response<Ticket> response = new Response<Ticket>();
		try {
			validateCreateTicket(ticket, result);
			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			ticket.setStatus(StatusEnum.getStatus("New"));
			ticket.setUser(userFromRequest(request));
			ticket.setDataAbertura(LocalDate.now() );
			Ticket ticketPersisted = (Ticket) ticketService.createOrUpdate(ticket);
			response.setData(ticketPersisted);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		
		return ResponseEntity.ok(response);
		}


	private User userFromRequest(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
        String matricula = jwtTokenUtil.getUsernameFromToken(token);
        return userService.findByMatricula(matricula);
	}


	private void validateCreateTicket(Ticket ticket, BindingResult result) {
		if (ticket.getNumeroNotaRelease() == null) {
			result.addError(new ObjectError("Numero da Nota de Release", "Informar numero da Nota de Release"));
			return;
		}//adicionar outras
		
	}

	@PutMapping()
	@PreAuthorize("hasAnyRole('CUSTOMER')")
	public ResponseEntity<Response<Ticket>> update(HttpServletRequest request, @RequestBody Ticket ticket,
			BindingResult result) {
		Response<Ticket> response = new Response<Ticket>();
		try {
			validateUpdateTicket(ticket, result);
			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			
			Ticket ticketCurrent = ticketService.findByCodigo(ticket.getCodigo());
			ticket.setStatus(ticketCurrent.getStatus());
			ticket.setUser(ticketCurrent.getUser());
			ticket.setDataAbertura(ticketCurrent.getDataAbertura());
			//Nao pode alterar o numero da nota de release, somente a descricao
			ticket.setNumeroNotaRelease(ticketCurrent.getNumeroNotaRelease());
			//ticket.setDescricao(ticketCurrent.getDescricao());
			if(ticketCurrent.getAssignedUser() != null) {
				ticket.setAssignedUser(ticketCurrent.getAssignedUser());
			}
			
			Ticket ticketPersisted = (Ticket) ticketService.createOrUpdate(ticket);
			response.setData(ticketPersisted);
			
			
			
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}
	

	
	
	
	private void validateUpdateTicket(Ticket ticket, BindingResult result) {
	if (ticket.getNumeroNotaRelease() == null) {
		result.addError(new ObjectError("Numero da Nota de Release", "Informar numero da Nota de Release"));
		return;
	}
	if (ticket.getCodigo() == null) {
		result.addError(new ObjectError("Numero do Codigo(PK)", "Informar numero do codigo"));
		return;
	}
	}


	/////////////PERFEITO
	@GetMapping(value = "/{codigo}")
	@PreAuthorize("hasAnyRole('CUSTOMER','TECHNICIAN')")
	public ResponseEntity<Response<Ticket>> findByCodigo(@PathVariable("codigo") Long codigo) {
		Response<Ticket> response = new Response<Ticket>();
		Ticket ticket = ticketService.findByCodigo(codigo);
		if (ticket == null) {
			response.getErrors().add("Register not found Codigo:" + codigo);
			return ResponseEntity.badRequest().body(response);
		}
		List<ChangeStatus> changes = new ArrayList<ChangeStatus>();
		Iterable<ChangeStatus> changesCurrent =  ticketService.listChangeStatus(codigo);
		for (Iterator<ChangeStatus> iterator = changesCurrent.iterator(); iterator.hasNext();) {
		ChangeStatus changeStatus = iterator.next();
		changeStatus.setTicket(null);
		changes.add(changeStatus);
		}	
		ticket.setChanges(changes);
		response.setData(ticket);
		return ResponseEntity.ok(response);
	}
	
	/////////////PERFEITO
	@DeleteMapping(value = "/{codigo}")
	@PreAuthorize("hasAnyRole('CUSTOMER')")
	public ResponseEntity<Response<String>> delete(@PathVariable("codigo") Long codigo) {
		Response<String> response = new Response<String>();
		Ticket ticket = ticketService.findByCodigo(codigo);
		if (ticket == null) {
			response.getErrors().add("Código não registrado:" + codigo);
			return ResponseEntity.badRequest().body(response);
		}
		ticketService.delete(codigo);
		return ResponseEntity.ok(new Response<String>());
	}
	
	/////////////PERFEITO nao tras as mudancas
	@GetMapping(value = "/{page}/{count}")
	@PreAuthorize("hasAnyRole('CUSTOMER','TECHNICIAN')")
    public  ResponseEntity<Response<Page<Ticket>>> findAll(HttpServletRequest request, @PathVariable int page, @PathVariable int count) {
		
		Response<Page<Ticket>> response = new Response<Page<Ticket>>();
		Page<Ticket> tickets = null;
		User userRequest = userFromRequest(request);
		if(userRequest.getProfile().equals(ProfileEnum.ROLE_TECHNICIAN)) {
			tickets = ticketService.listTicket(page, count);
		} else if(userRequest.getProfile().equals(ProfileEnum.ROLE_CUSTOMER)) {
			tickets = ticketService.findByCurrentUser(page, count, userRequest.getCodigo());
		}
		response.setData(tickets);
		return ResponseEntity.ok(response);
    }
	
	//VERIFICAR
	@GetMapping(value = "{page}/{count}/{numeronotarelease}/{status}/{assigned}")
	@PreAuthorize("hasAnyRole('CUSTOMER','TECHNICIAN')")
    public  ResponseEntity<Response<Page<Ticket>>> findByParams(HttpServletRequest request, 
    		 							@PathVariable("page") int page, 
    		 							@PathVariable("count") int count,
    		 							@PathVariable("numeroNotaRelease") String numeroNotaRelease,
    		 							@PathVariable("status") String status,
    		 							@PathVariable("assigned") boolean assigned) {
		
		numeroNotaRelease = numeroNotaRelease.equals("uninformed") ? "" : numeroNotaRelease;
		status = status.equals("uninformed") ? "" : status;
		
		Response<Page<Ticket>> response = new Response<Page<Ticket>>();
		Page<Ticket> tickets = null;
		if(numeroNotaRelease != null) {
			tickets = ticketService.findByNumber(page, count, numeroNotaRelease);
			} else {
				User userRequest = userFromRequest(request);
				if(userRequest.getProfile().equals(ProfileEnum.ROLE_TECHNICIAN)) {
					if(assigned) {
						tickets = ticketService.findByParametersAndAssignedUser(page, count, numeroNotaRelease, status, userRequest.getCodigo());
					} else {
						tickets = ticketService.findByParameters(page, count, numeroNotaRelease, status);
					}
				} else if(userRequest.getProfile().equals(ProfileEnum.ROLE_CUSTOMER)) {
					tickets = ticketService.findByParametersAndCurrentUser(page, count, numeroNotaRelease, status, userRequest.getCodigo());
				}
			}
		response.setData(tickets);
		return ResponseEntity.ok(response);
    }
	
	
	/////////////PERFEITO
	@PutMapping(value = "/{codigo}/{status}")
	@PreAuthorize("hasAnyRole('CUSTOMER','TECHNICIAN')")
	public ResponseEntity<Response<Ticket>> changeStatus(HttpServletRequest request,
													@PathVariable("codigo") Long codigo, 
													@PathVariable("status") String status,  
													@RequestBody Ticket ticket,
													BindingResult result) {
		
		Response<Ticket> response = new Response<Ticket>();
		
		
		try {
			validateChangeStatus(codigo, status, result);
			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			Ticket ticketCurrent = ticketService.findByCodigo(codigo);
			ticketCurrent.setStatus(StatusEnum.getStatus(status));
			if(status.equals("Assigned")) {
				ticketCurrent.setAssignedUser(userFromRequest(request));
			}
			Ticket ticketPersisted = (Ticket) ticketService.createOrUpdate(ticketCurrent);
			ChangeStatus changeStatus = new ChangeStatus();
			changeStatus.setUserChange(userFromRequest(request).getNome());			
			changeStatus.setDateChangeStatus(LocalDate.now());
			changeStatus.setStatus((StatusEnum.getStatus(status)).toString());
			changeStatus.setTicket(ticketCurrent);
			ticketService.createChangeStatus(changeStatus);
			response.setData(ticketPersisted);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	} 
	
	private void validateChangeStatus(Long codigo,String status, BindingResult result) {
		if (codigo == null || codigo.equals("")) {
			result.addError(new ObjectError("codigo", "codigo não informado"));
			return;
		}
		if (status == null || status.equals("")) {
			result.addError(new ObjectError("Ticket", "Status não informado"));
			return;
		}
	}
	
	
	@GetMapping(value = "/summary")
	public ResponseEntity<Response<Summary>> findChart() {
		Response<Summary> response = new Response<Summary>();
		Summary chart = new Summary();
		int amountNew = 0;
		int amountResolved = 0;
		int amountApproved = 0;
		int amountDisapproved = 0;
		int amountAssigned = 0;
		int amountClosed = 0;
		Iterable<Ticket> tickets = ticketService.findAll();
		if (tickets != null) {
			for (Iterator<Ticket> iterator = tickets.iterator(); iterator.hasNext();) {
				Ticket ticket = iterator.next();
				if(ticket.getStatus().equals(StatusEnum.New)){
					amountNew ++;
				}
				if(ticket.getStatus().equals(StatusEnum.Resolved)){
					amountResolved ++;
				}
				if(ticket.getStatus().equals(StatusEnum.Approved)){
					amountApproved ++;
				}
				if(ticket.getStatus().equals(StatusEnum.Disapproved)){
					amountDisapproved ++;
				}
				if(ticket.getStatus().equals(StatusEnum.Assigned)){
					amountAssigned ++;
				}
				if(ticket.getStatus().equals(StatusEnum.Closed)){
					amountClosed ++;
				}
			}	
		}
		chart.setAmountNew(amountNew);
		chart.setAmountResolved(amountResolved);
		chart.setAmountApproved(amountApproved);
		chart.setAmountDisapproved(amountDisapproved);
		chart.setAmountAssigned(amountAssigned);
		chart.setAmountClosed(amountClosed);
		response.setData(chart);
		return ResponseEntity.ok(response);
	}
	

}
