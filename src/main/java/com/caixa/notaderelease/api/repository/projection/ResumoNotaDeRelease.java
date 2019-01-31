package com.caixa.notaderelease.api.repository.projection;


import java.time.LocalDate;

public class ResumoNotaDeRelease {
	
	//private Long codigo;
	//private String statusDeploy;
	//private LocalDate dataRelease;
	//private String sistema;
	//private String tipoDeploy;
	//private String ambienteDeploy;
	//private String tagGit;
	//private String versaoNexus;
	
	private Long codigo;
	private String tipoDeploy;
	private String ambienteDeploy;
	private String nomeSistema;
	private String statusAprovacao;
	private LocalDate dataInstalacao;
	private LocalDate dataAprovacao;
	private LocalDate dataCadastramento;
	private String escopo;
	private String versaoModeloBanco;
	private String versaoCodigoCompilado;
	private String versaoCodigoFonte;
	private String funcionalidades;
	private String problemasConhecidos;
	
	public ResumoNotaDeRelease(Long codigo, String tipoDeploy, String ambienteDeploy, String nomeSistema, String statusAprovacao,
			LocalDate dataInstalacao, LocalDate dataAprovacao, LocalDate dataCadastramento, String escopo, String versaoModeloBanco,
			String versaoCodigoCompilado, String versaoCodigoFonte, String funcionalidades, String problemasConhecidos){
		
		
		this.codigo=codigo;
		this.tipoDeploy=tipoDeploy;
		this.ambienteDeploy=ambienteDeploy;
		this.nomeSistema=nomeSistema;
		this.statusAprovacao=statusAprovacao;
		this.dataInstalacao=dataInstalacao;
		this.dataAprovacao=dataAprovacao;
		this.dataCadastramento=dataCadastramento;
		this.escopo=escopo;
		this.versaoModeloBanco=versaoModeloBanco;
		this.versaoCodigoCompilado=versaoCodigoCompilado;
		this.versaoCodigoFonte=versaoCodigoFonte;
		this.funcionalidades=funcionalidades;
		this.problemasConhecidos=problemasConhecidos;
		
			
		
	}
	
	/**public ResumoNotaDeRelease(Long codigo, String statusDeploy ,LocalDate dataRelease, String sistema, String tipoDeploy, 
			String ambienteDeploy, String tagGit, String versaoNexus) {
	this.codigo = codigo;
	this.statusDeploy = statusDeploy;
	this.dataRelease = dataRelease;
	this.sistema = sistema;
	this.tipoDeploy = tipoDeploy;
	this.ambienteDeploy = ambienteDeploy;
	this.tagGit = tagGit;
	this.versaoNexus = versaoNexus;	
	}*/



	public Long getCodigo() {
		return codigo;
	}



	public void setCodigo(Long codigo) {
		this.codigo = codigo;
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



	public LocalDate getDataInstalacao() {
		return dataInstalacao;
	}



	public void setDataInstalacao(LocalDate dataInstalacao) {
		this.dataInstalacao = dataInstalacao;
	}



	public LocalDate getDataAprovacao() {
		return dataAprovacao;
	}



	public void setDataAprovacao(LocalDate dataAprovacao) {
		this.dataAprovacao = dataAprovacao;
	}



	public LocalDate getDataCadastramento() {
		return dataCadastramento;
	}



	public void setDataCadastramento(LocalDate dataCadastramento) {
		this.dataCadastramento = dataCadastramento;
	}



	public String getEscopo() {
		return escopo;
	}



	public void setEscopo(String escopo) {
		this.escopo = escopo;
	}



	public String getVersaoModeloBanco() {
		return versaoModeloBanco;
	}



	public void setVersaoModeloBanco(String versaoModeloBanco) {
		this.versaoModeloBanco = versaoModeloBanco;
	}



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



	public String getFuncionalidades() {
		return funcionalidades;
	}



	public void setFuncionalidades(String funcionalidades) {
		this.funcionalidades = funcionalidades;
	}



	public String getProblemasConhecidos() {
		return problemasConhecidos;
	}



	public void setProblemasConhecidos(String problemasConhecidos) {
		this.problemasConhecidos = problemasConhecidos;
	}
	
	
	

}
