package com.caixa.notaderelease.api.resource;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caixa.notaderelease.api.model.ReleaseNoteOld;
import com.caixa.notaderelease.api.model.Ticket;
import com.caixa.notaderelease.api.model.User;
import com.caixa.notaderelease.api.response.Response;
import com.caixa.notaderelease.api.security.jwt.JwtTokenUtil;
import com.caixa.notaderelease.api.service.ReleaseNoteOldService;

@RestController
@RequestMapping("/api/releasesnotess")
@CrossOrigin(origins = "*")
public class ReleaseNotesOldResource {

	private static final Boolean True = true;

	@Autowired
	private ReleaseNoteOldService releaseNoteService;

	@Autowired
	protected JwtTokenUtil jwtTokenUtil;
	
	//foi
	@PostMapping()
	@PreAuthorize("hasAnyRole('CUSTOMER')")
	public ResponseEntity<Response<ReleaseNoteOld>> create(HttpServletRequest request,
			@RequestBody ReleaseNoteOld releaseNote, BindingResult result) {
		Response<ReleaseNoteOld> response = new Response<ReleaseNoteOld>();
		try {
			validateCreateReleaseNote(releaseNote, result);
			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			ReleaseNoteOld releaseNotePersisted = (ReleaseNoteOld) releaseNoteService.createOrUpdate(releaseNote);
			response.setData(releaseNotePersisted);

		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}
	//foi
	private void validateCreateReleaseNote(ReleaseNoteOld releaseNote, BindingResult result) {
		if (releaseNote.getNomeSistema() == null) {
			result.addError(new ObjectError("Nome do sistema", "Informar sistema da Nota de Release"));
			return;
		}
	}
    
	//foi
	@GetMapping(value = "/{codigo}")
	//@PreAuthorize("hasAnyRole('CUSTOMER','TECHNICIAN')")
	public ResponseEntity<Response<ReleaseNoteOld>> findByCodigo(@PathVariable("codigo") Long codigo) {
		Response<ReleaseNoteOld> response = new Response<ReleaseNoteOld>();
		ReleaseNoteOld releaseNote = releaseNoteService.findByCodigo(codigo);
		if (releaseNote == null) {
			response.getErrors().add("Register not found Codigo:" + codigo);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(releaseNote);
		return ResponseEntity.ok(response);
	}
	
	//foi
	@GetMapping(value = "/{page}/{count}")
	@PreAuthorize("hasAnyRole('CUSTOMER','TECHNICIAN')")
	public ResponseEntity<Response<Page<ReleaseNoteOld>>> findAll(HttpServletRequest request, @PathVariable int page,
			@PathVariable int count) {

		Response<Page<ReleaseNoteOld>> response = new Response<Page<ReleaseNoteOld>>();
		Page<ReleaseNoteOld> releaseNotes = null;
		releaseNotes = releaseNoteService.findAll(page, count);
		response.setData(releaseNotes);
		return ResponseEntity.ok(response);
	}
	
	
	//foi
	@PutMapping()
	@PreAuthorize("hasAnyRole('CUSTOMER')")
	public ResponseEntity<Response<ReleaseNoteOld>> update(HttpServletRequest request, @RequestBody ReleaseNoteOld releaseNote,
			BindingResult result) {
		Response<ReleaseNoteOld> response = new Response<ReleaseNoteOld>();
		try {
			ReleaseNoteOld releaseNoteCurrent = releaseNoteService.findByCodigo(releaseNote.getCodigo());
			validateUpdate(releaseNote, releaseNoteCurrent, result);
			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			/////////////////////// Se esta aprovado nao pode modificar nada -- precisa fazer um metodo para virar true
			releaseNote.setEstaAprovado(releaseNoteCurrent.getEstaAprovado());
			
			ReleaseNoteOld releaseNotePersisted = (ReleaseNoteOld) releaseNoteService.createOrUpdate(releaseNote);
			response.setData(releaseNotePersisted);
			
			
			
			

		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}

	
	//foi
	private void validateUpdate(ReleaseNoteOld releaseNote, ReleaseNoteOld releaseNoteCurrent,  BindingResult result) {
		if (releaseNote.getNomeSistema() == null) {
			result.addError(new ObjectError("Sistema", "Sistema não informado!"));
			return;
		}
		if (releaseNoteCurrent.getEstaAprovado() == True) {
			result.addError(new ObjectError("Nota de Release aprovada", "Nota de Release aprovado, não pode alterar dados!"));
			return;
		}
	}

}
