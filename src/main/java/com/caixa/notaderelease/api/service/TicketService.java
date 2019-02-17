package com.caixa.notaderelease.api.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.caixa.notaderelease.api.model.ChangeStatus;
import com.caixa.notaderelease.api.model.Ticket;

@Component
public interface TicketService {
	
	Ticket createOrUpdate(Ticket ticket);
	
	Ticket findByCodigo(Long codigo);
	
	void delete(Long codigo);
	
	Page<Ticket> listTicket(int page, int count);
	
	
	//obs
	ChangeStatus createChangeStatus(ChangeStatus changeStatus);
	
	Iterable<ChangeStatus> listChangeStatus(Long codigo);
	
	
	//pesquisar somente os tickekts do dele (ciente)
	Page<Ticket> findByCurrentUser(int page, int count, Long userCodigo);
	
	Page<Ticket> findByParameters(int page, int count,String numeroNotaRelease,String status);
	
	Page<Ticket> findByParametersAndCurrentUser(int page, int count,String numeroNotaRelease,String status,Long userCodigo);
	
	
	//pesquisar pelo numero da nota de release title = numero.
	Page<Ticket> findByNumber(int page, int count,String numeroNotaRelease);
	
	Iterable<Ticket> findAll();
	
	
	//pesquisar os titulos atribuido a ele o tecnico
	public Page<Ticket> findByParametersAndAssignedUser(int page, int count,String numeroNotaRelease,String status,Long assignedUserCodigo);

}
