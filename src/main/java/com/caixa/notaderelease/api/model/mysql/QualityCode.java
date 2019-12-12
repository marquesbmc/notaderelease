package com.caixa.notaderelease.api.model.mysql;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_qualitycode")
public class QualityCode {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@Column(name = "dt_consulta")
	private LocalDate dataConsulta ;
	
	@Column(name = "nm_conceito")
	private String conceito;
	
	@Column(name = "tt_linhas")
	private String totalLinhas;
	
	@Column(name = "tt_classes")
	private String totalClasses;
	
	@Column(name = "tt_duplicacoes")
	private String totalduplicacoes;
	
	@Column(name = "tt_unitariocobertura")
	private String testeUnitarioCobertura;
	
	@Column(name = "tt_unitarioignorado")
	private String testeUnitarioIgnorado;
	
	@Column(name = "tt_teste")
	private String totalTeste;
	
	@Column(name = "tt_falhas")
	private String totalFalhas;
	
	@Column(name = "tt_erros")
	private String totalErros;
	
	@Column(name = "tt_problemablocker")
	private String problemaBlocker;
	
	@Column(name = "tt_problemacritical")
	private String problemaCritical;
	
	@Column(name = "tt_problemamajor")
	private String problemaMajor;
	
	@Column(name = "tt_problemaminor")
	private String problemaMinor;
	
	@Column(name = "tt_problemainfo")
	private String problemaInfo;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public LocalDate getDataConsulta() {
		return dataConsulta;
	}

	public void setDataConsulta(LocalDate dataConsulta) {
		this.dataConsulta = dataConsulta;
	}

	public String getConceito() {
		return conceito;
	}

	public void setConceito(String conceito) {
		this.conceito = conceito;
	}

	public String getTotalLinhas() {
		return totalLinhas;
	}

	public void setTotalLinhas(String totalLinhas) {
		this.totalLinhas = totalLinhas;
	}

	public String getTotalClasses() {
		return totalClasses;
	}

	public void setTotalClasses(String totalClasses) {
		this.totalClasses = totalClasses;
	}

	public String getTotalduplicacoes() {
		return totalduplicacoes;
	}

	public void setTotalduplicacoes(String totalduplicacoes) {
		this.totalduplicacoes = totalduplicacoes;
	}

	public String getTesteUnitarioCobertura() {
		return testeUnitarioCobertura;
	}

	public void setTesteUnitarioCobertura(String testeUnitarioCobertura) {
		this.testeUnitarioCobertura = testeUnitarioCobertura;
	}

	public String getTesteUnitarioIgnorado() {
		return testeUnitarioIgnorado;
	}

	public void setTesteUnitarioIgnorado(String testeUnitarioIgnorado) {
		this.testeUnitarioIgnorado = testeUnitarioIgnorado;
	}

	public String getTotalTeste() {
		return totalTeste;
	}

	public void setTotalTeste(String totalTeste) {
		this.totalTeste = totalTeste;
	}

	public String getTotalFalhas() {
		return totalFalhas;
	}

	public void setTotalFalhas(String totalFalhas) {
		this.totalFalhas = totalFalhas;
	}

	public String getTotalErros() {
		return totalErros;
	}

	public void setTotalErros(String totalErros) {
		this.totalErros = totalErros;
	}

	public String getProblemaBlocker() {
		return problemaBlocker;
	}

	public void setProblemaBlocker(String problemaBlocker) {
		this.problemaBlocker = problemaBlocker;
	}

	public String getProblemaCritical() {
		return problemaCritical;
	}

	public void setProblemaCritical(String problemaCritical) {
		this.problemaCritical = problemaCritical;
	}

	public String getProblemaMajor() {
		return problemaMajor;
	}

	public void setProblemaMajor(String problemaMajor) {
		this.problemaMajor = problemaMajor;
	}

	public String getProblemaMinor() {
		return problemaMinor;
	}

	public void setProblemaMinor(String problemaMinor) {
		this.problemaMinor = problemaMinor;
	}

	public String getProblemaInfo() {
		return problemaInfo;
	}

	public void setProblemaInfo(String problemaInfo) {
		this.problemaInfo = problemaInfo;
	}

}
