package com.caixa.notaderelease.api.repository;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.caixa.notaderelease.api.model.ReleaseNotes;



public interface ReleaseNotesRepository extends JpaRepository<ReleaseNotes, Long> {
	
	ReleaseNotes findByCodigo(Long codigo);
	void delete(Long codigo);
	
	
	Page<ReleaseNotes>  findByNomeSistemaIn(List<String> nomesistema, Pageable pages);
	//findByAgeIn(Collection<Age> ages)
	
	Page<ReleaseNotes> findByCodigoAndNomeSistemaIgnoreCaseContainingAndDataCriacaoAndVersaoCodigoFonteIgnoreCaseContainingAndStatusNrIgnoreCaseContaining(Long codigo, String nomeSistema,LocalDate dataCriacao,String versaoCodigoFonte,String statusNr, Pageable pages);
	
	
	Page<ReleaseNotes> findByCodigoAndNomeSistemaIgnoreCaseContainingAndVersaoCodigoFonteIgnoreCaseContainingAndStatusNrIgnoreCaseContaining(Long codigo, String nomeSistema,String versaoCodigoFonte,String statusNr , Pageable pages);
	Page<ReleaseNotes> findByDataCriacaoAndNomeSistemaIgnoreCaseContainingAndVersaoCodigoFonteIgnoreCaseContainingAndStatusNrIgnoreCaseContaining(LocalDate dataCriacao,  String nomeSistema,String versaoCodigoFonte, String statusNr,  Pageable pages);
	
	
	Page<ReleaseNotes> findByCodigoAndDataCriacaoAndNomeSistemaIgnoreCaseContainingAndVersaoCodigoFonteIgnoreCaseContainingAndStatusNrIgnoreCaseContaining(Long codigo, LocalDate dataCriacao,  String nomeSistema,String versaoCodigoFonte, String statusNr,  Pageable pages);
	Page<ReleaseNotes> findByNomeSistemaIgnoreCaseContainingAndVersaoCodigoFonteIgnoreCaseContainingAndStatusNrIgnoreCaseContaining( String nomeSistema,String versaoCodigoFonte, String statusNr,  Pageable pages);
	
	
	
	//Listar Nota de Release - Cliente
	Page<ReleaseNotes> findByNomeSistemaContainingIgnoreCaseAndStatusNrIgnoreCaseContainingAndVersaoCodigoCompiladoIgnoreCaseContainingAndVersaoCodigoFonteIgnoreCaseContainingAndDataCriacaoBetweenAndNomeSistemaInOrderByDataCriacaoDesc( String nomeSistema,String statusNr, String versaoCodigoCompilado,String versaoCodigoFonte, LocalDate dateini,LocalDate datefim,List<String> listsistema, Pageable pages);
	Page<ReleaseNotes> findByCodigoAndNomeSistemaContainingIgnoreCaseAndStatusNrIgnoreCaseContainingAndVersaoCodigoCompiladoIgnoreCaseContainingAndVersaoCodigoFonteIgnoreCaseContainingAndDataCriacaoBetweenAndNomeSistemaInOrderByDataCriacaoDesc( Long codigo,String nomeSistema,String statusNr, String versaoCodigoCompilado,String versaoCodigoFonte, LocalDate dateini,LocalDate datefim,List<String> listsistema, Pageable pages);
	
	//Listar Nota de Release - Tecnico
	Page<ReleaseNotes> findAllByNomeSistemaContainingIgnoreCaseAndStatusNrIgnoreCaseContainingAndVersaoCodigoCompiladoIgnoreCaseContainingAndVersaoCodigoFonteIgnoreCaseContainingAndDataCriacaoBetweenOrderByDataCriacaoDesc( String nomeSistema,String statusNr, String versaoCodigoCompilado,String versaoCodigoFonte, LocalDate dateini,LocalDate datefim, Pageable pages);
	Page<ReleaseNotes> findAllByCodigoAndNomeSistemaContainingIgnoreCaseAndStatusNrIgnoreCaseContainingAndVersaoCodigoCompiladoIgnoreCaseContainingAndVersaoCodigoFonteIgnoreCaseContainingAndDataCriacaoBetweenOrderByDataCriacaoDesc( Long codigo,String nomeSistema,String statusNr, String versaoCodigoCompilado,String versaoCodigoFonte, LocalDate dateini,LocalDate datefim, Pageable pages);
	
	}