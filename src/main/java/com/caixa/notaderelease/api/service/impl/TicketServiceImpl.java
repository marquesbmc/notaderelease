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
	public Page<Ticket> findByUserCoordenacao(int page, int count, String userCoordenacao) {
		Pageable pages = new PageRequest(page, count);
		
		return this.ticketRepository.findByUserCoordenacaoOrderByDataAberturaDesc(pages, userCoordenacao);
		//return this.ticketRepository.findByUserCodigoOrderByDataAberturaDesc(pages, userCodigo);
	}


	
	
	@Override
	public Page<Ticket> findByStatusAndUsuarioCliente(int page, int count,String status,Long userCodigo) {
		Pageable pages = new PageRequest(page, count);
	
		return this.ticketRepository.findByStatusContainingAndUserCodigoOrderByDataAberturaDesc(status, userCodigo, pages );
		
	}

	@Override
	public Page<Ticket> findByNumeroNotaRelease(int page, int count, String numeroNotaRelease) {
		Pageable pages = new PageRequest(page, count);
		return this.ticketRepository.findByNumeroNotaRelease(numeroNotaRelease, pages);
	}

	@Override
	public Iterable<Ticket> findAll() {
		return this.ticketRepository.findAll();
	}

	@Override
	public Page<Ticket> findByNumeroNotaReleaseAndStatusAndUsuarioTecnicoAtribuido(int page, int count, String numeroNotaRelease, String status,
			Long assignedUserCodigo) {
		Pageable pages = new PageRequest(page, count);
		return this.ticketRepository.findByNumeroNotaReleaseIgnoreCaseContainingAndStatusContainingAndAssignedUserCodigoOrderByDataAberturaDesc(numeroNotaRelease, status, assignedUserCodigo, pages);
	}
	
	
	

	@Override
	public Page<Ticket> findByNumeroNotaReleaseAndStatus(int page, int count, String numeroNotaRelease, String status) {
		Pageable pages = new PageRequest(page, count);
		return this.ticketRepository.
				findByNumeroNotaReleaseIgnoreCaseContainingAndStatusContainingOrderByDataAberturaDesc(numeroNotaRelease, status, pages);
	}

	
	
	
}
