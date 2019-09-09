package com.caixa.notaderelease.api.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.caixa.notaderelease.api.model.ChangeStatus;
import com.caixa.notaderelease.api.model.ReleaseNotes;
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
		Pageable pages = new PageRequest(page,count, Sort.Direction.DESC,"codigo");
		return this.ticketRepository.findAll(pages);
	}
	//obs
	@Override
	public ChangeStatus createChangeStatus(ChangeStatus changeStatus) {
		return this.changeStatusRepository.save(changeStatus);
	}

	@Override
	public Iterable<ChangeStatus> listChangeStatus(Long codigo) {
		return this.changeStatusRepository.findByTicketCodigoOrderByDateChangeStatusAsc(codigo);
		//return this.changeStatusRepository.findByTicketCodigoOrderByDateChangeStatusDesc(ticketCodigo);
		//return this.changeStatusRepository.findByChangesStatusCodigoOrderByDateTicketCodigo(ticketCodigo);
	//return null;
		}

	@Override
	public Page<Ticket> findByCoordenacao(int page, int count, String coordenacao) {
		Pageable pages = new PageRequest(page,count, Sort.Direction.DESC,"codigo");
		
		return this.ticketRepository.findByCoordenacaoOrderByDataAberturaDesc(pages, coordenacao);
		//return this.ticketRepository.findByUserCodigoOrderByDataAberturaDesc(pages, userCodigo);
	}


	
	
	@Override
	public Page<Ticket> findByStatusAndUsuarioCliente(int page, int count,String status,Long userCodigo) {
		Pageable pages = new PageRequest(page,count, Sort.Direction.DESC,"codigo");
	
		return this.ticketRepository.findByStatusContainingAndUserCodigoOrderByDataAberturaDesc(status, userCodigo, pages );
		
	}

	@Override
	public Page<Ticket> findByNumeroNotaRelease(int page, int count, String numeroNotaRelease) {
		Pageable pages = new PageRequest(page,count, Sort.Direction.DESC,"codigo");
		return this.ticketRepository.findByNumeroNotaRelease(numeroNotaRelease, pages);
	}

	@Override
	public Iterable<Ticket> findAll() {
		return this.ticketRepository.findAll();
	}
	
	@Override
	public Page<Ticket> findAllPages(int page, int count) {
		Pageable pages = new PageRequest(page,count);
		return this.ticketRepository.findAll(pages);
	}
	

	@Override
	public Page<Ticket> findByNumeroNotaReleaseAndStatusAndUsuarioTecnicoAtribuido(int page, int count, String numeroNotaRelease, String status,
			Long assignedUserCodigo) {
		Pageable pages = new PageRequest(page,count, Sort.Direction.DESC,"codigo");
		return this.ticketRepository.findByNumeroNotaReleaseIgnoreCaseContainingAndStatusContainingAndAssignedUserCodigoOrderByDataAberturaDesc(numeroNotaRelease, status, assignedUserCodigo, pages);
	}
	
	
	

	@Override
	public Page<Ticket> findByNumeroNotaReleaseAndStatus(int page, int count, String numeroNotaRelease, String status) {
		Pageable pages = new PageRequest(page,count, Sort.Direction.DESC,"codigo");
		return this.ticketRepository.
				findByNumeroNotaReleaseIgnoreCaseContainingAndStatusContainingOrderByDataAberturaDesc(numeroNotaRelease, status, pages);
	}

	
	@Override
	public Long countByNumeroNotaRelease(ReleaseNotes numeroNotaRelease){
		return this.ticketRepository.countByNumeroNotaRelease(numeroNotaRelease);
		

	};
	
	@Override
	public Long count(){
		return this.ticketRepository.count();
		

	};
	
	
	
	@Override
	public Ticket findByNumeroNotaRelease(ReleaseNotes numeroNotaRelease){
		return this.ticketRepository.findByNumeroNotaRelease(numeroNotaRelease);
	};
	
	
	@Override
	public Page<Ticket> findByParam(int page, int count,String status,String sistema, String coordenacao, LocalDate dateini,LocalDate datefim,List<String> listsistema){
		Pageable pages = new PageRequest(page,count, Sort.Direction.DESC,"codigo");
		return this.ticketRepository.findByStatusContainingIgnoreCaseAndNumeroNotaReleaseNomeSistemaContainingIgnoreCaseAndCoordenacaoContainingIgnoreCaseAndDataAberturaBetweenAndNumeroNotaReleaseNomeSistemaInOrderByDataAberturaDesc( status,  sistema, coordenacao,  dateini, datefim, listsistema, pages);							 
	};
	
	
	@Override
	public Page<Ticket> findByParamCodigo(int page, int count,Long codigo,String status,String sistema, String coordenacao, LocalDate dateini,LocalDate datefim,List<String> listsistema){
		Pageable pages = new PageRequest(page,count, Sort.Direction.DESC,"codigo");
		return this.ticketRepository.findByNumeroNotaReleaseCodigoAndStatusContainingIgnoreCaseAndNumeroNotaReleaseNomeSistemaContainingIgnoreCaseAndCoordenacaoContainingIgnoreCaseAndDataAberturaBetweenAndNumeroNotaReleaseNomeSistemaInOrderByDataAberturaDesc(codigo, status, sistema, coordenacao, dateini, datefim, listsistema, pages);							 
	};
	
	@Override
	public Page<Ticket> findByParamUserTecnico(int page, int count,String status,String sistema, String coordenacao, LocalDate dateini,LocalDate datefim){
		Pageable pages = new PageRequest(page,count, Sort.Direction.DESC,"codigo");
		//return this.ticketRepository.findByStatusContainingIgnoreCaseAndAssignedUserMatriculaContainingIgnoreCaseAndNumeroNotaReleaseNomeSistemaContainingIgnoreCaseAndCoordenacaoContainingIgnoreCaseAndDataAberturaBetweenOrderByDataAberturaDesc(status, assignedusermatricula, sistema, coordenacao, dateini, datefim, pages);
		return this.ticketRepository.findByStatusContainingIgnoreCaseAndNumeroNotaReleaseNomeSistemaContainingIgnoreCaseAndCoordenacaoContainingIgnoreCaseAndDataAberturaBetweenOrderByDataAberturaDesc(status,  sistema, coordenacao, dateini, datefim, pages);
	};
	
	@Override
	public Page<Ticket> findByParamUserTecnicoCodigo(int page, int count,Long codigo, String status,String sistema, String coordenacao,LocalDate dateini,LocalDate datefim){
		Pageable pages = new PageRequest(page,count, Sort.Direction.DESC,"codigo");
		return this.ticketRepository.findByNumeroNotaReleaseCodigoAndStatusContainingIgnoreCaseAndNumeroNotaReleaseNomeSistemaContainingIgnoreCaseAndCoordenacaoContainingIgnoreCaseAndDataAberturaBetweenOrderByDataAberturaDesc(codigo,status,  sistema, coordenacao,  dateini, datefim, pages);					 
	};
	
	
	
}
