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

import com.caixa.notaderelease.api.enums.ProfileEnum;
import com.caixa.notaderelease.api.model.CoordSystemNotes;
import com.caixa.notaderelease.api.model.ReleaseNotes;
import com.caixa.notaderelease.api.model.Ticket;
import com.caixa.notaderelease.api.model.User;
import com.caixa.notaderelease.api.response.Response;
import com.caixa.notaderelease.api.security.jwt.JwtTokenUtil;
import com.caixa.notaderelease.api.service.CoordSystemNotesService;
import com.caixa.notaderelease.api.service.ReleaseNotesService;
import com.caixa.notaderelease.api.service.TicketService;
import com.caixa.notaderelease.api.service.UserService;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class estatistica {
	
	
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
		
		if (userRequest.getProfile().equals(ProfileEnum.ROLE_TECHNICIAN) || Fonte !="" ) {
			tickets = null;		   
			tickets = ListarParaTecnico(page, count,CodigoL, status, nomesistema,coordenacao, Dateini, Datefim);			
		} else if (userRequest.getProfile().equals(ProfileEnum.ROLE_CUSTOMER)) {	
			listarCoordenacoes(userRequest, systemNotes); 
			tickets = null;
		tickets = ListarParaCliente(page, count,CodigoL,status, nomesistema, coordenacao, Dateini, Datefim,systemNotes);}
		
	
		response.setData(tickets);
		return ResponseEntity.ok(response);
	}
	
	private User userFromRequest(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		String matricula = jwtTokenUtil.getUsernameFromToken(token);
		return userService.findByMatricula(matricula);
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
	
	private Page<Ticket> ListarParaCliente(Integer page, Integer count, Long codigo,String status, String sistema, String coordenacao, LocalDate Dateini,LocalDate Datefim,List<String> systemNotes) {
		Page<Ticket> tickets = null;
		if (codigo == null){
			
			tickets =  ticketService.findByParam(page, count, status, sistema, coordenacao,Dateini,Datefim,systemNotes);
			
		} else {
			
			tickets =  ticketService.findByParamCodigo(page, count, codigo, status, sistema, coordenacao,Dateini,Datefim,systemNotes);
		}
		return tickets;
	}

}
