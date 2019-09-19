package com.caixa.notaderelease.api.resource;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.caixa.notaderelease.api.model.ChangeStatus;
import com.caixa.notaderelease.api.model.ReleaseNotes;
import com.caixa.notaderelease.api.model.Statistic;
import com.caixa.notaderelease.api.model.Ticket;
import com.caixa.notaderelease.api.model.User;
import com.caixa.notaderelease.api.response.Response;
import com.caixa.notaderelease.api.security.jwt.JwtTokenUtil;
import com.caixa.notaderelease.api.service.CoordSystemNotesService;
import com.caixa.notaderelease.api.service.ReleaseNotesService;
import com.caixa.notaderelease.api.service.TicketService;
import com.caixa.notaderelease.api.service.UserService;



@RestController
@RequestMapping("/api/statistic")
@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "http://pefug.arquitetura.caixa:4200")
public class StatisticsResource {

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
	
	private static final String ATRIBUIDO = "Atribuído"; 
	private static final String INCONFORME = "Inconforme";
	private static final String REAVALIAR = "Reavaliar";
	private static final String APROVADO = "Aprovado";
	private static final String REPROVADO = "Reprovado";
	private static final String NOVO = "Novo";
	private static final String SUBMETER = "Submeter";
	private static final String COMENTAR = "Comentar";
	@GetMapping()
	@PreAuthorize("hasAnyRole('CUSTOMER','TECHNICIAN')")
	public ResponseEntity<Response<Statistic>> findAll(HttpServletRequest request,
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

		Response<Statistic> response = new Response<Statistic>();
		Page<Ticket> tickets = null;
		Page<Ticket> ticketss = null;
		Page<Ticket> ticketsAtrasados = null;
		Statistic statistic = new Statistic();
		List<String> systemNotes = new ArrayList<String>();
		User userRequest = userFromRequest(request);
		LocalDate Dateini;
		LocalDate Datefim;
		Long CodigoL;
		String Fonte = fonte;
		ChangeStatus changestatus;

		if (dateini.isEmpty()) {
			Dateini = LocalDate.parse("0001-01-01");
		} else {
			Dateini = LocalDate.parse(dateini);
		}
		if (datefim.isEmpty()) {
			Datefim = LocalDate.now();
		} else {
			Datefim = LocalDate.parse(datefim);
		}
		if (codigonr.isEmpty()) {
			CodigoL = null;
		} else {
			CodigoL = Long.parseLong(codigonr);
		}

		// total de tickets
		statistic.setTotalTickets(String.valueOf(ticketService.count()));

		// total de tickets aprovados
		tickets = ListarParaTecnico(page, count, CodigoL, APROVADO, nomesistema, coordenacao, Dateini, Datefim);
		statistic.setTotalTicketsAprovados(String.valueOf(tickets.getTotalElements()));

		// total de tickets em analise
		long contAnalise = 0;
		Page<Ticket> ticketATRIBUIDO = ListarParaTecnico(page, count, CodigoL, ATRIBUIDO, nomesistema, coordenacao, Dateini, Datefim);
		Page<Ticket> ticketINCONFORME = ListarParaTecnico(page, count, CodigoL, INCONFORME, nomesistema, coordenacao, Dateini, Datefim);
		Page<Ticket> ticketREAVALIAR = ListarParaTecnico(page, count, CodigoL, REAVALIAR, nomesistema, coordenacao, Dateini, Datefim);
		contAnalise = ticketATRIBUIDO.getTotalElements() +ticketINCONFORME.getTotalElements() +ticketREAVALIAR.getTotalElements();
		statistic.setTotalTicketsAnalise(String.valueOf(contAnalise));
		
		
		
		// total de tickets atrasados
		int contAtrasados = 0;
		tickets =  ticketService.findAllPages(0, (int)tickets.getTotalElements());
				
	
		

	

		
		
	
		
		//ticketss = ListarParaTecnico(page, count, CodigoL, status, nomesistema, coordenacao, Dateini, Datefim);
		for (Ticket ticket : tickets) { 					
			if (
					(ticket.getDataAbertura().plusDays(2)).isBefore(LocalDate.now())
					&& 
					(ticket.getStatus().equals(ATRIBUIDO) || ticket.getStatus().equals(INCONFORME) || ticket.getStatus().equals(REAVALIAR)
					|| ticket.getStatus().equals(COMENTAR) || ticket.getStatus().equals(NOVO)  ) 
					) {
				contAtrasados += 1;
			}
		}
		statistic.setTotalTicketsAtrasados(String.valueOf(contAtrasados));

		response.setData(statistic);
		return ResponseEntity.ok(response);
	}

	private Page<Ticket> ListarParaTecnico(Integer page, Integer count, Long codigo, String status, String sistema,
			String coordenacao, LocalDate Dateini, LocalDate Datefim) {
		Page<Ticket> tickets;
		if (codigo == null) {
			tickets = ticketService.findByParamUserTecnico(page, count, status, sistema, coordenacao, Dateini, Datefim);
		} else {
			tickets = ticketService.findByParamUserTecnicoCodigo(page, count, codigo, status, sistema, coordenacao,
					Dateini, Datefim);
		}
		return tickets;
	}

	private User userFromRequest(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		String matricula = jwtTokenUtil.getUsernameFromToken(token);
		return userService.findByMatricula(matricula);
	}

}
