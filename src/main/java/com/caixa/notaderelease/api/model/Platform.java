package com.caixa.notaderelease.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_platform")
public class Platform {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@Column(name = "pb_conf_ambiente")
	private String pbConfAmbiente;
	
	@Column(name = "pb_testes_funcionais")
	private String pbTestesFuncionais;
	
	@Column(name = "pa_matriz_acesso")
	private String paMatrizAcesso;
	
	@Column(name = "pa_store_procedure")
	private String paStoreProcedure;
	
	@Column(name = "pa_bind")
	private String paBind;
	
	@Column(name = "pa_jcl")
	private String paJCL;
	
	@Column(name = "pa_cobol")
	private String paCobol;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getPbConfAmbiente() {
		return pbConfAmbiente;
	}

	public void setPbConfAmbiente(String pbConfAmbiente) {
		this.pbConfAmbiente = pbConfAmbiente;
	}

	public String getPbTestesFuncionais() {
		return pbTestesFuncionais;
	}

	public void setPbTestesFuncionais(String pbTestesFuncionais) {
		this.pbTestesFuncionais = pbTestesFuncionais;
	}

	public String getPaMatrizAcesso() {
		return paMatrizAcesso;
	}

	public void setPaMatrizAcesso(String paMatrizAcesso) {
		this.paMatrizAcesso = paMatrizAcesso;
	}

	public String getPaStoreProcedure() {
		return paStoreProcedure;
	}

	public void setPaStoreProcedure(String paStoreProcedure) {
		this.paStoreProcedure = paStoreProcedure;
	}

	public String getPaBind() {
		return paBind;
	}

	public void setPaBind(String paBind) {
		this.paBind = paBind;
	}

	public String getPaJCL() {
		return paJCL;
	}

	public void setPaJCL(String paJCL) {
		this.paJCL = paJCL;
	}

	public String getPaCobol() {
		return paCobol;
	}

	public void setPaCobol(String paCobol) {
		this.paCobol = paCobol;
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
		Platform other = (Platform) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}


}
