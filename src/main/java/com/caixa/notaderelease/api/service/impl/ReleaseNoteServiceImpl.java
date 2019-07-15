package com.caixa.notaderelease.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.caixa.notaderelease.api.model.ReleaseNote;
import com.caixa.notaderelease.api.repository.ReleaseNoteRepository;
import com.caixa.notaderelease.api.service.ReleaseNoteService;

@Component
public class ReleaseNoteServiceImpl implements ReleaseNoteService{
	
	@Autowired
	private ReleaseNoteRepository releaseNoteRepository;

	@Override
	public ReleaseNote createOrUpdate(ReleaseNote releasecode) {
		return this.releaseNoteRepository.save(releasecode);
	}

	@Override
	public ReleaseNote findByCodigo(Long codigo) {
		return this.releaseNoteRepository.findOne(codigo);
	}

	@Override
	public void delete(Long codigo) {
		 this.releaseNoteRepository.delete(codigo);
		
	}

	@Override
	public Page<ReleaseNote> findAll(int page, int cont) {
		Pageable pages = new PageRequest(page, cont);
		return this.releaseNoteRepository.findAll(pages);
	}

}
