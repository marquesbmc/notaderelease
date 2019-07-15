package com.caixa.notaderelease.api.repository;


import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.caixa.notaderelease.api.model.ReleaseNotes;


public interface ReleaseNotesRepository extends JpaRepository<ReleaseNotes, Long> {
	
	ReleaseNotes findByCodigo(Long codigo);
	void delete(Long codigo);
	Page<ReleaseNotes> findByNomeSistemaIgnoreCaseContaining(String nomeSistema, Pageable pages);
	Page<ReleaseNotes> findByCodigoAndNomeSistemaIgnoreCaseContainingAndDataCriacaoAndVersaoCodigoFonteIgnoreCaseContainingAndStatusNrIgnoreCaseContaining(Long codigo, String nomeSistema,LocalDate dataCriacao,String versaoCodigoFonte,String statusNr, Pageable pages);
	
	
	Page<ReleaseNotes> findByCodigoAndNomeSistemaIgnoreCaseContainingAndVersaoCodigoFonteIgnoreCaseContainingAndStatusNrIgnoreCaseContaining(Long codigo, String nomeSistema,String versaoCodigoFonte,String statusNr , Pageable pages);
	Page<ReleaseNotes> findByDataCriacaoAndNomeSistemaIgnoreCaseContainingAndVersaoCodigoFonteIgnoreCaseContainingAndStatusNrIgnoreCaseContaining(LocalDate dataCriacao,  String nomeSistema,String versaoCodigoFonte, String statusNr,  Pageable pages);
	Page<ReleaseNotes> findByCodigoAndDataCriacaoAndNomeSistemaIgnoreCaseContainingAndVersaoCodigoFonteIgnoreCaseContainingAndStatusNrIgnoreCaseContaining(Long codigo, LocalDate dataCriacao,  String nomeSistema,String versaoCodigoFonte, String statusNr,  Pageable pages);
	Page<ReleaseNotes> findByNomeSistemaIgnoreCaseContainingAndVersaoCodigoFonteIgnoreCaseContainingAndStatusNrIgnoreCaseContaining( String nomeSistema,String versaoCodigoFonte, String statusNr,  Pageable pages);
	
	
	}