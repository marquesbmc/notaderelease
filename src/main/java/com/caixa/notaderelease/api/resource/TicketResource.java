package com.caixa.notaderelease.api.resource;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.caixa.notaderelease.api.enums.ProfileEnum;
import com.caixa.notaderelease.api.model.mysql.ChangeStatus;
import com.caixa.notaderelease.api.model.mysql.CoordSystemNotes;
import com.caixa.notaderelease.api.model.mysql.ReleaseNotes;
import com.caixa.notaderelease.api.model.mysql.Ticket;
import com.caixa.notaderelease.api.model.mysql.User;
import com.caixa.notaderelease.api.response.Response;
import com.caixa.notaderelease.api.security.jwt.JwtTokenUtil;
import com.caixa.notaderelease.api.service.CoordSystemNotesService;
import com.caixa.notaderelease.api.service.ReleaseNotesService;
import com.caixa.notaderelease.api.service.TicketService;
import com.caixa.notaderelease.api.service.UserService;

@RestController
@RequestMapping("/api/ticket")
@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "http://pefug.arquitetura.caixa:4200")
public class TicketResource {

	@Autowired
	private TicketService ticketService;

	@Autowired
	private ReleaseNotesService releaseNotesService;

	
	private ReleaseNotes releaseNotes;
	
	@Autowired
	private CoordSystemNotesService systemNotesService;

	@Autowired
	protected JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserService userService;

	@Autowired
	private HttpServletRequest request;

	@DeleteMapping(value = "/{codigo}")
	@PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
	public ResponseEntity<Response<String>> delete(@PathVariable("codigo") Long codigo) {
		Response<String> response = new Response<String>();
		Ticket ticket = ticketService.findByCodigo(codigo);
		if (ticket.getCodigo() == null) {
			response.getErrors().add("Código não registrado:" + codigo);
			return ResponseEntity.badRequest().body(response);
		}
		ticketService.delete(codigo);
		return ResponseEntity.ok(new Response<String>());
	}

	@PostMapping()
	@PreAuthorize("hasAnyRole('CUSTOMER')")
	public ResponseEntity<Response<Ticket>> create(@RequestBody Ticket ticket, BindingResult result) {
		Response<Ticket> response = new Response<Ticket>();
		try {

			ticket.setStatus("Novo");
			ticket.setUser(userFromRequest(request));

			//LocalDateTime now = LocalDateTime.now();
			//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			//ticket.setDataAbertura(now);
			ticket.setDataAbertura(LocalDate.now());
			
			User userRequest = userFromRequest(request);
			ticket.setCoordenacao(userRequest.getCoordenacao());

			releaseNotes = releaseNotesService.findByCodigo(ticket.numeroNotaRelease.getCodigo());
			ticket.setNumeroNotaRelease(releaseNotes);

			validateCreateTicket(ticket, result);
			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}

			Ticket ticketPersisted = (Ticket) ticketService.createOrUpdate(ticket);
			response.setData(ticketPersisted);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}

