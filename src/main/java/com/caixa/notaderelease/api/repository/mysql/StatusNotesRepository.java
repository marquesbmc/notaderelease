package com.caixa.notaderelease.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.caixa.notaderelease.api.model.StatusNotes;

public interface StatusNotesRepository  extends JpaRepository<StatusNotes, Long> {

	StatusNotes findByCodigo(Long codigo);

}
