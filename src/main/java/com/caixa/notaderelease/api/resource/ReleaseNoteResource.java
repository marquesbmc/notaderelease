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

import com.caixa.notaderelease.api.model.ReleaseNote;
import com.caixa.notaderelease.api.model.ReleaseNoteOld;
import com.caixa.notaderelease.api.response.Response;
import com.caixa.notaderelease.api.security.jwt.JwtTokenUtil;
import com.caixa.notaderelease.api.service.ReleaseNoteService;

@RestController
@RequestMapping("/api/releasenote")
@CrossOrigin(origins = "*")
public class ReleaseNoteResource {
	
	private static final Boolean True = true;

	@Autowired
	private ReleaseNoteService releaseNoteService;

	@Autowired
	protected JwtTokenUtil jwtTokenUtil;
	
	
	@PostMapping()
	@PreAuthorize("hasAnyRole('CUSTOMER')")
	public ResponseEntity<Response<ReleaseNote>> create(HttpServletRequest request,
			@RequestBody ReleaseNote releaseNote, BindingResult result) {
		Response<ReleaseNote> response = new Response<ReleaseNote>();
		try {
			validateCreateReleaseNote(releaseNote, result);
			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			ReleaseNote releaseNotePersisted = (ReleaseNote) releaseNoteService.createOrUpdate(releaseNote);
			response.setData(releaseNotePersisted);

		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}

	private void validateCreateReleaseNote(ReleaseNote releaseNote, BindingResult result) {
		if (releaseNote.getSistema() == null) {
			result.addError(new ObjectError("Nome do sistema", "Informar sistema da Nota de Release"));
			return;
		}
	}
	
	
	@GetMapping(value = "/{codigo}")
	@PreAuthorize("hasAnyRole('CUSTOMER','TECHNICIAN')")
	public ResponseEntity<Response<ReleaseNote>> findByCodigo(@PathVariable("codigo") Long codigo) {
		Response<ReleaseNote> response = new Response<ReleaseNote>();
		ReleaseNote releaseNote = releaseNoteService.findByCodigo(codigo);
		if (releaseNote == null) {
			response.getErrors().add("Register not found Codigo:" + codigo);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(releaseNote);
		return ResponseEntity.ok(response);
	}


	@GetMapping(value = "/{page}/{count}")
	@PreAuthorize("hasAnyRole('CUSTOMER','TECHNICIAN')")
	public ResponseEntity<Response<Page<ReleaseNote>>> findAll(HttpServletRequest request, @PathVariable int page,
			@PathVariable int count) {

		Response<Page<ReleaseNote>> response = new Response<Page<ReleaseNote>>();
		Page<ReleaseNote> releaseNotes = null;
		releaseNotes = releaseNoteService.findAll(page, count);
		response.setData(releaseNotes);
		return ResponseEntity.ok(response);
	}

	@PutMapping()
	@PreAuthorize("hasAnyRole('CUSTOMER')")
	public ResponseEntity<Response<ReleaseNote>> update(HttpServletRequest request, @RequestBody ReleaseNote releaseNote,
			BindingResult result) {
		Response<ReleaseNote> response = new Response<ReleaseNote>();
		try {
			ReleaseNote releaseNoteCurrent = releaseNoteService.findByCodigo(releaseNote.getCodigo());
			validateUpdate(releaseNote, releaseNoteCurrent, result);
			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			/////////////////////// Se esta aprovado nao pode modificar nada -- precisa fazer um metodo para virar true
			//releaseNote.setEstaAprovado(releaseNoteCurrent.getEstaAprovado());
			
			ReleaseNote releaseNotePersisted = (ReleaseNote) releaseNoteService.createOrUpdate(releaseNote);
			response.setData(releaseNotePersisted);
			
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}
	
	private void validateUpdate(ReleaseNote releaseNote, ReleaseNote releaseNoteCurrent,  BindingResult result) {
		if (releaseNote.getSistema() == null) {
			result.addError(new ObjectError("Sistema", "Sistema não informado!"));
			return;
		}
		/*if (releaseNoteCurrent.getEstaAprovado() == True) {
			result.addError(new ObjectError("Nota de Release aprovada", "Nota de Release aprovado, não pode alterar dados!"));
			return;
		}*/
	}


}
