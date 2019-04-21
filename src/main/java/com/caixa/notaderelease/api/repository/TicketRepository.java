package com.caixa.notaderelease.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import com.caixa.notaderelease.api.model.Ticket;

public interface TicketRepository  extends JpaRepository<Ticket, Long>{
	
	
	//nao esta sendo utilizada - trocando pela debaixo como teste
	Page<Ticket> findByUserCodigoOrderByDataAberturaDesc(Pageable pages,Long userCodigo);
	
	Page<Ticket> findByUserCoordenacaoOrderByDataAberturaDesc(Pageable pages,String userCoordenacao);
	
	Page<Ticket> findByNumeroNotaReleaseIgnoreCaseContainingAndStatusContainingOrderByDataAberturaDesc(
			String numeroNotaRelease,String status,Pageable pages);
	
	Page<Ticket> findByNumeroNotaReleaseIgnoreCaseContainingAndAssignedUserCodigoOrderByDataAberturaDesc(String numeroNotaRelease, Long userCodigo,Pageable pages);
	
	Page<Ticket> findByNumeroNotaReleaseIgnoreCaseContainingAndStatusIgnoreCaseContainingAndUserCodigoOrderByDataAberturaDesc(
			String numeroNotaRelease,String status,Long userCodigo, Pageable pages);
	
	
	Page<Ticket>  findByStatusContainingAndUserCodigoOrderByDataAberturaDesc(String status, Long userCodigo, Pageable pages);

	
	Page<Ticket> findByNumeroNotaRelease(String numeroNotaRelease,Pageable pages);
	
	
	Page<Ticket> findByNumeroNotaReleaseIgnoreCaseContainingAndStatusContainingAndAssignedUserCodigoOrderByDataAberturaDesc(
			String numeroNotaRelease,String status,Long assignedUserCodigo, Pageable pages);
	

	
	void delete(Long codigo);

	


}
