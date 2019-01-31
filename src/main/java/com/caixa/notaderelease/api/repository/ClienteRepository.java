package com.caixa.notaderelease.api.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.caixa.notaderelease.api.model.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	public Page<Cliente> findByNomeContaining(String nome, Pageable pageable);

}
