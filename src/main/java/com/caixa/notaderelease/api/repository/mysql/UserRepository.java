package com.caixa.notaderelease.api.repository.mysql;

import org.springframework.data.jpa.repository.JpaRepository;

import com.caixa.notaderelease.api.model.mysql.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByMatricula(String matricula);

	User findByCodigo(Long codigo);

	void delete(Long codigo);

}
