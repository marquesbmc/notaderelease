package com.caixa.notaderelease.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.caixa.notaderelease.api.model.User;

public interface UserRepository extends JpaRepository<User, String> {
	
	User findByMatricula(String matricula);

}
