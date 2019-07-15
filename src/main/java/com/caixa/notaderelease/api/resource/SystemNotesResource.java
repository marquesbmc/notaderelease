package com.caixa.notaderelease.api.resource;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caixa.notaderelease.api.model.StatusNotes;
import com.caixa.notaderelease.api.model.SystemNotes;
import com.caixa.notaderelease.api.response.Response;
import com.caixa.notaderelease.api.security.jwt.JwtTokenUtil;
import com.caixa.notaderelease.api.service.StatusNotesService;
import com.caixa.notaderelease.api.service.SystemNotesService;

@RestController
@RequestMapping("/api/systemnotes")
@CrossOrigin(origins = "*")
public class SystemNotesResource {
	
	@Autowired
	private SystemNotesService systemNotesService;

	@Autowired
	protected JwtTokenUtil jwtTokenUtil;
	
	
	@GetMapping(value = "/{page}/{count}")
	@PreAuthorize("hasAnyRole('CUSTOMER','TECHNICIAN')")
	public ResponseEntity<Response<Page<SystemNotes>>> findAll(HttpServletRequest request, @PathVariable int page,
			@PathVariable int count) {

		Response<Page<SystemNotes>> response = new Response<Page<SystemNotes>>();
		Page<SystemNotes> systemNotes = null;
		systemNotes = systemNotesService.findAll(page, count);
		response.setData(systemNotes);
		return ResponseEntity.ok(response);
	}

}
