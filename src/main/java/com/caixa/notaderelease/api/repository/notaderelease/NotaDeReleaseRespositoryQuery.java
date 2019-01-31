package com.caixa.notaderelease.api.repository.notaderelease;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.caixa.notaderelease.api.model.NotaDeRelease;
import com.caixa.notaderelease.api.repository.filter.NotaDeReleaseFilter;
import com.caixa.notaderelease.api.repository.projection.ResumoNotaDeRelease;

public interface NotaDeReleaseRespositoryQuery {
	
	public Page<NotaDeRelease> filtrar(NotaDeReleaseFilter notaDeReleaseFilter, Pageable pageable);
	public Page<ResumoNotaDeRelease> resumir(NotaDeReleaseFilter notaDeReleaseFilter, Pageable pageable);

}
