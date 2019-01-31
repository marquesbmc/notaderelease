package com.caixa.notaderelease.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.caixa.notaderelease.api.model.NotaDeRelease;
import com.caixa.notaderelease.api.repository.notaderelease.NotaDeReleaseRespositoryQuery;


public interface NotaDeReleaseRepository extends JpaRepository<NotaDeRelease, Long>, NotaDeReleaseRespositoryQuery{

}
