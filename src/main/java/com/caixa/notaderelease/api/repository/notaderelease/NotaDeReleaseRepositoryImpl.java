package com.caixa.notaderelease.api.repository.notaderelease;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.caixa.notaderelease.api.model.NotaDeRelease;
import com.caixa.notaderelease.api.repository.filter.NotaDeReleaseFilter;
import com.caixa.notaderelease.api.repository.notaderelease.NotaDeReleaseRespositoryQuery;
import com.caixa.notaderelease.api.repository.projection.ResumoNotaDeRelease;

public class NotaDeReleaseRepositoryImpl implements NotaDeReleaseRespositoryQuery{
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<NotaDeRelease> filtrar(NotaDeReleaseFilter notadereleaseFilter, Pageable pageable) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<NotaDeRelease> criteria = builder.createQuery(NotaDeRelease.class);
		Root<NotaDeRelease> root = criteria.from(NotaDeRelease.class);
		
		Predicate[] predicates = criarRestricoes(notadereleaseFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<NotaDeRelease> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(notadereleaseFilter));
	}

	@Override
	public Page<ResumoNotaDeRelease> resumir(NotaDeReleaseFilter notadereleaseFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ResumoNotaDeRelease> criteria = builder.createQuery(ResumoNotaDeRelease.class);
		Root<NotaDeRelease> root = criteria.from(NotaDeRelease.class);
		
		criteria.select(builder.construct(ResumoNotaDeRelease.class
				, root.get("codigo")
				, root.get("tipoDeploy")
				, root.get("ambienteDeploy")
				, root.get("nomeSistema")
				, root.get("statusAprovacao")
				, root.get("dataInstalacao")
				, root.get("dataAprovacao")
				, root.get("dataCadastramento")
				, root.get("escopo")
				, root.get("versaoModeloBanco")
				, root.get("versaoCodigoCompilado")
				, root.get("versaoCodigoFonte")
				, root.get("funcionalidades")
				, root.get("problemasConhecidos")));
		
		Predicate[] predicates = criarRestricoes(notadereleaseFilter, builder, root);
		criteria.where(predicates);
		
		
		TypedQuery<ResumoNotaDeRelease> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(notadereleaseFilter));
	}
	

	private Predicate[] criarRestricoes(NotaDeReleaseFilter notadereleaseFilter, CriteriaBuilder builder,
			Root<NotaDeRelease> root) {
		List<Predicate> predicates = new ArrayList<>();		
		
		if (!StringUtils.isEmpty(notadereleaseFilter.getVersaoCodigoCompilado())) {
			predicates.add(builder.like(
					builder.lower(root.get("versaoCodigoCompilado")), "%" + notadereleaseFilter.getVersaoCodigoCompilado().toLowerCase() + "%"));
		}
		
		if (!StringUtils.isEmpty(notadereleaseFilter.getStatusAprovacao())) {
			predicates.add(builder.like(
					builder.lower(root.get("statusAprovacao")), "%" + notadereleaseFilter.getStatusAprovacao().toLowerCase() + "%"));
		}
		
		if (!StringUtils.isEmpty(notadereleaseFilter.getVersaoCodigoFonte())) {
			predicates.add(builder.like(
					builder.lower(root.get("versaoCodigoFonte")), "%" + notadereleaseFilter.getVersaoCodigoFonte().toLowerCase() + "%"));
		}
		
		if (!StringUtils.isEmpty(notadereleaseFilter.getNomeSistema())) {
			predicates.add(builder.like(
					builder.lower(root.get("nomeSistema")), "%" + notadereleaseFilter.getNomeSistema().toLowerCase() + "%"));
		}
		
		if (notadereleaseFilter.getDataAprovacaoDe() != null) {
			predicates.add(
					builder.greaterThanOrEqualTo(root.get("dataAprovacao"), notadereleaseFilter.getDataAprovacaoDe()));
		}
		
		if (notadereleaseFilter.getDataAprovacaoAte() != null) {
			predicates.add(
					builder.lessThanOrEqualTo(root.get("dataAprovacao"), notadereleaseFilter.getDataAprovacaoAte()));
		}
		
		if (notadereleaseFilter.getDataCadastramentoDe() != null) {
			predicates.add(
					builder.greaterThanOrEqualTo(root.get("dataCadastramento"), notadereleaseFilter.getDataCadastramentoDe()));
		}
		
		if (notadereleaseFilter.getDataCadastramentoAte() != null) {
			predicates.add(
					builder.lessThanOrEqualTo(root.get("dataCadastramento"), notadereleaseFilter.getDataCadastramentoAte()));
		}
		
		if (notadereleaseFilter.getDataInstalacaoDe() != null) {
			predicates.add(
					builder.greaterThanOrEqualTo(root.get("dataInstalacao"), notadereleaseFilter.getDataInstalacaoDe()));
		}
		
		if (notadereleaseFilter.getDataInstalacaoAte() != null) {
			predicates.add(
					builder.lessThanOrEqualTo(root.get("dataInstalacao"), notadereleaseFilter.getDataInstalacaoAte()));
		}
		
		
		
		/**if (notadereleaseFilter.getDataEnvioDe() != null) {
			predicates.add(
					builder.greaterThanOrEqualTo(root.get("dataRelease"), notadereleaseFilter.getDataEnvioDe()));
		}
		
		if (notadereleaseFilter.getDataEnvioAte() != null) {
			predicates.add(
					builder.lessThanOrEqualTo(root.get("dataRelease"), notadereleaseFilter.getDataEnvioAte()));
		} */
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	
	private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}
	
	
	private Long total(NotaDeReleaseFilter notadereleaseFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<NotaDeRelease> root = criteria.from(NotaDeRelease.class);
		
		Predicate[] predicates = criarRestricoes(notadereleaseFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

}
