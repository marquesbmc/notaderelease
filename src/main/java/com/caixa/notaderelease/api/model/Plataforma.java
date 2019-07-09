package com.caixa.notaderelease.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_plataforma")
public class Plataforma {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@Column(name = "conf_ambiente")
	private String confAmbiente;
	
	@Column(name = "rel_teste")
	private String relTeste;
	
	@Column(name = "rel_matriz_acesso")
	private String relMatrizAcesso;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getConfAmbiente() {
		return confAmbiente;
	}

	public void setConfAmbiente(String confAmbiente) {
		this.confAmbiente = confAmbiente;
	}

	public String getRelTeste() {
		return relTeste;
	}

	public void setRelTeste(String relTeste) {
		this.relTeste = relTeste;
	}

	public String getRelMatrizAcesso() {
		return relMatrizAcesso;
	}

	public void setRelMatrizAcesso(String relMatrizAcesso) {
		this.relMatrizAcesso = relMatrizAcesso;
	}

}
