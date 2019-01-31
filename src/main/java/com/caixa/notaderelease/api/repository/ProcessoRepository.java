package com.caixa.notaderelease.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.caixa.notaderelease.api.model.Processo;

public interface ProcessoRepository extends JpaRepository<Processo, Long> {

	public Page<Processo> findByNomeContaining(String nome, Pageable pageable);

}
