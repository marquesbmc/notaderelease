package com.caixa.notaderelease.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.caixa.notaderelease.api.model.mysql.StatusNotes;
import com.caixa.notaderelease.api.repository.mysql.StatusNotesRepository;
import com.caixa.notaderelease.api.service.StatusNotesService;

@Component
public class StatusNotesServiceImpl implements StatusNotesService{
	
	@Autowired
	private StatusNotesRepository statusNotesRepository;

	@Override
	public Page<StatusNotes> findAll(int page, int count) {
		Pageable pages = new PageRequest(page, count);
		return this.statusNotesRepository.findAll(pages);
	}

}
