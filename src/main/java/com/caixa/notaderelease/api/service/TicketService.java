package com.caixa.notaderelease.api.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.caixa.notaderelease.api.model.mysql.ChangeStatus;
import com.caixa.notaderelease.api.model.mysql.ReleaseNotes;
import com.caixa.notaderelease.api.model.mysql.Ticket;

@Component
public interface TicketService {
	
	
	
	
	Ticket createOrUpdate(Ticket ticket);
	
	
	// @GetMapping(value = "/{codigo}")
	//@DeleteMapping(value = "/{codigo}")
	//@PutMapping(value = "/{codigo}/{status}")
	//@PutMapping()
	//@GetMapping(value = "/{codigo}")
	Ticket findByCodigo(Long codigo);
	
	//@DeleteMapping(value = "/{codigo}")
	void delete(Long codigo);
	
	//@GetMapping(value = "/{page}/{count}")
	Page<Ticket> listTicket(int page, int count);
	
	//@GetMapping(value = "/{page}/{count}")
	//Page<Ticket> findByCurrentUser(int page, int count, Long userCodigo);
	Page<Ticket> findByCoordenacao(int page, int count, String coordenacao);
	
	//@PutMapping(value = "/{codigo}/{status}")
	ChangeStatus createChangeStatus(ChangeStatus changeStatus);
	
	
	//@GetMapping(value = "/{codigo}")
	Iterable<ChangeStatus> listChangeStatus(Long codigo);
	
	
	public Page<Ticket> findAllPages(int page, int count);
	
	
	//@GetMapping(value = "/{page}/{count}")
	//@GetMapping(value = "/summary")
	Iterable<Ticket> findAll();
	//@GetMapping(value = "{page}/{count}/{numeroNotaRelease}/{status}/{assigned}")
	Page<Ticket> findByNumeroNotaRelease(int page, int count,String numeroNotaRelease);
	//@GetMapping(value = "{page}/{count}/{numeroNotaRelease}/{status}/{assigned}")
	public Page<Ticket> findByNumeroNotaReleaseAndStatusAndUsuarioTecnicoAtribuido(int page, int count,String numeroNotaRelease,String status,Long assignedUserCodigo);
	//@GetMapping(value = "{page}/{count}/{numeroNotaRelease}/{status}/{assigned}")
	Page<Ticket> findByNumeroNotaReleaseAndStatus(int page, int count,String numeroNotaRelease,String status);
	
	//@GetMapping(value = "{page}/{count}/{numeroNotaRelease}/{status}/{assigned}")
	Page<Ticket> findByStatusAndUsuarioCliente(int page, int count,String status,Long userCodigo);
	
	Long countByNumeroNotaRelease(ReleaseNotes numeroNotaRelease);
	
	
	Long count();
	
	Ticket findByNumeroNotaRelease(ReleaseNotes numeroNotaRelease);
	
	
	
	Page<Ticket> findByParam(int page, int count,String status,String sistema, String coordenacao, LocalDate dateini,LocalDate datefim,List<String> listsistema);
	Page<Ticket> findByParamCodigo(int page, int count,Long codigo,String status,String sistema, String coordenacao, LocalDate dateini,LocalDate datefim,List<String> listsistema);
	
	
	Page<Ticket> findByParamUserTecnico(int page, int count,String status,String sistema, String coordenacao, LocalDate dateini,LocalDate datefim);
	Page<Ticket> findByParamUserTecnicoCodigo(int page, int count,Long codigo, String status,String sistema, String coordenacao, LocalDate dateini,LocalDate datefim);
	
	

}
