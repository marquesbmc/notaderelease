package com.caixa.notaderelease.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_database")
public class Database {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@Column(name = "nm_modelobanco")
	private String modeloBanco;
	
	@Column(name = "nm_matrizacesso")
	private String matrizAcesso;
	
	@Column(name = "nm_cargadados")
	private String cargaDados;
	
	@Column(name = "nm_acessobd")
	private String acessoBanco;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getModeloBanco() {
		return modeloBanco;
	}

	public void setModeloBanco(String modeloBanco) {
		this.modeloBanco = modeloBanco;
	}

	public String getMatrizAcesso() {
		return matrizAcesso;
	}

	public void setMatrizAcesso(String matrizAcesso) {
		this.matrizAcesso = matrizAcesso;
	}

	public String getCargaDados() {
		return cargaDados;
	}

	public void setCargaDados(String cargaDados) {
		this.cargaDados = cargaDados;
	}

	public String getAcessoBanco() {
		return acessoBanco;
	}

	public void setAcessoBanco(String acessoBanco) {
		this.acessoBanco = acessoBanco;
	}

}
