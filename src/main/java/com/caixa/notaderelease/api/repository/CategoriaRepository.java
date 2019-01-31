package com.caixa.notaderelease.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.caixa.notaderelease.api.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
