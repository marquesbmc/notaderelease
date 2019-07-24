package com.caixa.notaderelease.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.caixa.notaderelease.api.model.CoordSystemNotes;
import com.caixa.notaderelease.api.repository.CoordSystemNotesRepository;
import com.caixa.notaderelease.api.service.CoordSystemNotesService;

@Component
public class CoordSystemNotesServiceImpl implements CoordSystemNotesService{
	
	@Autowired
	private CoordSystemNotesRepository coordsystemNotesRepository;
	
	
	@Override
	public Page<CoordSystemNotes> findAll(int page, int count) {
		Pageable pages = new PageRequest(page, count);
		return this.coordsystemNotesRepository.findAll(pages);
	}
	
	
	@Override
	public Page<CoordSystemNotes> findByCoordSystem(int page, int count, String coordsystem){
		Pageable pages = new PageRequest(page, count);
		return this.coordsystemNotesRepository.findByCoordSystem(pages, coordsystem);
	}
	
	
	@Override
	public List<CoordSystemNotes> findByCoordSystem(String coordsystem){
		return this.coordsystemNotesRepository.findByCoordSystem(coordsystem);
	}
	
	

}
