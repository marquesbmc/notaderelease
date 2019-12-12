package com.caixa.notaderelease.api.service;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public interface StoreProcedureService {
	
	List<String> buscarSP();

}
