package com.caixa.notaderelease.api.repository.mysql;


import org.springframework.data.jpa.repository.JpaRepository;

import com.caixa.notaderelease.api.model.mysql.ChangeStatus;


public interface ChangeStatusRepository extends JpaRepository<ChangeStatus, Long>{
	

	
			Iterable<ChangeStatus> findByTicketCodigoOrderByDateChangeStatusAsc(Long codigo);
			
			ChangeStatus findByTicketCodigoAndStatus(Long codigoticket, String status);

}
