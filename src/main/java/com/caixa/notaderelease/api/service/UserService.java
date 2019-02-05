package com.caixa.notaderelease.api.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.caixa.notaderelease.api.model.User;

@Component
public interface UserService {
	
	User findByMatricula(String matricula);
	
	User createOrUpdate(User user);
	
	User findByCodigo(String codigo);
	
	void delete(String codigo);
	
	Page<User> findAll(int page, int count);

}
