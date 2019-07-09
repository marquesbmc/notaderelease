package com.caixa.notaderelease.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.caixa.notaderelease.api.model.ReleaseNoteOld;
import com.caixa.notaderelease.api.model.Ticket;
import com.caixa.notaderelease.api.repository.ReleaseNoteOldRepository;
import com.caixa.notaderelease.api.service.ReleaseNoteOldService;


@Component
public class ReleaseNoteOldServiceImpl implements ReleaseNoteOldService{
	
	@Autowired
	private ReleaseNoteOldRepository  releaseNoteRepository;

	@Override
	public ReleaseNoteOld createOrUpdate(ReleaseNoteOld releasecode) {
		return this.releaseNoteRepository.save(releasecode) ;
	}

	@Override
	public ReleaseNoteOld findByCodigo(Long codigo) {
		return this.releaseNoteRepository.findOne(codigo);
	}

	@Override
	public void delete(Long codigo) {
		 this.releaseNoteRepository.delete(codigo);
	}

	@Override
	public Page<ReleaseNoteOld> findAll(int page, int count) {
		Pageable pages = new PageRequest(page, count);
		return this.releaseNoteRepository.findAll(pages);
	}




	





}
