package com.caixa.notaderelease.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.caixa.notaderelease.api.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, String>{
	
	Page<Ticket> findByUserCodigoOrderByDataAberturaDesc(Pageable pages,String userCodigo);
	
	Page<Ticket> findByNumeroNotaReleaseIgnoreCaseContainingAndStatusIgnoreCaseContainingOrderByDataAberturaDesc(
			String numeroNotaRelease,String status,Pageable pages);
	
	Page<Ticket> findByNumeroNotaReleaseIgnoreCaseContainingAndStatusIgnoreCaseContainingAndUserCodigoOrderByDataAberturaDesc(
			String numeroNotaRelease,String status,String userCodigo, Pageable pages);
	
	Page<Ticket> findByCodigo(Integer codigo,Pageable pages);
	
	
	Page<Ticket> findByNumeroNotaReleaseIgnoreCaseContainingAndStatusIgnoreCaseContainingAndAssignedUserOrderByDataAberturaDesc(
			String numeroNotaRelease,String status,String assignedUser, Pageable pages);
	

}
