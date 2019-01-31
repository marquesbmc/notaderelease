package com.caixa.notaderelease.api.repository.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class NotaDeReleaseFilter {	
	
	private String versaoCodigoCompilado;
	private String versaoCodigoFonte;
	private String nomeSistema;
	private String statusAprovacao;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataInstalacaoDe;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataInstalacaoAte;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataAprovacaoAte;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataAprovacaoDe;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataCadastramentoAte;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataCadastramentoDe;
	
	

	public String getVersaoCodigoCompilado() {
		return versaoCodigoCompilado;
	}

	public void setVersaoCodigoCompilado(String versaoCodigoCompilado) {
		this.versaoCodigoCompilado = versaoCodigoCompilado;
	}

	public String getVersaoCodigoFonte() {
		return versaoCodigoFonte;
	}

	public void setVersaoCodigoFonte(String versaoCodigoFonte) {
		this.versaoCodigoFonte = versaoCodigoFonte;
	}

	public String getNomeSistema() {
		return nomeSistema;
	}

	public void setNomeSistema(String nomeSistema) {
		this.nomeSistema = nomeSistema;
	}

	public String getStatusAprovacao() {
		return statusAprovacao;
	}

	public void setStatusAprovacao(String statusAprovacao) {
		this.statusAprovacao = statusAprovacao;
	}

	public LocalDate getDataInstalacaoDe() {
		return dataInstalacaoDe;
	}

	public void setDataInstalacaoDe(LocalDate dataInstalacaoDe) {
		this.dataInstalacaoDe = dataInstalacaoDe;
	}

	public LocalDate getDataInstalacaoAte() {
		return dataInstalacaoAte;
	}

	public void setDataInstalacaoAte(LocalDate dataInstalacaoAte) {
		this.dataInstalacaoAte = dataInstalacaoAte;
	}

	public LocalDate getDataAprovacaoAte() {
		return dataAprovacaoAte;
	}

	public void setDataAprovacaoAte(LocalDate dataAprovacaoAte) {
		this.dataAprovacaoAte = dataAprovacaoAte;
	}

	public LocalDate getDataAprovacaoDe() {
		return dataAprovacaoDe;
	}

	public void setDataAprovacaoDe(LocalDate dataAprovacaoDe) {
		this.dataAprovacaoDe = dataAprovacaoDe;
	}

	public LocalDate getDataCadastramentoAte() {
		return dataCadastramentoAte;
	}

	public void setDataCadastramentoAte(LocalDate dataCadastramentoAte) {
		this.dataCadastramentoAte = dataCadastramentoAte;
	}

	public LocalDate getDataCadastramentoDe() {
		return dataCadastramentoDe;
	}

	public void setDataCadastramentoDe(LocalDate dataCadastramentoDe) {
		this.dataCadastramentoDe = dataCadastramentoDe;
	}
	
	
	


	

}
