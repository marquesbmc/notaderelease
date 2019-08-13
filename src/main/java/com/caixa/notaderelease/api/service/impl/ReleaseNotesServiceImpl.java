
package com.caixa.notaderelease.api.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.caixa.notaderelease.api.model.ReleaseNotes;
import com.caixa.notaderelease.api.repository.ReleaseNotesRepository;
import com.caixa.notaderelease.api.service.ReleaseNotesService;



@Component
public class ReleaseNotesServiceImpl implements ReleaseNotesService {

	@Autowired
	private ReleaseNotesRepository releaseNotesRepository;
	
	@Override
	public ReleaseNotes update(ReleaseNotes releasenotes) {
		return this.releaseNotesRepository.saveAndFlush(releasenotes);
	}
	
	@Override
	public Page<ReleaseNotes>  findByNomeSistemaIn(int page, int count, List<String> nomesistema){
		//Pageable pages = new PageRequest(page, count);
		Pageable pages = new PageRequest(page,count, Sort.Direction.DESC,"codigo");
		return this.releaseNotesRepository.findByNomeSistemaIn(nomesistema, pages);
	}
	
	
	
	@Override
	public ReleaseNotes createOrUpdate(ReleaseNotes releasenotes) {

		return this.releaseNotesRepository.save(releasenotes);
	}

	@Override
	public ReleaseNotes findByCodigo(Long codigo) {
		return this.releaseNotesRepository.findOne(codigo);
	}

	@Override
	public void delete(Long codigo) {
		this.releaseNotesRepository.delete(codigo);
	}

	@Override
	public Page<ReleaseNotes> findAll(int page, int count) {
		
		Pageable pages = new PageRequest(page,count, Sort.Direction.DESC,"codigo");
		
		
		return this.releaseNotesRepository.findAll(pages);
	}

	

	@Override
	public Page<ReleaseNotes> findByCodigoAndNomeSistemaIgnoreCaseContainingAndVersaoCodigoFonteIgnoreCaseContainingAndStatusNrIgnoreCaseContaining(
			int page, int count, Long codigo, String nomeSistema, String versaoCodigoFonte, String statusNr) {
		Pageable pages = new PageRequest(page,count, Sort.Direction.DESC,"codigo");
		return this.releaseNotesRepository.findByCodigoAndNomeSistemaIgnoreCaseContainingAndVersaoCodigoFonteIgnoreCaseContainingAndStatusNrIgnoreCaseContaining(codigo, nomeSistema, versaoCodigoFonte, statusNr, pages);
	}

	@Override
	public Page<ReleaseNotes> findByDataCriacaoAndNomeSistemaIgnoreCaseContainingAndVersaoCodigoFonteIgnoreCaseContainingAndStatusNrIgnoreCaseContaining(
			int page, int count, LocalDate dataCriacao, String nomeSistema, String versaoCodigoFonte, String statusNr) {
		Pageable pages = new PageRequest(page,count, Sort.Direction.DESC,"codigo");
		return this.releaseNotesRepository.findByDataCriacaoAndNomeSistemaIgnoreCaseContainingAndVersaoCodigoFonteIgnoreCaseContainingAndStatusNrIgnoreCaseContaining(dataCriacao, nomeSistema, versaoCodigoFonte, statusNr, pages);
	}

	@Override
	public Page<ReleaseNotes> findByCodigoAndDataCriacaoAndNomeSistemaIgnoreCaseContainingAndVersaoCodigoFonteIgnoreCaseContainingAndStatusNrIgnoreCaseContaining(
			int page, int count, Long codigo, LocalDate dataCriacao, String nomeSistema, String versaoCodigoFonte,
			String statusNr) {
		Pageable pages = new PageRequest(page,count, Sort.Direction.DESC,"codigo");
		return this.releaseNotesRepository.findByCodigoAndDataCriacaoAndNomeSistemaIgnoreCaseContainingAndVersaoCodigoFonteIgnoreCaseContainingAndStatusNrIgnoreCaseContaining(codigo, dataCriacao, nomeSistema, versaoCodigoFonte, statusNr, pages);
	}

