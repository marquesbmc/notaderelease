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
import javax.persistence.Table;



@Entity
@Table(name = "tbl_analise_nota")
public class AnaliseNota {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	/**@Column(name = "data_aprovacao")
	private LocalDate dataAprovacao;
	
	@Column(name = "nome_aprovador")
	private String nomeAprovador;*/
	
	@ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(name = "tbl_rel_analisenotaxhistoricoanalise", joinColumns = @JoinColumn(name = "codigo_analisenota")
		, inverseJoinColumns = @JoinColumn(name = "codigo_historicoanalise"))
	private List<HistoricoAnalise> historicoanalises;

}
