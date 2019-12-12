package com.caixa.notaderelease.api.model.mysql;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_coordsystemnotes")
public class CoordSystemNotes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	@Column(name = "nm_coordnotes")
	private String coordSystem;
	
	
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

	public String getCoordSystem() {
		return coordSystem;
	}

	public void setCoordSystem(String coordSystem) {
		this.coordSystem = coordSystem;
	}

}
