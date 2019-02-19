package com.caixa.notaderelease.api.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "tbl_quality")
public class Quality {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@Column(name = "data_captura")
	private LocalDate dataCaptura;
	
	@Column(name = "conceito")
	private String conceito;
	
	@Column(name = "tot_testes_unitarios")
	private String totalTestesUnitarios;
	
	@Column(name = "tot_testes_funcionais")
	private String totalTestesFuncionais;
	
	@Column(name = "tot_testes_falhas")
	private String totalTestesFalhas;
	
	@Column(name = "tot_testes_erros")
	private String totalTestesErros;
	
	@Column(name = "tot_prob_info")
	private String totalProbInfo;
	
	@Column(name = "tot_prob_minor")
	private String totalProbMinor;
	
	@Column(name = "tot_prob_major")
	private String totalProbMajor;
	
	@Column(name = "tot_prob_blocker")
	private String totalProbBlocker;
	
	@Column(name = "tot_prob_critical")
	private String totalProbCritical;
	
	@Column(name = "rel_testes")
	private String relatorioTestes;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public LocalDate getDataCaptura() {
		return dataCaptura;
	}

	public void setDataCaptura(LocalDate dataCaptura) {
		this.dataCaptura = dataCaptura;
	}

	public String getConceito() {
		return conceito;
	}

	public void setConceito(String conceito) {
		this.conceito = conceito;
	}

	public String getTotalTestesUnitarios() {
		return totalTestesUnitarios;
	}

	public void setTotalTestesUnitarios(String totalTestesUnitarios) {
		this.totalTestesUnitarios = totalTestesUnitarios;
	}

	public String getTotalTestesFuncionais() {
		return totalTestesFuncionais;
	}

	public void setTotalTestesFuncionais(String totalTestesFuncionais) {
		this.totalTestesFuncionais = totalTestesFuncionais;
	}

	public String getTotalTestesFalhas() {
		return totalTestesFalhas;
	}

	public void setTotalTestesFalhas(String totalTestesFalhas) {
		this.totalTestesFalhas = totalTestesFalhas;
	}

	public String getTotalTestesErros() {
		return totalTestesErros;
	}

	public void setTotalTestesErros(String totalTestesErros) {
		this.totalTestesErros = totalTestesErros;
	}

	public String getTotalProbInfo() {
		return totalProbInfo;
	}

	public void setTotalProbInfo(String totalProbInfo) {
		this.totalProbInfo = totalProbInfo;
	}

	public String getTotalProbMinor() {
		return totalProbMinor;
	}

	public void setTotalProbMinor(String totalProbMinor) {
		this.totalProbMinor = totalProbMinor;
	}

	public String getTotalProbMajor() {
		return totalProbMajor;
	}

	public void setTotalProbMajor(String totalProbMajor) {
		this.totalProbMajor = totalProbMajor;
	}

	public String getTotalProbBlocker() {
		return totalProbBlocker;
	}

	public void setTotalProbBlocker(String totalProbBlocker) {
		this.totalProbBlocker = totalProbBlocker;
	}

	public String getTotalProbCritical() {
		return totalProbCritical;
	}

	public void setTotalProbCritical(String totalProbCritical) {
		this.totalProbCritical = totalProbCritical;
	}

	public String getRelatorioTestes() {
		return relatorioTestes;
	}

	public void setRelatorioTestes(String relatorioTestes) {
		this.relatorioTestes = relatorioTestes;
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
		Quality other = (Quality) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
