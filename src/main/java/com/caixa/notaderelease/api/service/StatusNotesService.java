package com.caixa.notaderelease.api.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;


import com.caixa.notaderelease.api.model.StatusNotes;

@Component
public interface StatusNotesService {
	
	Page<StatusNotes> findAll(int page, int count);

}
