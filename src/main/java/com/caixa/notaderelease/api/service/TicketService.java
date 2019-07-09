package com.caixa.notaderelease.api.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.caixa.notaderelease.api.enums.StatusEnum;
import com.caixa.notaderelease.api.model.ChangeStatus;
import com.caixa.notaderelease.api.model.Ticket;

@Component
public interface TicketService {
	
	//@PutMapping(value = "/{codigo}/{status}")
	//@PostMapping()
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
	Page<Ticket> findByUserCoordenacao(int page, int count, String userCoordenacao);
	
	//@PutMapping(value = "/{codigo}/{status}")
	ChangeStatus createChangeStatus(ChangeStatus changeStatus);
	
	
	//@GetMapping(value = "/{codigo}")
	Iterable<ChangeStatus> listChangeStatus(Long codigo);
	
	
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

}
