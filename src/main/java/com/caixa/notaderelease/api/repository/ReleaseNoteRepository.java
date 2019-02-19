package com.caixa.notaderelease.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.caixa.notaderelease.api.model.ReleaseNote;



public interface ReleaseNoteRepository extends JpaRepository<ReleaseNote, Long>{
	
	ReleaseNote findByCodigo(Long codigo);
	
	Page<ReleaseNote> findByCodigoIgnoreCaseContainingAndNomeSistemaIgnoreCaseContainingAndVersaoCodigoFonteIgnoreCaseContainingAndVersaoCodigoCompilado(Long codigo, String nomesistema, String versaocodigofonte, String versaocodigocompilado, Pageable pages);
	
	void delete(Long codigo);

}
