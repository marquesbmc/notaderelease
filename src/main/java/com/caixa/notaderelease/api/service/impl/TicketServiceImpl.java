package com.caixa.notaderelease.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.caixa.notaderelease.api.enums.StatusEnum;
import com.caixa.notaderelease.api.model.ChangeStatus;
import com.caixa.notaderelease.api.model.Ticket;
import com.caixa.notaderelease.api.repository.ChangeStatusRepository;
import com.caixa.notaderelease.api.repository.TicketRepository;
import com.caixa.notaderelease.api.service.TicketService;

@Component
public class TicketServiceImpl implements TicketService{
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private ChangeStatusRepository changeStatusRepository;
	
	@Override
	public Ticket createOrUpdate(Ticket ticket) {
		return this.ticketRepository.save(ticket);
	}

	@Override
	public Ticket findByCodigo(Long codigo) {
		return this.ticketRepository.findOne(codigo);
	}

	@Override
	public void delete(Long codigo) {
		this.ticketRepository.delete(codigo);
		
	}

	@Override
	public Page<Ticket> listTicket(int page, int count) {
		Pageable pages = new PageRequest(page, count);
		return this.ticketRepository.findAll(pages);
	}
	//obs
	@Override
	public ChangeStatus createChangeStatus(ChangeStatus changeStatus) {
		return this.changeStatusRepository.save(changeStatus);
	}

	@Override
	public Iterable<ChangeStatus> listChangeStatus(Long codigo) {
		return this.changeStatusRepository.findByTicketCodigoOrderByDateChangeStatusDesc(codigo);
		//return this.changeStatusRepository.findByTicketCodigoOrderByDateChangeStatusDesc(ticketCodigo);
		//return this.changeStatusRepository.findByChangesStatusCodigoOrderByDateTicketCodigo(ticketCodigo);
	//return null;
		}

	@Override
	public Page<Ticket> findByCurrentUser(int page, int count, Long userCodigo) {
		Pageable pages = new PageRequest(page, count);
		return this.ticketRepository.findByUserCodigoOrderByDataAberturaDesc(pages, userCodigo);
	}

	@Override
	public Page<Ticket> findByParameters(int page, int count, String numeroNotaRelease, StatusEnum status) {
		Pageable pages = new PageRequest(page, count);
		return this.ticketRepository.
				findByNumeroNotaReleaseIgnoreCaseContainingAndStatusOrderByDataAberturaDesc(numeroNotaRelease, status, pages);
	}

	
	
	@Override
	public Page<Ticket> findByParametersAndCurrentUser(int page, int count,StatusEnum status,Long userCodigo) {
		Pageable pages = new PageRequest(page, count);
		return this.ticketRepository.findByStatusAndUserCodigoOrderByDataAberturaDesc(status, userCodigo, pages );
		
	}

	@Override
	public Page<Ticket> findByNumber(int page, int count, String numeroNotaRelease) {
		Pageable pages = new PageRequest(page, count);
		return this.ticketRepository.findByNumeroNotaRelease(numeroNotaRelease, pages);
	}

	@Override
	public Iterable<Ticket> findAll() {
		return this.ticketRepository.findAll();
	}

	@Override
	public Page<Ticket> findByParametersAndAssignedUser(int page, int count, String numeroNotaRelease, StatusEnum status,
			Long assignedUserCodigo) {
		Pageable pages = new PageRequest(page, count);
		return this.ticketRepository.findByNumeroNotaReleaseIgnoreCaseContainingAndStatusAndAssignedUserCodigoOrderByDataAberturaDesc(numeroNotaRelease, status, assignedUserCodigo, pages);
	}

}
