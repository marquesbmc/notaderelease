package com.caixa.notaderelease.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.caixa.notaderelease.api.enums.StatusEnum;
import com.caixa.notaderelease.api.model.Ticket;

public interface TicketRepository  extends JpaRepository<Ticket, Long>{
	
	Page<Ticket> findByUserCodigoOrderByDataAberturaDesc(Pageable pages,Long userCodigo);
	
	Page<Ticket> findByNumeroNotaReleaseIgnoreCaseContainingAndStatusOrderByDataAberturaDesc(
			String numeroNotaRelease,StatusEnum status,Pageable pages);
	
	Page<Ticket> findByNumeroNotaReleaseIgnoreCaseContainingAndAssignedUserCodigoOrderByDataAberturaDesc(String numeroNotaRelease, Long userCodigo,Pageable pages);
	
	Page<Ticket> findByNumeroNotaReleaseIgnoreCaseContainingAndStatusIgnoreCaseContainingAndUserCodigoOrderByDataAberturaDesc(
			String numeroNotaRelease,String status,Long userCodigo, Pageable pages);
	
	//Page<Ticket> findByStatusIgnoreCaseContainingAndUserCodigoOrderByDataAberturaDesc(String status,Long userCodigo, Pageable pages);
	
	
	Page<Ticket> findByStatusAndUserCodigoOrderByDataAberturaDesc(StatusEnum status,Long userCodigo, Pageable pages);
	
	Page<Ticket> findByNumeroNotaRelease(String numeroNotaRelease,Pageable pages);
	
	
	Page<Ticket> findByNumeroNotaReleaseIgnoreCaseContainingAndStatusAndAssignedUserCodigoOrderByDataAberturaDesc(
			String numeroNotaRelease,StatusEnum status,Long assignedUserCodigo, Pageable pages);
	
	Page<Ticket> findByNumeroNotaReleaseIgnoreCaseContainingAndStatusContainingAndAssignedUserCodigoOrderByDataAberturaDesc(
			String numeroNotaRelease,StatusEnum status,Long assignedUserCodigo, Pageable pages);
	
	void delete(Long codigo);

	


}
