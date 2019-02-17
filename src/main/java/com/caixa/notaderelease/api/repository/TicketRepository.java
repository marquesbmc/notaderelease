package com.caixa.notaderelease.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.caixa.notaderelease.api.model.Ticket;

public interface TicketRepository  extends JpaRepository<Ticket, Long>{
	
	Page<Ticket> findByUserCodigoOrderByDataAberturaDesc(Pageable pages,Long userCodigo);
	
	Page<Ticket> findByNumeroNotaReleaseIgnoreCaseContainingAndStatusIgnoreCaseContainingOrderByDataAberturaDesc(
			String numeroNotaRelease,String status,Pageable pages);
	
	Page<Ticket> findByNumeroNotaReleaseIgnoreCaseContainingAndStatusIgnoreCaseContainingAndUserCodigoOrderByDataAberturaDesc(
			String numeroNotaRelease,String status,Long userCodigo, Pageable pages);
	
	Page<Ticket> findByNumeroNotaRelease(String numeroNotaRelease,Pageable pages);
	
	
	Page<Ticket> findByNumeroNotaReleaseIgnoreCaseContainingAndStatusIgnoreCaseContainingAndAssignedUserCodigoOrderByDataAberturaDesc(
			String numeroNotaRelease,String status,Long assignedUserCodigo, Pageable pages);

	void delete(Long codigo);

	


}
