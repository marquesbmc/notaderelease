package com.caixa.notaderelease.api.resource;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.caixa.notaderelease.api.model.mysql.CoordSystemNotes;
import com.caixa.notaderelease.api.model.mysql.ReleaseNotes;
import com.caixa.notaderelease.api.model.mysql.Ticket;
import com.caixa.notaderelease.api.model.mysql.User;
import com.caixa.notaderelease.api.response.Response;
import com.caixa.notaderelease.api.security.jwt.JwtTokenUtil;
import com.caixa.notaderelease.api.service.CoordSystemNotesService;
import com.caixa.notaderelease.api.service.ReleaseNotesService;
import com.caixa.notaderelease.api.service.StoreProcedureService;
import com.caixa.notaderelease.api.service.TicketService;
import com.caixa.notaderelease.api.service.UserService;

@RestController
@RequestMapping("/api/releasenotes")
@CrossOrigin(origins = "*")
// @CrossOrigin(origins = "http://pefug.arquitetura.caixa:4200")
public class ReleaseNotesResource {

	@Autowired
	private ReleaseNotesService releaseNotesService;

	@Autowired
	private CoordSystemNotesService systemNotesService;

	@Autowired
	protected JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserService userService;

	@Autowired
	TicketService ticketService;

	//@Autowired
	//private StoreProcedureService storeProcedureService;
	
	/*@PostMapping(value = "/validasp")
	public ResponseEntity<Response<StoreProcedure>> create(@RequestBody StoreProcedure storeProcedure,
			BindingResult result) {
		Response<StoreProcedure> response = new Response<StoreProcedure>();
		try {

			// verificacao de erro de dados
			validateCreateStoreProcedure(storeProcedure, result);
			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			StoreProcedure spAnalizada = storeProcedure;

			spAnalizada.setStatus(ValidarSP(storeProcedure));
			response.setData(spAnalizada);

		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}

		return ResponseEntity.ok(response);
	}*/

	/*private String ValidarSP(StoreProcedure storeProcedure) {
		String result = null;
		List<String> spListdivergente = new ArrayList<String>();
		List<String> spListIguais = new ArrayList<String>();
		List<String> spList = new ArrayList<String>(Arrays.asList(storeProcedure.getNome().split("\n")));
		List<String> spListTQS = storeProcedureService.buscarSP();

		// possui sp repetida?
		for (int i = 0; i < spList.size(); i++) {
			for (int j = i + 1; j < spList.size(); j++) {
				if (spList.get(i).equals(spList.get(j))) {
					spListIguais.add(spList.get(i));
				}
			}
		}
		// possui sp fora base?
		if (spListIguais.isEmpty()) {
			for (String spItem : spList) {
				if (!spListTQS.contains(spItem)) {
					spListdivergente.add(spItem);
				}
			}
		}

		if (!spListIguais.isEmpty()) {
			result = ("Stored procedure(s) duplicada(s): <br \\>");
			for (String spr : spListIguais) {
				result += spr + "<br \\>";
			}
		} else if (!spListdivergente.isEmpty()) {
			result = ("Stored procedure(s) fora do catálogo DB2-TQS: <br \\> ");
			for (String spr : spListdivergente) {
				result += spr + "<br \\>";
			}
		}

		// if (result == null) {
		// result = "Lista de StoredProcedure verificadas no ambiente TQS.";
		// }

		return result;
	}*/

	/*private void validateCreateStoreProcedure(StoreProcedure storeProcedure, BindingResult result) {
		if (storeProcedure.getCodigo() == '1') {
			result.addError(new ObjectError("Stored procedure(s) nok", "Favor informar"));
			return;
		}

	}*/

	@GetMapping(value = "/{page}/{count}")
	@PreAuthorize("hasAnyRole('CUSTOMER','TECHNICIAN')")
	public ResponseEntity<Response<Page<ReleaseNotes>>> findAll(HttpServletRequest request, @PathVariable int page,
			@PathVariable int count) {
		User userRequest = userFromRequest(request);
		Response<Page<ReleaseNotes>> response = new Response<Page<ReleaseNotes>>();
		Page<ReleaseNotes> releaseNotes = null;

		if (userRequest.getProfile().equals(ProfileEnum.ROLE_TECHNICIAN)) {
			releaseNotes = releaseNotesService.findAll(page, count);
		} else if (userRequest.getProfile().equals(ProfileEnum.ROLE_CUSTOMER)) {
			List<CoordSystemNotes> coordsystemNotes = null;
			coordsystemNotes = systemNotesService.findByCoordSystem(userRequest.getCoordenacao());
			List<String> systemNotes = new ArrayList<String>();
			for (int i = 0; i < coordsystemNotes.size(); i++) {
				systemNotes.add(coordsystemNotes.get(i).getNomeSystem());
			}

			releaseNotes = releaseNotesService.findByNomeSistemaIn(page, count, systemNotes);
		}

		buscarCodigoStatusTicket(releaseNotes, userRequest);

		response.setData(releaseNotes);
		return ResponseEntity.ok(response);
	}

