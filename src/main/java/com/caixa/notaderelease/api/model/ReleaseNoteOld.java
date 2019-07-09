package com.caixa.notaderelease.api.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.caixa.notaderelease.api.enums.AmbienteDeployEnum;
import com.caixa.notaderelease.api.enums.NomeSistemaEnum;
import com.caixa.notaderelease.api.enums.TipoDeployEnum;

@Entity
@Table(name = "tbl_releasenote")
public class ReleaseNoteOld {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@Column(name = "nome_sistema")
	private NomeSistemaEnum nomeSistema;

	@Column(name = "data_deploy")
	private LocalDate dataDeploy;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_deploy")
	private TipoDeployEnum tipoDeploy;

	@Enumerated(EnumType.STRING)
	@Column(name = "ambiente_deploy")
	private AmbienteDeployEnum ambienteDeploy;

	@Column(name = "esta_aprovado")
	private Boolean estaAprovado = false;

	// git-tag
	@Column(name = "versao_codigo_fonte")
	private String versaoCodigoFonte;

	// nexus - vecbuild
	@Column(name = "versao_codigo_compilado")
	private String versaoCodigoCompilado;

	// modelo banco de dados
	@Column(name = "versao_modelo_banco")
	private String versaoModeloBanco;

	// resumo da release
	@Column(name = "escopo_notaderelease")
	private String escopo;

	// resumo funcionalidades
	@Column(name = "funcionalidades_notaderelease")
	private String funcionalidades;

	// resumo ploblemas
	@Column(name = "problemas_conhecidos_notaderelease")
	private String problemasConhecidos;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "codigo_quality")
	private Quality quality;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "codigo_platform")
	private Platform platform;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_releasenote")
	private List<Module> modules;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_releasenote")
	private List<Team> team;

	public List<Team> getTeam () {
		return team;
	}

	public void setTeam (List<Team> team) {
		this.team = team;
	}

	public Platform getPlatform() {
		return platform;
	}

	public void setPlatform(Platform platform) {
		this.platform = platform;
	}

	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public NomeSistemaEnum getNomeSistema() {
		return nomeSistema;
	}

	public void setNomeSistema(NomeSistemaEnum nomeSistema) {
		this.nomeSistema = nomeSistema;
	}

	public LocalDate getDataDeploy() {
		return dataDeploy;
	}

	public void setDataDeploy(LocalDate dataDeploy) {
		this.dataDeploy = dataDeploy;
	}

	public TipoDeployEnum getTipoDeploy() {
		return tipoDeploy;
	}

	public void setTipoDeploy(TipoDeployEnum tipoDeploy) {
		this.tipoDeploy = tipoDeploy;
	}

	public AmbienteDeployEnum getAmbienteDeploy() {
		return ambienteDeploy;
	}

	public void setAmbienteDeploy(AmbienteDeployEnum ambienteDeploy) {
		this.ambienteDeploy = ambienteDeploy;
	}

	public Boolean getEstaAprovado() {
		return estaAprovado;
	}

	public void setEstaAprovado(Boolean estaAprovado) {
		this.estaAprovado = estaAprovado;
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

	public Quality getQuality() {
		return quality;
	}

	public void setQuality(Quality quality) {
		this.quality = quality;
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
		ReleaseNoteOld other = (ReleaseNoteOld) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
