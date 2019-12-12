package com.caixa.notaderelease.api.resource;


import com.caixa.notaderelease.api.model.db2.StoreProcedure;
import com.caixa.notaderelease.api.resource.StoreprocedureController;
import com.caixa.notaderelease.api.response.Response;
import com.caixa.notaderelease.api.service.StoreProcedureService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping({"/api/sp"})
public class StoreprocedureController {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	  
	  @Autowired
	  private StoreProcedureService storeProcedureService;
	  
	  @GetMapping({"/getall"})
	  public List<String> getAll() {
	    List<String> retorno = null;
	    String sp = "sit,FUGSPAAA_CALL_SP_BBB,FUGSPAAJ_ASSOCIAR_RUBRICA_DOCUMENTO,FUGSPAAR_LISTAR_NATUREZA_PECUNIARIA,sit,amet";
	    List<String> spListdivergente = new ArrayList<>();
	    List<String> spListIguais = new ArrayList<>();

	    if (sp.contains("?") || sp.contains(";") || sp.contains("/") || sp.contains("*")) {
	      retorno.add("Contém caracteres fora do padrão");
	    } else {
	      List<String> spList = new ArrayList<>(Arrays.asList(sp.split(",")));
	      List<String> spListTQS = this.storeProcedureService.buscarSP();
	      
	      spListIguais = spList;
	      
	      for (String spItem : spList) {
	        spListIguais.remove(spItem);
	      }
	      
	      if (!spListIguais.isEmpty()) {
	        for (String spItem : spList) {
	          if (!spListTQS.contains(spItem)) {
	            spListdivergente.add(spItem);
	          }
	        } 
	      }
	    } 
	    
	    return spListIguais;
	  }

	  
	  @PostMapping
	  public ResponseEntity<Response<StoreProcedure>> create(@RequestBody StoreProcedure storeProcedure, BindingResult result) {
	    Response<StoreProcedure> response = new Response();

	    
	    try {
	      validateCreateStoreProcedure(storeProcedure, result);
	      if (result.hasErrors()) {
	        result.getAllErrors().forEach(error -> { 
	            }); return ResponseEntity.badRequest().body(response);
	      } 
	      StoreProcedure spAnalizada = storeProcedure;
	      
	      spAnalizada.setStatus(ValidarSP(storeProcedure));
	      response.setData(spAnalizada);
	    }
	    catch (Exception e) {
	      response.getErrors().add(e.getMessage());
	      return ResponseEntity.badRequest().body(response);
	    } 
	    
	    return ResponseEntity.ok(response);
	  }
	  
	  private String ValidarSP(StoreProcedure storeProcedure) {
	    String result = null;
	    List<String> spListdivergente = new ArrayList<>();
	    List<String> spListIguais = new ArrayList<>();

	    
	    List<String> spList = new ArrayList<>(Arrays.asList(storeProcedure.getNome().split("\n")));
	    List<String> spListTQS = this.storeProcedureService.buscarSP();

	    
	    for (int i = 0; i < spList.size(); i++) {
	      for (int j = i + 1; j < spList.size(); j++) {
	        if (((String)spList.get(i)).equals(spList.get(j))) {
	          spListIguais.add(spList.get(i));
	        }
	      } 
	    } 
	    
	    if (spListIguais.isEmpty()) {
	      for (String spItem : spList) {
	        if (!spListTQS.contains(spItem)) {
	          spListdivergente.add(spItem);
	        }
	      } 
	    }
	    
	    if (!spListIguais.isEmpty()) {
	      result = "SP repetida! \n";
	      for (String spr : spListIguais) {
	        result = String.valueOf(result) + spr + "\r\n";
	      }
	    } else if (!spListdivergente.isEmpty()) {
	      result = "SP inexistente no catalogo DB2! \n";
	      for (String spr : spListdivergente) {
	        result = String.valueOf(result) + spr + "\r\n";
	      }
	    } 

	    
	    return result;
	  }
	  
	  private void validateCreateStoreProcedure(StoreProcedure storeProcedure, BindingResult result) {
	    if (storeProcedure.getNome() == null) {
	      result.addError(new ObjectError("Lista de StoreProcedure vazia", "Favor informar"));
	      
	      return;
	    } 
	    if (storeProcedure.getNome().contains("?") || storeProcedure.getNome().contains(";") || storeProcedure.getNome().contains(",") || storeProcedure.getNome().contains("-") || storeProcedure.getNome().contains("/") || storeProcedure.getNome().contains("*")) {
	      result.addError(new ObjectError("SP contém caracter inválido, ", "SP contcaracter inválido!"));
	      return;
	    } 
	  }
	}
