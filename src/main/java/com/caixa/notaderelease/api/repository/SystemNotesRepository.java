package com.caixa.notaderelease.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.caixa.notaderelease.api.model.SystemNotes;

public interface SystemNotesRepository extends JpaRepository<SystemNotes, Long> {

}
