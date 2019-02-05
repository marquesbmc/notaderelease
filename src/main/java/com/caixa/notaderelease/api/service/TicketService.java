package com.caixa.notaderelease.api.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.caixa.notaderelease.api.model.ChangeStatus;
import com.caixa.notaderelease.api.model.Ticket;

@Component
public interface TicketService {
	
	Ticket createOrUpdate(Ticket ticket);
	
	Ticket findByCodigo(String codigo);
	
	void delete(String codigo);;
	
	Page<Ticket> listTicket(int page, int count);
	
	ChangeStatus createChangeStatus(ChangeStatus changeStatus);
	
	Iterable<ChangeStatus> listChangeStatus(String ticketId);
	
	Page<Ticket> findByCurrentUser(int page, int count, String codigo);
	
	Page<Ticket> findByParameters(int page, int count,String numeroNotaRelease,String status);
	
	Page<Ticket> findByParametersAndCurrentUser(int page, int count,String numeroNotaRelease,String status,String userCodigo);
	
	Page<Ticket> findByCodigo(int page, int count,Integer codigo);
	
	Iterable<Ticket> findAll();
	
	public Page<Ticket> findByParametersAndAssignedUser(int page, int count,String numeroNotaRelease,String status,String assignedUserCodigo);
	
	

}
