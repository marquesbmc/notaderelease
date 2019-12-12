package com.caixa.notaderelease.api.model.mysql;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tbl_releasenotes")
public class ReleaseNotes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@Column(name = "nm_sistema")
	private String nomeSistema;
	
	@Column(name = "dt_criacao")
	private LocalDate dataCriacao ;
	
	@Column(name = "tp_ambiente_deploy")
	private String tipoDeploy;
	
	@Column(name = "nm_ambiente_deploy")
	private String ambienteDeploy;
	
	@Column(name = "vs_codigo_fonte")
	private String versaoCodigoFonte;
	
	@Column(name = "vs_codigo_compilado")
	private String versaoCodigoCompilado;
	
	@Column(name = "nm_coordenador_ti")
	private String nomeCoordTi;
	
	@Column(name = "nm_coordenador_projeto")
	private String nomeCoordProjeto;
	
	//Gerado, Vinculado, aprovado, reprovado, cancelado
	@Column(name = "tp_status_nr")
	private String statusNr;
	
	@Column(name = "nm_resumo")
	private String resumo;
	
	@Column(name = "nm_problemas")
	private String problemas;
	
	@Column(name = "nm_funcionalidades")
	private String funcionalidades;
	
	@Column(name = "nm_infraestrutura_software")
	private String infraestruturaSoftware;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_releasenotes")
	private List<Team> listteam;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_releasenotes")
	private List<Module> listmodule;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "codigo_qualitycode")
	private QualityCode qualitycode;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "codigo_database")
	private Database database;
	
	@Transient
	private String codTicket;
	
	@Transient
	private String statusticket;
	
	
	@Transient
	private String coordenacao;
	
	public String getCoordenacao() {
		return coordenacao;
	}

	public void setCoordenacao(String coordenacao) {
		this.coordenacao = coordenacao;
	}

	public String getStatusTicket() {
		return statusticket;
	}

	public void setStatusTicket(String statusTicket) {
		this.statusticket = statusTicket;
	}

	
	public String getCodTicket() {
		return codTicket;
	}

	public void  setCodTicket(String codTicket) {
		this.codTicket = codTicket;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNomeSistema() {
		return nomeSistema;
	}

	public void setNomeSistema(String nomeSistema) {
		this.nomeSistema = nomeSistema;
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

	public String getStatusNr() {
		return statusNr;
	}

	public void setStatusNr(String statusNr) {
		this.statusNr = statusNr;
	}
	
	
	public String getResumo() {
		return resumo;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}

	public String getProblemas() {
		return problemas;
	}

	public void setProblemas(String problemas) {
		this.problemas = problemas;
	}

	public String getFuncionalidades() {
		return funcionalidades;
	}

	public void setFuncionalidades(String funcionalidades) {
		this.funcionalidades = funcionalidades;
	}

	public String getInfraestruturaSoftware() {
		return infraestruturaSoftware;
	}

	public void setInfraestruturaSoftware(String infraestruturaSoftware) {
		this.infraestruturaSoftware = infraestruturaSoftware;
	}

	public List<Team> getListteam() {
		return listteam;
	}

	public void setListteam(List<Team> listteam) {
		this.listteam = listteam;
	}

	public List<Module> getListmodule() {
		return listmodule;
	}

	public void setListmodule(List<Module> listmodule) {
		this.listmodule = listmodule;
	}

	public QualityCode getQualitycode() {
		return qualitycode;
	}

	public void setQualitycode(QualityCode qualitycode) {
		this.qualitycode = qualitycode;
	}

	public Database getDatabase() {
		return database;
	}

	public void setDatabase(Database database) {
		this.database = database;
	}



}
