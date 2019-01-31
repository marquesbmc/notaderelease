package com.caixa.notaderelease.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.caixa.notaderelease.api.model.Modulo;

public interface ModuloRepository extends JpaRepository<Modulo, Long> {

	public Page<Modulo> findByNomeContaining(String nome, Pageable pageable);

}