	@Override
	public Page<ReleaseNotes> findByNomeSistemaIgnoreCaseContainingAndVersaoCodigoFonteIgnoreCaseContainingAndStatusNrIgnoreCaseContaining(
			int page, int count, String nomeSistema, String versaoCodigoFonte, String statusNr) {
		Pageable pages = new PageRequest(page,count, Sort.Direction.DESC,"codigo");
		return this.releaseNotesRepository.findByNomeSistemaIgnoreCaseContainingAndVersaoCodigoFonteIgnoreCaseContainingAndStatusNrIgnoreCaseContaining(nomeSistema, versaoCodigoFonte, statusNr, pages);
	
	}

	@Override
	public Page<ReleaseNotes> findByParamCodigo( Long codigo, String nomeSistema,String statusNr, String versaoCodigoCompilado,String versaoCodigoFonte, LocalDate dateini,LocalDate datefim,List<String> listsistema, int page, int count){					
		Pageable pages = new PageRequest(page,count, Sort.Direction.DESC,"codigo");
		return this.releaseNotesRepository.findByCodigoAndNomeSistemaContainingIgnoreCaseAndStatusNrIgnoreCaseContainingAndVersaoCodigoCompiladoIgnoreCaseContainingAndVersaoCodigoFonteIgnoreCaseContainingAndDataCriacaoBetweenAndNomeSistemaInOrderByDataCriacaoDesc(codigo,nomeSistema, statusNr, versaoCodigoCompilado, versaoCodigoFonte, dateini, datefim, listsistema, pages);				
	}
	
	@Override
	public Page<ReleaseNotes> findByParam(String nomeSistema,String statusNr, String versaoCodigoCompilado,String versaoCodigoFonte, LocalDate dateini,LocalDate datefim,List<String> listsistema, int page, int count){					
		Pageable pages = new PageRequest(page,count, Sort.Direction.DESC,"codigo");
		return this.releaseNotesRepository.findByNomeSistemaContainingIgnoreCaseAndStatusNrIgnoreCaseContainingAndVersaoCodigoCompiladoIgnoreCaseContainingAndVersaoCodigoFonteIgnoreCaseContainingAndDataCriacaoBetweenAndNomeSistemaInOrderByDataCriacaoDesc(nomeSistema, statusNr, versaoCodigoCompilado, versaoCodigoFonte, dateini, datefim, listsistema, pages);				
	}
	
	@Override
	public Page<ReleaseNotes> findByParamCodigoTecnico( Long codigo, String nomeSistema,String statusNr, String versaoCodigoCompilado,String versaoCodigoFonte, LocalDate dateini,LocalDate datefim, int page, int count){					
		Pageable pages = new PageRequest(page,count, Sort.Direction.DESC,"codigo");
		return this.releaseNotesRepository.findAllByCodigoAndNomeSistemaContainingIgnoreCaseAndStatusNrIgnoreCaseContainingAndVersaoCodigoCompiladoIgnoreCaseContainingAndVersaoCodigoFonteIgnoreCaseContainingAndDataCriacaoBetweenOrderByDataCriacaoDesc(codigo,nomeSistema, statusNr, versaoCodigoCompilado, versaoCodigoFonte, dateini, datefim,  pages);			
	}
	
	@Override
	public Page<ReleaseNotes> findByParamTecnico(String nomeSistema,String statusNr, String versaoCodigoCompilado,String versaoCodigoFonte, LocalDate dateini,LocalDate datefim, int page, int count){					
		Pageable pages = new PageRequest(page,count, Sort.Direction.DESC,"codigo");
		return this.releaseNotesRepository.findAllByNomeSistemaContainingIgnoreCaseAndStatusNrIgnoreCaseContainingAndVersaoCodigoCompiladoIgnoreCaseContainingAndVersaoCodigoFonteIgnoreCaseContainingAndDataCriacaoBetweenOrderByDataCriacaoDesc(nomeSistema, statusNr, versaoCodigoCompilado, versaoCodigoFonte, dateini, datefim,  pages);				
	}

	
	
}
