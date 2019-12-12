package com.caixa.notaderelease.api.model.mysql;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_statusnotes")
public class StatusNotes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@Column(name = "nm_statusnotes")
	private String nomeStatus;
	public Long getCodigo() {
		return codigo;
	}

	public String getNomeStatus() {
		return nomeStatus;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public void setNomeStatus(String nomeStatus) {
		this.nomeStatus = nomeStatus;
	}

	

}