	private User userFromRequest(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		String matricula = jwtTokenUtil.getUsernameFromToken(token);
		return userService.findByMatricula(matricula);
	}

	@GetMapping(value = "/{codigo}")
	@PreAuthorize("hasAnyRole('CUSTOMER','TECHNICIAN')")
	public ResponseEntity<Response<ReleaseNotes>> findByCodigo(@PathVariable("codigo") Long codigo) {
		Response<ReleaseNotes> response = new Response<ReleaseNotes>();
		ReleaseNotes releaseNotes = releaseNotesService.findByCodigo(codigo);

		Ticket ticket = new Ticket();
		ticket = ticketService.findByNumeroNotaRelease(releaseNotes);
		if (ticket != null) {
			releaseNotes.setCodTicket(ticket.getCodigo().toString());
			releaseNotes.setStatusTicket(ticket.getStatus().toString());
			releaseNotes.setCoordenacao(ticket.getCoordenacao().toString());
		}

		response.setData(releaseNotes);
		return ResponseEntity.ok(response);
	}

	@PostMapping()
	@PreAuthorize("hasAnyRole('CUSTOMER')")
	public ResponseEntity<Response<ReleaseNotes>> create(HttpServletRequest request,
			@RequestBody ReleaseNotes releaseNotes, BindingResult result) {
		Response<ReleaseNotes> response = new Response<ReleaseNotes>();
		try {
			validateCreateReleaseNotes(releaseNotes, result);
			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			releaseNotes.setStatusNr("Nova");
			releaseNotes.setDataCriacao(LocalDate.now());
			ReleaseNotes releaseNotesPersisted = (ReleaseNotes) releaseNotesService.createOrUpdate(releaseNotes);
			response.setData(releaseNotesPersisted);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}
	
	private void validateCreateReleaseNotes(ReleaseNotes releaseNotes, BindingResult result) {
	
		
		if (releaseNotes.getNomeSistema() == null) {
			result.addError(new ObjectError("Nome do sistema", "Informar sistema da Nota de Release"));
			return;
		}
		
		
		/*if (releaseNotes.getAmbienteDeploy().contains("Homologação") ) {
		 StoreProcedure newSp = new StoreProcedure();
		 newSp.setCodigo((long) 1);
		 newSp.setNome(releaseNotes.getDatabase().getAcessoBanco());
		 String resultadoValidaSp = ValidarSP(newSp);

			if (resultadoValidaSp!=null) {
				result.addError(new ObjectError("Lista de StoreProcedure fora padrão", resultadoValidaSp));
				return;
			}
		 
		
		}*/
		
	
		
		
		
	}

	@PutMapping()
	@PreAuthorize("hasAnyRole('CUSTOMER')")
	public ResponseEntity<Response<ReleaseNotes>> update(HttpServletRequest request,
			@RequestBody ReleaseNotes releaseNotes, BindingResult result) {
		Response<ReleaseNotes> response = new Response<ReleaseNotes>();
		try {

			ReleaseNotes releaseNotesCurrent = releaseNotesService.findByCodigo(releaseNotes.getCodigo());
			validateUpdate(releaseNotes, releaseNotesCurrent, result);
		
			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			///////////////////// releaseNotesPersisted// Se esta aprovado nao
			///////////////////// pode modificar nada --
			/////////////////////// precisa fazer um metodo para virar true
			releaseNotes.setStatusNr(releaseNotesCurrent.getStatusNr());
			ReleaseNotes releaseNotesPersisted = (ReleaseNotes) releaseNotesService.update(releaseNotes);
			response.setData(releaseNotesPersisted);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}

	private void validateUpdate(ReleaseNotes releaseNotes, ReleaseNotes releaseNotesCurrent, BindingResult result) {
		if (releaseNotes.getNomeSistema() == null) {
			result.addError(new ObjectError("Sistema", "Sistema não informado!"));
			return;
		}
		
		/*if (releaseNotes.getAmbienteDeploy().contains("Homologação") ) {
		StoreProcedure newSp = new StoreProcedure();
		 newSp.setCodigo((long) 1);
		 newSp.setNome(releaseNotes.getDatabase().getAcessoBanco());
		 String resultadoValidaSp = ValidarSP(newSp);

		 if (resultadoValidaSp!=null  ) {
				result.addError(new ObjectError("Lista de StoreProcedure fora padrão", resultadoValidaSp));
				return;
			}
		}*/
		 
	}

	@DeleteMapping(value = "/{codigo}")
	@PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
	public ResponseEntity<Response<String>> delete(@PathVariable("codigo") Long codigo) {
		Response<String> response = new Response<String>();

		ReleaseNotes relesenote = releaseNotesService.findByCodigo(codigo);
		if (relesenote.getCodigo() == null) {
			response.getErrors().add("Código não registrado:" + codigo);
			return ResponseEntity.badRequest().body(response);
		}

		ReleaseNotes nr = new ReleaseNotes();
		nr.setCodigo(codigo);
		Ticket ticket = new Ticket();
		ticket = ticketService.findByNumeroNotaRelease(nr);
		if (ticket != null) {
			response.getErrors().add("Nota de Release Vinculada com Ticket: " + ticket.getCodigo());
			return ResponseEntity.badRequest().body(response);
		}

		// nr.setCodigo(codigo);
		// Long count = ticketService.countByNumeroNotaRelease(nr);
		// if (count != 0) {
		// response.getErrors().add("Nota de Release Vinculada não pode ser
		// excluída:" + codigo);
		// return ResponseEntity.badRequest().body(response);
		// }
		// Ticket ticket =
		// ticketService.findByNumeroNotaRelease(codigo.toString());

		// if (ticketService.findByNumeroNotaRelease(codigo.toString()) == null)
		// {
		// response.getErrors().add("Código não registrado:" + codigo);
		// return ResponseEntity.badRequest().body(response);
		// }

		releaseNotesService.delete(codigo);
		return ResponseEntity.ok(new Response<String>());
	}

	@GetMapping(value = "/{page}/{count}/{codigo}/{nomeSistema}/{dataCriacao}/{versaoCodigoFonte}/{statusNr}")
	@PreAuthorize("hasAnyRole('CUSTOMER','TECHNICIAN')")
	public ResponseEntity<Response<Page<ReleaseNotes>>> findByParams(HttpServletRequest request,
			@PathVariable("page") int page, @PathVariable("count") int count, @PathVariable("codigo") String codigo,
			@PathVariable("nomeSistema") String nomeSistema, @PathVariable("dataCriacao") String dataCriacao,
			@PathVariable("versaoCodigoFonte") String versaoCodigoFonte, @PathVariable("statusNr") String statusNr) {

		User userRequest = userFromRequest(request);
		LocalDate dateCreation = null;
		long code = -1;
		codigo = codigo.equals("uninformed") ? null : codigo;
		if (codigo != null) {
			code = Long.parseLong(codigo);
		}

		nomeSistema = nomeSistema.equals("uninformed") ? "" : nomeSistema;

		dataCriacao = dataCriacao.equals("uninformed") ? null : dataCriacao;
		if (dataCriacao != null) {
			dateCreation = LocalDate.parse(dataCriacao);
		}

		versaoCodigoFonte = versaoCodigoFonte.equals("uninformed") ? "" : versaoCodigoFonte;
		statusNr = statusNr.equals("uninformed") ? "" : statusNr;
		Response<Page<ReleaseNotes>> response = new Response<Page<ReleaseNotes>>();
		Page<ReleaseNotes> releaseNotes = null;

		if (codigo != null && dataCriacao == null) {
			releaseNotes = releaseNotesService
					.findByCodigoAndNomeSistemaIgnoreCaseContainingAndVersaoCodigoFonteIgnoreCaseContainingAndStatusNrIgnoreCaseContaining(
							page, count, code, nomeSistema, versaoCodigoFonte, statusNr);
		} else if (codigo == null && dataCriacao != null) {
			releaseNotes = releaseNotesService
					.findByDataCriacaoAndNomeSistemaIgnoreCaseContainingAndVersaoCodigoFonteIgnoreCaseContainingAndStatusNrIgnoreCaseContaining(
							page, count, dateCreation, nomeSistema, versaoCodigoFonte, statusNr);
		} else if (codigo == null && dataCriacao == null) {
			releaseNotes = releaseNotesService
					.findByNomeSistemaIgnoreCaseContainingAndVersaoCodigoFonteIgnoreCaseContainingAndStatusNrIgnoreCaseContaining(
							page, count, nomeSistema, versaoCodigoFonte, statusNr);
		} else if (codigo != null && dataCriacao != null) {
			releaseNotes = releaseNotesService
					.findByCodigoAndDataCriacaoAndNomeSistemaIgnoreCaseContainingAndVersaoCodigoFonteIgnoreCaseContainingAndStatusNrIgnoreCaseContaining(
							page, count, code, dateCreation, nomeSistema, versaoCodigoFonte, statusNr);
		} else {
			releaseNotes = releaseNotesService.findAll(page, count);
		}

		buscarCodigoStatusTicket(releaseNotes, userRequest);
		response.setData(releaseNotes);
		return ResponseEntity.ok(response);

	}

	@GetMapping
	@PreAuthorize("hasAnyRole('CUSTOMER','TECHNICIAN')")
	public ResponseEntity<Response<Page<ReleaseNotes>>> findAll(HttpServletRequest request,
			@RequestParam(name = "codigo", required = false, defaultValue = "") String codigo,
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "count", required = false, defaultValue = "5") Integer count,
			@RequestParam(name = "nomesistema", required = false, defaultValue = "") String nomesistema,
			@RequestParam(name = "status", required = false, defaultValue = "") String status,
			@RequestParam(name = "versaocodigocompilado", required = false, defaultValue = "") String versaocodigocompilado,
			@RequestParam(name = "versaocodigofonte", required = false, defaultValue = "") String versaocodigofonte,
			@RequestParam(name = "dateini", required = false, defaultValue = "") String dateini,
			@RequestParam(name = "datefim", required = false, defaultValue = "") String datefim

	) throws ParseException {
		Response<Page<ReleaseNotes>> response = new Response<Page<ReleaseNotes>>();
		Page<ReleaseNotes> relesenotes = null;
		User userRequest = userFromRequest(request);

		LocalDate Dateini;
		LocalDate Datefim;

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

		Long CodigoL;
		if (codigo.isEmpty()) {
			CodigoL = null;
		} else {
			CodigoL = Long.parseLong(codigo);
		}
		List<String> systemNotes = new ArrayList<String>();

		if (userRequest.getProfile().equals(ProfileEnum.ROLE_TECHNICIAN)) {
			relesenotes = ListarParaTecnico(page, count, nomesistema, status, versaocodigocompilado, versaocodigofonte,
					Dateini, Datefim, CodigoL);
		} else if (userRequest.getProfile().equals(ProfileEnum.ROLE_CUSTOMER)) {
			listarCoordenacoes(userRequest, systemNotes);
			relesenotes = ListarParaCliente(page, count, nomesistema, status, versaocodigocompilado, versaocodigofonte,
					Dateini, Datefim, CodigoL, systemNotes);
		}

		buscarCodigoStatusTicket(relesenotes, userRequest);
		response.setData(relesenotes);
		return ResponseEntity.ok(response);
	}

	private void listarCoordenacoes(User userRequest, List<String> systemNotes) {
		List<CoordSystemNotes> coordsystemNotes = null;
		coordsystemNotes = systemNotesService.findByCoordSystem(userRequest.getCoordenacao());
		for (int i = 0; i < coordsystemNotes.size(); i++) {
			systemNotes.add(coordsystemNotes.get(i).getNomeSystem());
		}
	}

	private Page<ReleaseNotes> ListarParaCliente(Integer page, Integer count, String nomeSistema, String status,
			String versaocodigocompilado, String versaocodigofonte, LocalDate Dateini, LocalDate Datefim, Long CodigoL,
			List<String> systemNotes) {
		Page<ReleaseNotes> relesenotes;
		if (CodigoL == null) {
			relesenotes = releaseNotesService.findByParam(nomeSistema, status, versaocodigocompilado, versaocodigofonte,
					Dateini, Datefim, systemNotes, page, count);
		} else {
			relesenotes = releaseNotesService.findByParamCodigo(CodigoL, nomeSistema, status, versaocodigocompilado,
					versaocodigofonte, Dateini, Datefim, systemNotes, page, count);
		}
		return relesenotes;
	}

	private Page<ReleaseNotes> ListarParaTecnico(Integer page, Integer count, String nomeSistema, String status,
			String versaocodigocompilado, String versaocodigofonte, LocalDate Dateini, LocalDate Datefim,
			Long CodigoL) {
		Page<ReleaseNotes> relesenotes;
		if (CodigoL == null) {
			relesenotes = releaseNotesService.findByParamTecnico(nomeSistema, status, versaocodigocompilado,
					versaocodigofonte, Dateini, Datefim, page, count);
		} else {
			relesenotes = releaseNotesService.findByParamCodigoTecnico(CodigoL, nomeSistema, status,
					versaocodigocompilado, versaocodigofonte, Dateini, Datefim, page, count);
		}
		return relesenotes;
	}

	private void buscarCodigoStatusTicket(Page<ReleaseNotes> releaseNotes, User userRequest) {

		releaseNotes.forEach(rn ->

		rn.setCoordenacao(userRequest.getCoordenacao()));

		releaseNotes.forEach(rn ->

		rn.setCodTicket(ticketService.findByNumeroNotaRelease(rn) != null
				? String.valueOf(ticketService.findByNumeroNotaRelease(rn).getCodigo()) : "")

		);

		releaseNotes.forEach(rn ->

		rn.setStatusTicket(ticketService.findByNumeroNotaRelease(rn) != null
				? String.valueOf(ticketService.findByNumeroNotaRelease(rn).getStatus()) : ""));
	}

}