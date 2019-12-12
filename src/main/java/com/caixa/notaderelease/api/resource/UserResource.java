package com.caixa.notaderelease.api.resource;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
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

import com.caixa.notaderelease.api.model.mysql.User;
import com.caixa.notaderelease.api.response.Response;
import com.caixa.notaderelease.api.service.UserService;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "http://pefug.arquitetura.caixa:4200")
public class UserResource {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@PostMapping()
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<User>> create(HttpServletRequest request, @RequestBody User user,
			BindingResult result) {
		Response<User> response = new Response<User>();
		try {
			validateCreateUser(user, result);
			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			User userPersisted = (User) userService.createOrUpdate(user);
			response.setData(userPersisted);
		} catch (DuplicateKeyException dE) {
			response.getErrors().add("Matricula registrada!");
			return ResponseEntity.badRequest().body(response);
		} catch (Exception e) {
			
			
			if ( e.getCause().toString().equals("org.hibernate.exception.ConstraintViolationException: could not execute statement") ) {
				response.getErrors().add("Erro! Matrícula cadastrada anteriormente.");
				} else {
				response.getErrors().add(e.getMessage());
				}
				
				
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}
	
	private void validateCreateUser(User user, BindingResult result) {
		if (user.getMatricula() == null) {
			result.addError(new ObjectError("User", "Matricula não informada!"));
			return;
		}
	}
	
	@PutMapping()
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<User>> update(HttpServletRequest request, @RequestBody User user,
			BindingResult result) {
		Response<User> response = new Response<User>();
		try {
			validateUpdate(user, result);
			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			User userPersisted = (User) userService.createOrUpdate(user);
			response.setData(userPersisted);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}
	
	private void validateUpdate(User user, BindingResult result) {
		if (user.getCodigo() == null) {
			result.addError(new ObjectError("User", "Codigo não informado"));
			return;
		}
		if (user.getMatricula() == null) {
			result.addError(new ObjectError("User", "Matricula não informada!"));
			return;
		}	
		
	}
	
	
	@PutMapping(value = "/{senha}")
	public ResponseEntity<Response<User>> updatepassword(HttpServletRequest request,@RequestBody User user,  @PathVariable("senha") String senha,
			BindingResult result) {
		Response<User> response = new Response<User>();
	
		try {
			validatePassword(user,senha, result);
			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			User userData = (User) userService.findByCodigo(user.getCodigo());
			
			user.setProfile(userData.getProfile());
			
			user.setPassword(passwordEncoder.encode(senha));
			User userPersisted = (User) userService.createOrUpdate(user);
			response.setData(userPersisted);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}
	
	
	private void validatePassword(User user, String senha, BindingResult result) {
		
		User userData = (User) userService.findByCodigo(user.getCodigo());
		
		if(!passwordEncoder.matches(user.getPassword(), userData.getPassword())) {
			result.addError(new ObjectError("User", "Senha não confere, Alteração cancelada!"));
			return;
		}
	}
	
	
	
	
	@GetMapping(value = "{codigo}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<User>> findByCodigo(@PathVariable("codigo") Long codigo) {
		Response<User> response = new Response<User>();
		User user = userService.findByCodigo(codigo);
		if (user == null) {
			response.getErrors().add("Register not found codigo:" + codigo);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(user);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping(value = "/{codigo}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<String>> delete(@PathVariable("codigo") Long codigo) {
		Response<String> response = new Response<String>();
		User user = userService.findByCodigo(codigo);
		if (user == null) {
			response.getErrors().add("Register not found id:" + codigo);
			return ResponseEntity.badRequest().body(response);
		}
		userService.delete(codigo);
		return ResponseEntity.ok(new Response<String>());
	}
	
	
	@GetMapping(value = "{page}/{count}")
	@PreAuthorize("hasAnyRole('ADMIN')")
    public  ResponseEntity<Response<Page<User>>> findAll(@PathVariable int page, @PathVariable int count) {
		Response<Page<User>> response = new Response<Page<User>>();
		Page<User> users = userService.findAll(page, count);
		response.setData(users);
		return ResponseEntity.ok(response);
    }

}
