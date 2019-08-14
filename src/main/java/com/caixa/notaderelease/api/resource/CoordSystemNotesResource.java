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

import com.caixa.notaderelease.api.enums.ProfileEnum;
import com.caixa.notaderelease.api.model.CoordSystemNotes;
import com.caixa.notaderelease.api.model.User;
import com.caixa.notaderelease.api.response.Response;
import com.caixa.notaderelease.api.security.jwt.JwtTokenUtil;
import com.caixa.notaderelease.api.service.CoordSystemNotesService;
import com.caixa.notaderelease.api.service.UserService;

@RestController
@RequestMapping("/api/coordsystemnotes")
@CrossOrigin(origins = "*")
public class CoordSystemNotesResource {
	
	@Autowired
	private CoordSystemNotesService systemNotesService;

	@Autowired
	protected JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping(value = "/{page}/{count}")
	@PreAuthorize("hasAnyRole('CUSTOMER','ADMIN','TECHNICIAN')")
	public ResponseEntity<Response<Page<CoordSystemNotes>>> findAll(HttpServletRequest request, @PathVariable int page,
			@PathVariable int count) {
		
		User userRequest = userFromRequest(request);
		Response<Page<CoordSystemNotes>> response = new Response<Page<CoordSystemNotes>>();
		Page<CoordSystemNotes> coordsystemNotes = null;
		
		if (userRequest.getProfile().equals(ProfileEnum.ROLE_CUSTOMER)) {
			coordsystemNotes = systemNotesService.findByCoordSystem(page, count, userRequest.getCoordenacao());
		}else if (userRequest.getProfile().equals(ProfileEnum.ROLE_TECHNICIAN) || userRequest.getProfile().equals(ProfileEnum.ROLE_ADMIN)) {
			coordsystemNotes = systemNotesService.findAll(page, count);
		}
		
		
		
		
		response.setData(coordsystemNotes);
		return ResponseEntity.ok(response);
	}


	
	
	private User userFromRequest(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		String matricula = jwtTokenUtil.getUsernameFromToken(token);
		return userService.findByMatricula(matricula);
	}

}
