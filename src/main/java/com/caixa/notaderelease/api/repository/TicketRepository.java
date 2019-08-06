package com.caixa.notaderelease.api.repository;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;

import com.caixa.notaderelease.api.model.ReleaseNotes;
import com.caixa.notaderelease.api.model.Ticket;
import com.caixa.notaderelease.api.model.User;

public interface TicketRepository  extends JpaRepository<Ticket, Long>{
	
	
	
	//nao esta sendo utilizada - trocando pela debaixo como teste
	Page<Ticket> findByUserCodigoOrderByDataAberturaDesc(Pageable pages,Long userCodigo);
	
	//Page<Ticket> findByUserCoordenacaoOrderByDataAberturaDesc(Pageable pages,String userCoordenacao);
	
	Page<Ticket> findByCoordenacaoOrderByDataAberturaDesc(Pageable pages,String coordenacao);
	
	Page<Ticket> findByNumeroNotaReleaseIgnoreCaseContainingAndStatusContainingOrderByDataAberturaDesc(
			String numeroNotaRelease,String status,Pageable pages);
	
	Page<Ticket> findByNumeroNotaReleaseIgnoreCaseContainingAndAssignedUserCodigoOrderByDataAberturaDesc(String numeroNotaRelease, Long userCodigo,Pageable pages);
	
	Page<Ticket> findByNumeroNotaReleaseIgnoreCaseContainingAndStatusIgnoreCaseContainingAndUserCodigoOrderByDataAberturaDesc(
			String numeroNotaRelease,String status,Long userCodigo, Pageable pages);
	
	
	Page<Ticket>  findByStatusContainingAndUserCodigoOrderByDataAberturaDesc(String status, Long userCodigo, Pageable pages);

	
	Page<Ticket> findByNumeroNotaRelease(String numeroNotaRelease,Pageable pages);
	
	
	Page<Ticket> findByNumeroNotaReleaseIgnoreCaseContainingAndStatusContainingAndAssignedUserCodigoOrderByDataAberturaDesc(String numeroNotaRelease,String status,Long assignedUserCodigo, Pageable pages);
	
	Long countByNumeroNotaRelease(ReleaseNotes numeroNotaRelease);
	
	Ticket findByNumeroNotaRelease(ReleaseNotes numeroNotaRelease);
	
	void delete(Long codigo);
	

	//Listar Ticket - Cliente
	Page<Ticket> findByStatusContainingIgnoreCaseAndNumeroNotaReleaseNomeSistemaContainingIgnoreCaseAndCoordenacaoContainingIgnoreCaseAndDataAberturaBetweenAndNumeroNotaReleaseNomeSistemaInOrderByDataAberturaDesc(String status, String sistema,String coordenacao,LocalDate dateini,LocalDate datefim,List<String> listsistema,Pageable pages);
	Page<Ticket> findByNumeroNotaReleaseCodigoAndStatusContainingIgnoreCaseAndNumeroNotaReleaseNomeSistemaContainingIgnoreCaseAndCoordenacaoContainingIgnoreCaseAndDataAberturaBetweenAndNumeroNotaReleaseNomeSistemaInOrderByDataAberturaDesc(Long codigo,String status, String sistema,String coordenacao,LocalDate dateini,LocalDate datefim,List<String> listsistema,Pageable pages);					
	
	//Listar Ticket - Tecnico
	Page<Ticket> findByStatusContainingIgnoreCaseAndNumeroNotaReleaseNomeSistemaContainingIgnoreCaseAndCoordenacaoContainingIgnoreCaseAndDataAberturaBetweenOrderByDataAberturaDesc(String status, String sistema,String coordenacao, LocalDate dateini,LocalDate datefim,Pageable pages);
	//Page<Ticket> findByStatusContainingIgnoreCaseAndAssignedUserMatriculaContainingIgnoreCaseAndNumeroNotaReleaseNomeSistemaContainingIgnoreCaseAndCoordenacaoContainingIgnoreCaseAndDataAberturaBetweenOrderByDataAberturaDesc(String status,String assignedusermatricula, String sistema,String coordenacao, LocalDate dateini,LocalDate datefim,Pageable pages);
	Page<Ticket> findByNumeroNotaReleaseCodigoAndStatusContainingIgnoreCaseAndNumeroNotaReleaseNomeSistemaContainingIgnoreCaseAndCoordenacaoContainingIgnoreCaseAndDataAberturaBetweenOrderByDataAberturaDesc(Long codigo,String status, String sistema,String coordenacao, LocalDate dateini,LocalDate datefim,Pageable pages);					
		

}
