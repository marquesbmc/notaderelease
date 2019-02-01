package com.caixa.notaderelease.api.model;

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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tbl_notaderelease")
public class NotaDeRelease {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@Column(name = "tipo_deploy")
	private String tipoDeploy;
	
	@Column(name = "ambiente_deploy")
	private String ambienteDeploy;
	
	@Column(name = "nome_sistema")
	private String nomeSistema;
	
	@Column(name = "status_aprovacao")
	private String statusAprovacao;
	
	@Column(name = "data_aprovacao")
	private LocalDate dataAprovacao;
	
	@Column(name = "data_cadastramento")
	private LocalDate dataCadastramento;
	
	@Column(name = "data_instalacao")
	private LocalDate dataInstalacao;
	
	@Column(name = "data_cancelamento")
	private LocalDate dataCancelamento;
	
	//git-tag
	@Column(name = "versao_codigo_fonte")
	private String versaoCodigoFonte;
	
	//nexus - vecbuil
	@Column(name = "versao_codigo_compilado")
	private String versaoCodigoCompilado;
	
	//modelo banco de dados
	@Column(name = "versao_modelo_banco")
	private String versaoModeloBanco;
	
	//resumo da release
	@Column(name = "escopo_notaderelease")
	private String escopo;
	
	//resumo funcionalidades
	@Column(name = "funcionalidades_notaderelease")
	private String funcionalidades;
			
	//resumo ploblemas
	@Column(name = "problemas_conhecidos_notaderelease")
	private String problemasConhecidos;
	

	@ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(name = "tbl_rel_notaxcliente", joinColumns = @JoinColumn(name = "codigo_notaderelease")
		, inverseJoinColumns = @JoinColumn(name = "codigo_cliente"))
	private List<Cliente> clientes;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(name = "tbl_rel_notaxmodulo", joinColumns = @JoinColumn(name = "codigo_notaderelease")
	, inverseJoinColumns = @JoinColumn(name = "codigo_modulo"))
	private List<Modulo> modulos;
	
	
	@ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(name = "tbl_rel_notaxprocesso", joinColumns = @JoinColumn(name = "codigo_notaderelease")
	, inverseJoinColumns = @JoinColumn(name = "codigo_processo"))
	private List<Processo> processos;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "codigo_plataforma")
	private Plataforma plataforma;

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "codigo_qualidade")
	private Qualidade qualidade;
	
	public Qualidade getQualidade() {
		return qualidade;
	}

	public void setQualidade(Qualidade qualidade) {
		this.qualidade = qualidade;
	}

	public List<Processo> getProcessos() {
		return processos;
	}

	public void setProcessos(List<Processo> processos) {
		this.processos = processos;
	}

	public List<Modulo> getModulos() {
		return modulos;
	}

	public void setModulos(List<Modulo> modulos) {
		this.modulos = modulos;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

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

	public LocalDate getDataInstalacao() {
		return dataInstalacao;
	}

	public void setDataInstalacao(LocalDate dataInstalacao) {
		this.dataInstalacao = dataInstalacao;
	}

	public LocalDate getDataCancelamento() {
		return dataCancelamento;
	}

	public void setDataCancelamento(LocalDate dataCancelamento) {
		this.dataCancelamento = dataCancelamento;
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

	public String getVersaoModeloBanco() {
		return versaoModeloBanco;
	}

	public void setVersaoModeloBanco(String versaoModeloBanco) {
		this.versaoModeloBanco = versaoModeloBanco;
	}

	public String getEscopo() {
		return escopo;
	}

	public void setEscopo(String escopo) {
		this.escopo = escopo;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NotaDeRelease other = (NotaDeRelease) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	public Plataforma getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(Plataforma plataforma) {
		this.plataforma = plataforma;
	}
	
	
}
