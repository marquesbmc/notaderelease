package com.caixa.notaderelease.api.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.caixa.notaderelease.api.enums.StatusEnum;
import com.caixa.notaderelease.api.enums.StatusTicketEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tbl_ticket")
public class Ticket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.REFRESH)
	@JoinColumn(name = "codigo_usuario_cliente")
	private User user;
	
	@Column(name = "data_abertura")
	private LocalDateTime  dataAbertura;
	
	// no futuro a data de instalacao soh podera ser preenchida apos aprovado e fechado o chamado.
	@Column(name = "data_instalacao")
	private LocalDateTime  dataInstalacao;


	@Column(name = "numero_nr")
	public String numeroNotaRelease;
	
	/*@Enumerated(EnumType.STRING)
	@Column(name = "status_notaderelease")
	private StatusEnum status;*/
	
	
	@Column(name = "status")
	private String status;
	
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.REFRESH)
	@JoinColumn(name = "codigo_usuario_aprovador")
	private User assignedUser;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "info")
	private String info;
	
	@Transient
	private List<ChangeStatus> changes;
	


	public String getNumeroNotaRelease() {
		return numeroNotaRelease;
	}

	public void setNumeroNotaRelease(String numeroNotaRelease) {
		this.numeroNotaRelease = numeroNotaRelease;
	}
	
	
	public List<ChangeStatus> getChanges() {
		return changes;
	}

	public void setChanges(List<ChangeStatus> changes) {
		this.changes = changes;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

	

	/*public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}*/

	public User getAssignedUser() {
		return assignedUser;
	}

	public void setAssignedUser(User assignedUser) {
		this.assignedUser = assignedUser;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(LocalDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public LocalDateTime getDataInstalacao() {
		return dataInstalacao;
	}

	public void setDataInstalacao(LocalDateTime dataInstalacao) {
		this.dataInstalacao = dataInstalacao;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
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
		Ticket other = (Ticket) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	


}
