package com.caixa.notaderelease.api.service;


import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.caixa.notaderelease.api.model.ReleaseNote;


@Component
public interface ReleaseNoteService {
	
	
	ReleaseNote createOrUpdate(ReleaseNote releasecode);
	
	ReleaseNote findByCodigo(Long codigo);
	
	void delete(Long codigo);
	


	Page<ReleaseNote> findAll(int page, int count);
	

}
