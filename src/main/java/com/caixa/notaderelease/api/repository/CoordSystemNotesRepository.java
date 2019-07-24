package com.caixa.notaderelease.api.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.caixa.notaderelease.api.model.CoordSystemNotes;
import com.caixa.notaderelease.api.model.Ticket;


public interface CoordSystemNotesRepository extends JpaRepository<CoordSystemNotes, Long> {
	
	Page<CoordSystemNotes> findByCoordSystem(Pageable pages, String coordsystem);
	List<CoordSystemNotes> findByCoordSystem(String coordsystem);
	
	

}
