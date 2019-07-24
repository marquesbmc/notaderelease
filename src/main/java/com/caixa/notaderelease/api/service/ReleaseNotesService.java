
package com.caixa.notaderelease.api.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.caixa.notaderelease.api.model.CoordSystemNotes;
import com.caixa.notaderelease.api.model.ReleaseNotes;
import com.caixa.notaderelease.api.model.Ticket;




@Component
public interface ReleaseNotesService {
	
	ReleaseNotes createOrUpdate(ReleaseNotes releasecode);
	ReleaseNotes findByCodigo(Long codigo);
	void delete(Long codigo);
	ReleaseNotes update(ReleaseNotes releasenotes);
	Page<ReleaseNotes> findAll(int page, int count);
	
	
	
	public Page<ReleaseNotes>  findByNomeSistemaIn(int page, int count, List<String> nomesistema);
	public Page<ReleaseNotes> findByCodigoAndNomeSistemaIgnoreCaseContainingAndVersaoCodigoFonteIgnoreCaseContainingAndStatusNrIgnoreCaseContaining(int page, int count, Long codigo, String nomeSistema,String versaoCodigoFonte,String statusNr);
	public Page<ReleaseNotes> findByDataCriacaoAndNomeSistemaIgnoreCaseContainingAndVersaoCodigoFonteIgnoreCaseContainingAndStatusNrIgnoreCaseContaining(int page, int count, LocalDate dataCriacao,  String nomeSistema,String versaoCodigoFonte,String statusNr);
	public Page<ReleaseNotes> findByCodigoAndDataCriacaoAndNomeSistemaIgnoreCaseContainingAndVersaoCodigoFonteIgnoreCaseContainingAndStatusNrIgnoreCaseContaining(int page, int count, Long codigo, LocalDate dataCriacao,  String nomeSistema,String versaoCodigoFonte, String statusNr);
	public Page<ReleaseNotes> findByNomeSistemaIgnoreCaseContainingAndVersaoCodigoFonteIgnoreCaseContainingAndStatusNrIgnoreCaseContaining(int page, int count, String nomeSistema,String versaoCodigoFonte, String statusNr);
	
}