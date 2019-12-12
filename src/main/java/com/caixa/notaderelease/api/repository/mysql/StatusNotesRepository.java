package com.caixa.notaderelease.api.repository.mysql;

import org.springframework.data.jpa.repository.JpaRepository;

import com.caixa.notaderelease.api.model.mysql.StatusNotes;

public interface StatusNotesRepository  extends JpaRepository<StatusNotes, Long> {

	StatusNotes findByCodigo(Long codigo);

}
