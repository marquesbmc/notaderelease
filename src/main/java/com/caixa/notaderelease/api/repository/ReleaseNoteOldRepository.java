package com.caixa.notaderelease.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.caixa.notaderelease.api.model.ReleaseNoteOld;



public interface ReleaseNoteOldRepository extends JpaRepository<ReleaseNoteOld, Long>{
	
	ReleaseNoteOld findByCodigo(Long codigo);
	
	Page<ReleaseNoteOld> findByCodigoIgnoreCaseContainingAndNomeSistemaIgnoreCaseContainingAndVersaoCodigoFonteIgnoreCaseContainingAndVersaoCodigoCompilado(Long codigo, String nomesistema, String versaocodigofonte, String versaocodigocompilado, Pageable pages);
	
	void delete(Long codigo);

}
