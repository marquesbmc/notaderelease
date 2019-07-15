package com.caixa.notaderelease.api.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;


import com.caixa.notaderelease.api.model.SystemNotes;

@Component
public interface SystemNotesService {
	
	Page<SystemNotes> findAll(int page, int count);

}
