package com.caixa.notaderelease.api.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "tbl_releasenotess")
public class ReleaseNote {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@Column(name = "nome_sistema")
	private String sistema;

	@Column(name = "data_criacao")
	private LocalDate  dataCriacao;
	
	@Column(name = "tipo_deploy")
	private String  tipoDeploy;
	
	@Column(name = "ambiente_deploy")
	private String  ambienteDeploy;
	
	@Column(name = "versao_codigo_fonte")
	private String  versaoCodigoFonte;
	
	@Column(name = "versao_codigo_compilado")
	private String  versaoCodigoCompilado;
	
	@Column(name = "nome_coordTi")
	private String  nomeCoordTi;
	
	@Column(name = "nome_coordProjeto")
	private String  nomeCoordProjeto;
	
	@Column(name = "status_nr")
	private String statusNR;
	

	public String getStatusNR() {
		return statusNR;
	}

	public void setStatusNR(String statusNR) {
		this.statusNR = statusNR;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getSistema() {
		return sistema;
	}

	public void setSistema(String sistema) {
		this.sistema = sistema;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getTipoDeploy() {
		return tipoDeploy;
	}

	public void setTipoDeploy(String tipoDeploy) {
		this.tipoDeploy = tipoDeploy;
	}

	public String getAmbienteDeploy() {
		return ambienteDeploy;
	}

	public void setAmbienteDeploy(String ambienteDeploy) {
		this.ambienteDeploy = ambienteDeploy;
	}

	public String getVersaoCodigoFonte() {
		return versaoCodigoFonte;
	}

	public void setVersaoCodigoFonte(String versaoCodigoFonte) {
		this.versaoCodigoFonte = versaoCodigoFonte;
	}

	public String getVersaoCodigoCompilado() {
		return versaoCodigoCompilado;
	}

	public void setVersaoCodigoCompilado(String versaoCodigoCompilado) {
		this.versaoCodigoCompilado = versaoCodigoCompilado;
	}

	public String getNomeCoordTi() {
		return nomeCoordTi;
	}

	public void setNomeCoordTi(String nomeCoordTi) {
		this.nomeCoordTi = nomeCoordTi;
	}

	public String getNomeCoordProjeto() {
		return nomeCoordProjeto;
	}

	public void setNomeCoordProjeto(String nomeCoordProjeto) {
		this.nomeCoordProjeto = nomeCoordProjeto;
	}
	
	
	

	
}
