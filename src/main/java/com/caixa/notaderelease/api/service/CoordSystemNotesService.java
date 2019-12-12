package com.caixa.notaderelease.api.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.caixa.notaderelease.api.model.mysql.CoordSystemNotes;

@Component
public interface CoordSystemNotesService {
	
	Page<CoordSystemNotes> findAll(int page, int count);
	
	Page<CoordSystemNotes> findByCoordSystem(int page, int count, String coordsystem);
	
	
	List<CoordSystemNotes> findByCoordSystem(String coordsystem);
}