		return ResponseEntity.ok(response);
	}

	private void validateCreateTicket(Ticket ticket, BindingResult result) {
		if (ticket.getNumeroNotaRelease() == null) {
			result.addError(new ObjectError("Numero da Nota de Release", "Informar numero da Nota de Release"));
			return;
		} // adicionar outras

	}

	private User userFromRequest(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		String matricula = jwtTokenUtil.getUsernameFromToken(token);
		return userService.findByMatricula(matricula);
	}

	@PutMapping()
	@PreAuthorize("hasAnyRole('CUSTOMER','TECHNICIAN')")
	public ResponseEntity<Response<Ticket>> update(@RequestBody Ticket ticket, BindingResult result) {
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
			// Nao pode alterar o numero da nota de release, somente a descricao
			ticket.setNumeroNotaRelease(ticketCurrent.getNumeroNotaRelease());
			// ticket.setDescricao(ticketCurrent.getDescricao());
			if (ticketCurrent.getAssignedUser() != null) {
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

	@PostMapping(value = "/{codigonr}")
	@PreAuthorize("hasAnyRole('CUSTOMER')")
	public ResponseEntity<Response<Ticket>> create(@PathVariable("codigonr") String codigonr,
			@RequestBody Ticket ticket, BindingResult result) {
		Response<Ticket> response = new Response<Ticket>();
		try {

			ticket.setStatus("Novo");
			ticket.setUser(userFromRequest(request));

			//LocalDateTime now = LocalDateTime.now();
			//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			//ticket.setDataAbertura(now);
			ticket.setDataAbertura(LocalDate.now());

			User userRequest = userFromRequest(request);
			ticket.setCoordenacao(userRequest.getCoordenacao());

			releaseNotes = releaseNotesService.findByCodigo(ticket.numeroNotaRelease.getCodigo());
			ticket.setNumeroNotaRelease(releaseNotes);

			validateCreateTicket(ticket, result);
			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}

			Ticket ticketPersisted = (Ticket) ticketService.createOrUpdate(ticket);
			response.setData(ticketPersisted);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}

		return ResponseEntity.ok(response);
	}

	@PutMapping(value = "/{codigo}/{status}")
	@PreAuthorize("hasAnyRole('CUSTOMER','TECHNICIAN')")
	public ResponseEntity<Response<Ticket>> changeStatus(@PathVariable("codigo") Long codigo,
			@PathVariable("status") String status, @RequestBody Ticket ticket, BindingResult result) {

		Response<Ticket> response = new Response<Ticket>();

		try {
			validateChangeStatus(codigo, status, result);
			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			
			Ticket ticketCurrent = ticketService.findByCodigo(codigo);
			ticketCurrent.setStatus(status);
			if (status.equals("Atribuído")) {
				ticketCurrent.setAssignedUser(userFromRequest(request));
			}

			Ticket ticketPersisted = (Ticket) ticketService.createOrUpdate(ticketCurrent);
			ChangeStatus changeStatus = new ChangeStatus();

			changeStatus.setUserChange(userFromRequest(request).getNome());

			LocalDateTime now = LocalDateTime.now();
			changeStatus.setDateChangeStatus(now);
			changeStatus.setStatus(status);
			changeStatus.setTicket(ticketCurrent);

			if (status.equals("Atribuído")) {
				changeStatus.setInfo(ticket.getInfo());
			} else {
				
				changeStatus.setInfo(ticket.getInfo());
			}

			if (status.equals("Aprovado") || status.equals("Reprovado")) {
				ticketPersisted.setDataInstalacao(now);
			}
			
			if (status.equals("Comentar")) {	
				ticketPersisted.setStatus("Novo");
				ticketCurrent.setAssignedUser(null);
			}

			// ticketPersisted.setDescricao(ticket.getDescricao());
			ticketService.createChangeStatus(changeStatus);
			
			
			List<ChangeStatus> changes = new ArrayList<ChangeStatus>();
			Iterable<ChangeStatus> changesCurrent = ticketService.listChangeStatus(ticketPersisted.getCodigo());
			for (Iterator<ChangeStatus> iterator = changesCurrent.iterator(); iterator.hasNext();) {
				ChangeStatus changeStatuss = iterator.next();
				changeStatuss.setTicket(null);
				changes.add(changeStatuss);
			}
			ticketPersisted.setChanges(changes);
			
			
			
			
				
			//Ticket ticketCurrent = ticketService.findByCodigo(codigo);
			ticketPersisted = ticketService.findByCodigo(ticketPersisted.getCodigo());
			response.setData(ticketPersisted);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}

	/*
	 * @PutMapping(value = "/{codigo}/{status}")
	 * 
	 * @PreAuthorize("hasAnyRole('CUSTOMER','TECHNICIAN')") public
	 * ResponseEntity<Response<Ticket>> changeStatus(HttpServletRequest request,
	 * 
	 * @PathVariable("codigo") Long codigo,
	 * 
	 * @PathVariable("status") String status,
	 * 
	 * @RequestBody Ticket ticket, BindingResult result) {
	 * 
	 * Response<Ticket> response = new Response<Ticket>();
	 * 
	 * 
	 * try { validateChangeStatus(codigo, status, result); if
	 * (result.hasErrors()) { result.getAllErrors().forEach(error ->
	 * response.getErrors().add(error.getDefaultMessage())); return
	 * ResponseEntity.badRequest().body(response); } Ticket ticketCurrent =
	 * ticketService.findByCodigo(codigo); ticketCurrent.setStatus(status);
	 * String st = "Adquirido" ; if(status.equals(st)) {
	 * ticketCurrent.setAssignedUser(userFromRequest(request)); } Ticket
	 * ticketPersisted = (Ticket) ticketService.createOrUpdate(ticketCurrent);
	 * ChangeStatus changeStatus = new ChangeStatus();
	 * changeStatus.setUserChange(userFromRequest(request).getNome());
	 * //changeStatus.setDateChangeStatus(LocalDate.now());
	 * changeStatus.setStatus(status); changeStatus.setTicket(ticketCurrent);
	 * if(status.equals("Aprovado") || status.equals("Reprovado") ) {
	 * //ticketPersisted.setDataInstalacao(LocalDate.now()); }
	 * //ticketPersisted.setDescricao("TEstesssss");
	 * ticketService.createChangeStatus(changeStatus);
	 * response.setData(ticketPersisted); } catch (Exception e) {
	 * response.getErrors().add(e.getMessage()); return
	 * ResponseEntity.badRequest().body(response); } return
	 * ResponseEntity.ok(response); }
	 */

	private void validateChangeStatus(Long codigo, String status, BindingResult result) {
		if (codigo == null || codigo.equals("")) {
			result.addError(new ObjectError("codigo", "codigo não informado"));
			return;
		}
		if (status == null || status.equals("")) {
			result.addError(new ObjectError("Ticket", "Status não informado"));
			return;
		}
	}

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
		Iterable<ChangeStatus> changesCurrent = ticketService.listChangeStatus(codigo);
		for (Iterator<ChangeStatus> iterator = changesCurrent.iterator(); iterator.hasNext();) {
			ChangeStatus changeStatus = iterator.next();
			changeStatus.setTicket(null);
			changes.add(changeStatus);
		}
		ticket.setChanges(changes);
		response.setData(ticket);
		return ResponseEntity.ok(response);
	}

	
	  @GetMapping(value = "/{page}/{count}")
	  @PreAuthorize("hasAnyRole('CUSTOMER','TECHNICIAN')") public
	  ResponseEntity<Response<Page<Ticket>>> listAll(HttpServletRequest
	  request, @PathVariable int page, @PathVariable int count) {
	  
	  Response<Page<Ticket>> response = new Response<Page<Ticket>>();
	  Page<Ticket> tickets = null; User userRequest = userFromRequest(request);
	  if (userRequest.getProfile().equals(ProfileEnum.ROLE_TECHNICIAN)) {
	  tickets = ticketService.listTicket(page, count); } else if
	  (userRequest.getProfile().equals(ProfileEnum.ROLE_CUSTOMER)) { tickets =
	  ticketService.findByCoordenacao(page, count,userRequest.getCoordenacao()
	 ); } response.setData(tickets); return ResponseEntity.ok(response); }
	 
/*
	@GetMapping
	@PreAuthorize("hasAnyRole('CUSTOMER','TECHNICIAN')")
	public ResponseEntity<Response<Page<Ticket>>> findAll(@RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer count) {

		Response<Page<Ticket>> response = new Response<Page<Ticket>>();
		Page<Ticket> tickets = null;
		User userRequest = userFromRequest(request);
		if (userRequest.getProfile().equals(ProfileEnum.ROLE_TECHNICIAN)) {
			tickets = ticketService.listTicket(page, count);
		} else if (userRequest.getProfile().equals(ProfileEnum.ROLE_CUSTOMER)) {
			tickets = ticketService.findByCoordenacao(page, count, userRequest.getCoordenacao());
		}
		response.setData(tickets);
		return ResponseEntity.ok(response);
	}
*/
	@GetMapping(value = "{page}/{count}/{numeroNotaRelease}/{status}/{assigned}")
	@PreAuthorize("hasAnyRole('CUSTOMER','TECHNICIAN')")
	public ResponseEntity<Response<Page<Ticket>>> findByParams(HttpServletRequest request,
			@PathVariable("page") int page, @PathVariable("count") int count,
			@PathVariable("numeroNotaRelease") String numeroNotaRelease, @PathVariable("status") String status,
			@PathVariable("assigned") boolean assigned) {

		numeroNotaRelease = numeroNotaRelease.equals("uninformed") ? "" : numeroNotaRelease;
		status = status.equals("uninformed") ? "" : status;
		Response<Page<Ticket>> response = new Response<Page<Ticket>>();
		Page<Ticket> tickets = null;
		if (!numeroNotaRelease.isEmpty() && status.isEmpty()) {
			tickets = ticketService.findByNumeroNotaRelease(page, count, numeroNotaRelease);
		} else {
			User userRequest = userFromRequest(request);
			if (userRequest.getProfile().equals(ProfileEnum.ROLE_TECHNICIAN)) {
				if (assigned) {
					tickets = ticketService.findByNumeroNotaReleaseAndStatusAndUsuarioTecnicoAtribuido(page, count,
							numeroNotaRelease, status, userRequest.getCodigo());
				} else {

					tickets = ticketService.findByNumeroNotaReleaseAndStatus(page, count, numeroNotaRelease, status);
				}

			} else if (userRequest.getProfile().equals(ProfileEnum.ROLE_CUSTOMER)) {

				tickets = ticketService.findByStatusAndUsuarioCliente(page, count, status, userRequest.getCodigo());
			}
		}
		response.setData(tickets);
		return ResponseEntity.ok(response);
	}

	

	@GetMapping()
	@PreAuthorize("hasAnyRole('CUSTOMER','TECHNICIAN')")
	public ResponseEntity<Response<Page<Ticket>>> findAll(HttpServletRequest request,
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "count", required = false, defaultValue = "5") Integer count,
			@RequestParam(name = "codigonr", required = false, defaultValue = "") String codigonr, 
			@RequestParam(name = "status", required = false, defaultValue = "") String status,
			@RequestParam(name = "nomesistema", required = false, defaultValue = "") String nomesistema,
			@RequestParam(name = "coordenacao", required = false, defaultValue = "") String coordenacao,
			@RequestParam(name = "dateini", required = false, defaultValue = "") String dateini,
			@RequestParam(name = "datefim", required = false, defaultValue = "") String datefim,
			@RequestParam(name = "fonte", required = false, defaultValue = "") String fonte
			
			
	) throws ParseException {
		
		Response<Page<Ticket>> response = new Response<Page<Ticket>>();
		Page<Ticket> tickets = null;
		List<String> systemNotes= new ArrayList<String>();
		User userRequest = userFromRequest(request);
		LocalDate Dateini; LocalDate Datefim;Long CodigoL; 
		String Fonte =fonte;
		
		
		if (dateini.isEmpty()){Dateini = LocalDate.parse("0001-01-01");} else {Dateini = LocalDate.parse(dateini);}
		if (datefim.isEmpty()) {Datefim = LocalDate.now();} else {Datefim = LocalDate.parse(datefim);}
		if (codigonr.isEmpty()) {CodigoL = null;} else {CodigoL = Long.parseLong(codigonr);}
		
		if (userRequest.getProfile().equals(ProfileEnum.ROLE_TECHNICIAN) ) {
			tickets = null;		   
			tickets = ListarParaTecnico(page, count,CodigoL, status, nomesistema,coordenacao, Dateini, Datefim);			
		} else if (userRequest.getProfile().equals(ProfileEnum.ROLE_CUSTOMER)) {	
			listarCoordenacoes(userRequest, systemNotes); 
			tickets = null;
		tickets = ListarParaCliente(page, count,CodigoL,status, nomesistema, coordenacao, Dateini, Datefim,systemNotes);}
		
		
	
		response.setData(tickets);
		return ResponseEntity.ok(response);
	}
	
	private Page<Ticket> ListarParaCliente(Integer page, Integer count, Long codigo,String status, String sistema, String coordenacao, LocalDate Dateini,LocalDate Datefim,List<String> systemNotes) {
		Page<Ticket> tickets = null;
		if (codigo == null){
			
			tickets =  ticketService.findByParam(page, count, status, sistema, coordenacao,Dateini,Datefim,systemNotes);
			
		} else {
			
			tickets =  ticketService.findByParamCodigo(page, count, codigo, status, sistema, coordenacao,Dateini,Datefim,systemNotes);
		}
		return tickets;
	}

	private Page<Ticket> ListarParaTecnico(Integer page, Integer count, Long codigo,String status, String sistema, String coordenacao, LocalDate Dateini,LocalDate Datefim) {
		Page<Ticket> tickets;
		if (codigo == null){
			tickets =  ticketService.findByParamUserTecnico(page, count, status,sistema, coordenacao,Dateini,Datefim);
		} else {
			tickets =  ticketService.findByParamUserTecnicoCodigo(page, count, codigo, status, sistema, coordenacao,  Dateini, Datefim);
		}
		return tickets;
	}

	private void listarCoordenacoes(User userRequest, List<String> systemNotes) {
		List<CoordSystemNotes> coordsystemNotes = null;
		coordsystemNotes = systemNotesService.findByCoordSystem(userRequest.getCoordenacao());
			 for (int i=0; i< coordsystemNotes.size(); i++) {
		      	systemNotes.add(coordsystemNotes.get(i).getNomeSystem());
		   }
	}
	

	/*
	 * @GetMapping(value = "/summary") public ResponseEntity<Response<Summary>>
	 * findChart() { Response<Summary> response = new Response<Summary>();
	 * Summary chart = new Summary(); int amountNew = 0; int amountResolved = 0;
	 * int amountApproved = 0; int amountDisapproved = 0; int amountAssigned =
	 * 0; int amountClosed = 0; Iterable<Ticket> tickets =
	 * ticketService.findAll(); if (tickets != null) { for (Iterator<Ticket>
	 * iterator = tickets.iterator(); iterator.hasNext();) { Ticket ticket =
	 * iterator.next(); if(ticket.getStatus().equals(StatusEnum.New)){ amountNew
	 * ++; } if(ticket.getStatus().equals(StatusEnum.Resolved)){ amountResolved
	 * ++; } if(ticket.getStatus().equals(StatusEnum.Approved)){ amountApproved
	 * ++; } if(ticket.getStatus().equals(StatusEnum.Disapproved)){
	 * amountDisapproved ++; }
	 * if(ticket.getStatus().equals(StatusEnum.Assigned)){ amountAssigned ++; }
	 * if(ticket.getStatus().equals(StatusEnum.Closed)){ amountClosed ++; } } }
	 * chart.setAmountNew(amountNew); chart.setAmountResolved(amountResolved);
	 * chart.setAmountApproved(amountApproved);
	 * chart.setAmountDisapproved(amountDisapproved);
	 * chart.setAmountAssigned(amountAssigned);
	 * chart.setAmountClosed(amountClosed); response.setData(chart); return
	 * ResponseEntity.ok(response); }
	 */
	
	
	
	

}
