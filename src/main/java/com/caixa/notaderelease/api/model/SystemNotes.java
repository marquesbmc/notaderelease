package com.caixa.notaderelease.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_systemnotes")
public class SystemNotes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@Column(name = "nm_systemnotes")
	private String nomeSystem;

	public Long getCodigo() {
		return codigo;
	}

	public String getNomeSystem() {
		return nomeSystem;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public void setNomeSystem(String nomeSystem) {
		this.nomeSystem = nomeSystem;
	}

}
