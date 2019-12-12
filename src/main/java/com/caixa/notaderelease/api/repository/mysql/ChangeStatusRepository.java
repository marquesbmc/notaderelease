package com.caixa.notaderelease.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;


import com.caixa.notaderelease.api.model.ChangeStatus;


public interface ChangeStatusRepository extends JpaRepository<ChangeStatus, Long>{
	

	
			Iterable<ChangeStatus> findByTicketCodigoOrderByDateChangeStatusAsc(Long codigo);
			
			ChangeStatus findByTicketCodigoAndStatus(Long codigoticket, String status);

}
