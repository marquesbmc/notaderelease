package com.caixa.notaderelease.api.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tbl_ticket")
public class Ticket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@OneToOne()
	@JoinColumn(name = "codigo_usuario_cliente")
	private User user;
	
	@Column(name = "data_abertura")
	//private LocalDateTime  dataAbertura;
	private LocalDate  dataAbertura;
	
	// no futuro a data de instalacao soh podera ser preenchida apos aprovado e fechado o chamado.
	@Column(name = "data_instalacao")
	private LocalDateTime  dataInstalacao;

	@OneToOne()
	@JoinColumn(name = "numero_nr")
	public ReleaseNotes numeroNotaRelease;	
	
	@Column(name = "coordenacao")
	private String coordenacao;
	
	@Column(name = "status")
	private String status;
	
	@OneToOne()
	@JoinColumn(name = "codigo_usuario_aprovador")
	private User assignedUser;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "info")
	private String info;
	
	@Transient
	private List<ChangeStatus> changes;
	
	

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

	public String getCoordenacao() {
		return coordenacao;
	}

	public void setCoordenacao(String coordenacao) {
		this.coordenacao = coordenacao;
	}

	public LocalDate getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(LocalDate dataAbertura) {
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

	public ReleaseNotes getNumeroNotaRelease() {
		return numeroNotaRelease;
	}

	public void setNumeroNotaRelease(ReleaseNotes numeroNotaRelease) {
		this.numeroNotaRelease = numeroNotaRelease;
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
