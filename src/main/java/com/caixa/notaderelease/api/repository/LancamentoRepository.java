package com.caixa.notaderelease.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.caixa.notaderelease.api.model.Lancamento;
import com.caixa.notaderelease.api.repository.lancamento.LancamentoRepositoryQuery;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery {

}
