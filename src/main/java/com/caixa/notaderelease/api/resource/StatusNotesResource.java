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

import com.caixa.notaderelease.api.model.ReleaseNotes;
import com.caixa.notaderelease.api.model.StatusNotes;
import com.caixa.notaderelease.api.response.Response;
import com.caixa.notaderelease.api.security.jwt.JwtTokenUtil;
import com.caixa.notaderelease.api.service.ReleaseNotesService;
import com.caixa.notaderelease.api.service.StatusNotesService;


@RestController
@RequestMapping("/api/statusnotes")
@CrossOrigin(origins = "*")
public class StatusNotesResource {
	
	
	@Autowired
	private StatusNotesService statusNotesService;

	@Autowired
	protected JwtTokenUtil jwtTokenUtil;
	
	
	@GetMapping(value = "/{page}/{count}")
	@PreAuthorize("hasAnyRole('CUSTOMER','TECHNICIAN')")
	public ResponseEntity<Response<Page<StatusNotes>>> findAll(HttpServletRequest request, @PathVariable int page,
			@PathVariable int count) {

		Response<Page<StatusNotes>> response = new Response<Page<StatusNotes>>();
		Page<StatusNotes> statusNotes = null;
		statusNotes = statusNotesService.findAll(page, count);
		response.setData(statusNotes);
		return ResponseEntity.ok(response);
	}
	
	


}
