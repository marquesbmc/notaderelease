package com.caixa.notaderelease.api.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotBlank;


import com.caixa.notaderelease.api.enums.StatusEnum;


@Entity
@Table(name = "tbl_ticket")
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NotBlank
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "codigo_usuario_cliente")
	private User user;
	
	@NotBlank
	@Column(name = "data_abertura")
	private LocalDate dataAbertura;
	
	
	@NotBlank
	@Column(name = "numero_nr")
	private String numeroNotaRelease;
	
	@NotBlank
	@Enumerated(EnumType.STRING)
	@Column(name = "status_notaderelease")
	private StatusEnum status;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "codigo_usuario_aprovador")
	private User  assignedUser;
	
	@Column(name = "descricao")
	private String descricao;
		
	@Transient 
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name = "codigo_qualidade")
	private List<ChangeStatus> changes;

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

	public LocalDate getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(LocalDate dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public String getNumeroNotaRelease() {
		return numeroNotaRelease;
	}

	public void setNumeroNotaRelease(String numeroNotaRelease) {
		this.numeroNotaRelease = numeroNotaRelease;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

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

	/*public List<ChangeStatus> getChanges() {
		return changes;
	}

	public void setChanges(List<ChangeStatus> changes) {
		this.changes = changes;
	} */

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
