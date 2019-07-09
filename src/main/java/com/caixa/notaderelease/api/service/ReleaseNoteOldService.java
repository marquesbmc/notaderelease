package com.caixa.notaderelease.api.service;


import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.caixa.notaderelease.api.model.ReleaseNoteOld;


@Component
public interface ReleaseNoteOldService {
	
	
	ReleaseNoteOld createOrUpdate(ReleaseNoteOld releasecode);
	
	ReleaseNoteOld findByCodigo(Long codigo);
	
	void delete(Long codigo);
	
	Page<ReleaseNoteOld> findAll(int page, int count);

}
