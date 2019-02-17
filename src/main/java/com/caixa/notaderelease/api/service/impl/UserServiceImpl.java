package com.caixa.notaderelease.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.caixa.notaderelease.api.model.User;
import com.caixa.notaderelease.api.repository.UserRepository;
import com.caixa.notaderelease.api.service.UserService;

@Component
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User findByMatricula(String matricula) {
		return this.userRepository.findByMatricula(matricula);
	}

	@Override
	public User createOrUpdate(User user) {
		return this.userRepository.save(user);
	}

	@Override
	public User findByCodigo(Long codigo) {
		return this.userRepository.findByCodigo(codigo);
	}

	@Override
	public void delete(Long codigo) {
		this.userRepository.delete(codigo);
		
	}

	@Override
	public Page<User> findAll(int page, int count) {
		Pageable pages = new PageRequest(page, count);
		return this.userRepository.findAll(pages);
	}
	
	

}
