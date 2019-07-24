package com.caixa.notaderelease.api.resource;



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.DateFormatter;

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

import com.caixa.notaderelease.api.enums.ProfileEnum;
import com.caixa.notaderelease.api.model.CoordSystemNotes;
import com.caixa.notaderelease.api.model.ReleaseNotes;
import com.caixa.notaderelease.api.model.User;
import com.caixa.notaderelease.api.response.Response;
import com.caixa.notaderelease.api.security.jwt.JwtTokenUtil;
import com.caixa.notaderelease.api.service.CoordSystemNotesService;
import com.caixa.notaderelease.api.service.ReleaseNotesService;
import com.caixa.notaderelease.api.service.UserService;

@RestController
@RequestMapping("/api/releasenotes")
@CrossOrigin(origins = "*")
public class ReleaseNotesResource {

	@Autowired
	private ReleaseNotesService releaseNotesService;
	
	@Autowired
	private CoordSystemNotesService systemNotesService;

	@Autowired
	protected JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private UserService userService;

	@SuppressWarnings("null")
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
			List<String> systemNotes= new ArrayList<String>();
			 for (int i=0; i< coordsystemNotes.size(); i++) {
		      	systemNotes.add(coordsystemNotes.get(i).getNomeSystem());
		     } 
	        releaseNotes =  releaseNotesService.findByNomeSistemaIn(page, count, systemNotes);
		}
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
		if (releaseNotes == null) {
			response.getErrors().add("Registro não encontrado com código:" + codigo);
			return ResponseEntity.badRequest().body(response);
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
			releaseNotes.setStatusNr("Novo");
			  
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
			/////////////////////releaseNotesPersisted// Se esta aprovado nao pode modificar nada --
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
		// if (releaseNoteCurrent.getEstaAprovado() == True) {
		// result.addError(new ObjectError("Nota de Release aprovada", "Nota de
		// Release aprovado, não pode alterar dados!"));
		// return;
		// }
	}

	@DeleteMapping(value = "/{codigo}")
	@PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
	public ResponseEntity<Response<String>> delete(@PathVariable("codigo") Long codigo) {
		Response<String> response = new Response<String>();
		ReleaseNotes releaseNotes = releaseNotesService.findByCodigo(codigo);
		if (releaseNotes == null) {
			response.getErrors().add("Código não registrado:" + codigo);
			return ResponseEntity.badRequest().body(response);
		}
		releaseNotesService.delete(codigo);
		return ResponseEntity.ok(new Response<String>());
	}


	@GetMapping(value = "/{page}/{count}/{codigo}/{nomeSistema}/{dataCriacao}/{versaoCodigoFonte}/{statusNr}")
	@PreAuthorize("hasAnyRole('CUSTOMER','TECHNICIAN')")
	public ResponseEntity<Response<Page<ReleaseNotes>>> findByParams(
			HttpServletRequest request,
			@PathVariable("page") int page, 
			@PathVariable("count") int count,
			@PathVariable("codigo") String codigo,
			@PathVariable("nomeSistema") String nomeSistema,
			@PathVariable("dataCriacao") String dataCriacao,
			@PathVariable("versaoCodigoFonte") String versaoCodigoFonte,
			@PathVariable("statusNr") String statusNr) {
		
	
		LocalDate dateCreation = null;
		long code = -1;
		codigo  = codigo.equals("uninformed") ? null : codigo;
		if(codigo != null ){
			code =  Long.parseLong(codigo);
		}
		
		nomeSistema = nomeSistema.equals("uninformed") ? "" : nomeSistema;
	
		
		dataCriacao = dataCriacao.equals("uninformed") ? null : dataCriacao;
		if(dataCriacao != null ){
			 dateCreation =  LocalDate.parse(dataCriacao);
		}
		
		
		
		versaoCodigoFonte = versaoCodigoFonte.equals("uninformed") ? "" : versaoCodigoFonte;
		statusNr = statusNr.equals("uninformed") ? "" : statusNr;
		Response<Page<ReleaseNotes>> response = new Response<Page<ReleaseNotes>>();
		Page<ReleaseNotes> releaseNotes = null;
		
		if (codigo != null && dataCriacao == null ) {
			releaseNotes = releaseNotesService.findByCodigoAndNomeSistemaIgnoreCaseContainingAndVersaoCodigoFonteIgnoreCaseContainingAndStatusNrIgnoreCaseContaining(page, count, code, nomeSistema, versaoCodigoFonte, statusNr);
			} else if (codigo == null && dataCriacao != null) {
				releaseNotes = releaseNotesService.findByDataCriacaoAndNomeSistemaIgnoreCaseContainingAndVersaoCodigoFonteIgnoreCaseContainingAndStatusNrIgnoreCaseContaining(page, count, dateCreation, nomeSistema, versaoCodigoFonte, statusNr);
			} else if (codigo == null && dataCriacao == null ) {
				releaseNotes = releaseNotesService.findByNomeSistemaIgnoreCaseContainingAndVersaoCodigoFonteIgnoreCaseContainingAndStatusNrIgnoreCaseContaining(page, count, nomeSistema, versaoCodigoFonte, statusNr);
			} else if (codigo != null && dataCriacao != null ) {
				releaseNotes = releaseNotesService.findByCodigoAndDataCriacaoAndNomeSistemaIgnoreCaseContainingAndVersaoCodigoFonteIgnoreCaseContainingAndStatusNrIgnoreCaseContaining(page, count, code, dateCreation, nomeSistema, versaoCodigoFonte, statusNr);
			}else {
				releaseNotes = releaseNotesService.findAll(page, count);
			}
		
		
		response.setData(releaseNotes);
		return ResponseEntity.ok(response);

	}

	

}