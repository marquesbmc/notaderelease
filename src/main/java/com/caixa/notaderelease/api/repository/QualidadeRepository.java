package com.caixa.notaderelease.api.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import com.caixa.notaderelease.api.model.Qualidade;

public interface QualidadeRepository extends JpaRepository<Qualidade, Long> {
	
	public Page<Qualidade> findByDataCapturaContaining(LocalDate dataCaptura, Pageable pageable);

}
