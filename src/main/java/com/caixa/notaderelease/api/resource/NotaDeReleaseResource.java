package com.caixa.notaderelease.api.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.caixa.notaderelease.api.event.RecursoCriadoEvent;
import com.caixa.notaderelease.api.model.NotaDeRelease;
import com.caixa.notaderelease.api.repository.NotaDeReleaseRepository;
import com.caixa.notaderelease.api.repository.filter.NotaDeReleaseFilter;
import com.caixa.notaderelease.api.repository.projection.ResumoNotaDeRelease;
import com.caixa.notaderelease.api.service.NotaDeReleaseService;

@RestController
@RequestMapping("/notasdereleases")
public class NotaDeReleaseResource {
	
	@Autowired
	private NotaDeReleaseRepository notadereleaseRepository;
	
	@Autowired
	private NotaDeReleaseService notadereleaseService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_NOTADERELEASE') and #oauth2.hasScope('read')")
	public Page<NotaDeRelease> pesquisar(NotaDeReleaseFilter notadereleaseFilter, Pageable pageable) {
		return notadereleaseRepository.filtrar(notadereleaseFilter, pageable);
	}
	
	@GetMapping(params = "resumo")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_NOTADERELEASE') and #oauth2.hasScope('read')")
	public Page<ResumoNotaDeRelease> resumir(NotaDeReleaseFilter notadereleaseFilter, Pageable pageable) {
		return notadereleaseRepository.resumir(notadereleaseFilter, pageable);
	}
	
	
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_NOTADERELEASE') and #oauth2.hasScope('read')")
	public ResponseEntity<NotaDeRelease> buscarPeloCodigo(@PathVariable Long codigo) {
		NotaDeRelease notaderelease = notadereleaseRepository.findOne(codigo);
		return notaderelease != null ? ResponseEntity.ok(notaderelease) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_NOTADERELEASE') and #oauth2.hasScope('write')")
	public ResponseEntity<NotaDeRelease> criar(@Valid @RequestBody NotaDeRelease notaderelease, HttpServletResponse response) {
		NotaDeRelease notadereleaseSalvo = notadereleaseService.salvar(notaderelease);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, notadereleaseSalvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(notadereleaseSalvo);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_NOTADERELEASE') and #oauth2.hasScope('write')")
	public void remover(@PathVariable Long codigo) {
		notadereleaseRepository.delete(codigo);
	}
	
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_NOTADERELEASE')")
	public ResponseEntity<NotaDeRelease> atualizar(@PathVariable Long codigo, @Valid @RequestBody NotaDeRelease notaderelease) {
		try {
			NotaDeRelease notadereleaseSalvo = notadereleaseService.atualizar(codigo, notaderelease);
			return ResponseEntity.ok(notadereleaseSalvo);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
	

}
