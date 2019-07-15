package com.caixa.notaderelease.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.caixa.notaderelease.api.model.SystemNotes;
import com.caixa.notaderelease.api.repository.SystemNotesRepository;
import com.caixa.notaderelease.api.service.SystemNotesService;

@Component
public class SystemNotesServiceImpl implements SystemNotesService{
	
	@Autowired
	private SystemNotesRepository systemNotesRepository;
	
	
	@Override
	public Page<SystemNotes> findAll(int page, int count) {
		Pageable pages = new PageRequest(page, count);
		return this.systemNotesRepository.findAll(pages);
	}
	
	


}
