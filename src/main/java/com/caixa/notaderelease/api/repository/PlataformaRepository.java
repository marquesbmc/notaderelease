package com.caixa.notaderelease.api.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.caixa.notaderelease.api.model.Plataforma;
import com.caixa.notaderelease.api.model.Processo;

public interface PlataformaRepository extends JpaRepository<Plataforma, Long> {
	
	public Page<Plataforma> findByConfAmbienteContaining(String confAmbiente, Pageable pageable);

}