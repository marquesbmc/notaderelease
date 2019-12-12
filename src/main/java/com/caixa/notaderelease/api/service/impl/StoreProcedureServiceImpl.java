package com.caixa.notaderelease.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.caixa.notaderelease.api.repository.db2.StoreProcedureRepository;
import com.caixa.notaderelease.api.service.StoreProcedureService;



@Component
public class StoreProcedureServiceImpl  implements StoreProcedureService
{
	  @Autowired
	  private StoreProcedureRepository storeProcedureRepository;
	  
	  public List<String> buscarSP() { return this.storeProcedureRepository.consultaSP(); }
	}
