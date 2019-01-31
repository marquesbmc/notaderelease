package com.caixa.notaderelease.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caixa.notaderelease.api.model.Cliente;
import com.caixa.notaderelease.api.model.Lancamento;
import com.caixa.notaderelease.api.model.Modulo;
import com.caixa.notaderelease.api.model.NotaDeRelease;
import com.caixa.notaderelease.api.model.Pessoa;
import com.caixa.notaderelease.api.model.Processo;
import com.caixa.notaderelease.api.repository.ClienteRepository;
import com.caixa.notaderelease.api.repository.LancamentoRepository;
import com.caixa.notaderelease.api.repository.ModuloRepository;
import com.caixa.notaderelease.api.repository.NotaDeReleaseRepository;
import com.caixa.notaderelease.api.repository.PessoaRepository;
import com.caixa.notaderelease.api.repository.ProcessoRepository;
import com.caixa.notaderelease.api.service.exception.PessoaInexistenteOuInativaException;

@Service
public class NotaDeReleaseService {

	@Autowired
	private NotaDeReleaseRepository notadereleaseRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ModuloRepository moduloRepository;
	
	
	@Autowired
	private ProcessoRepository processoRepository;

	public NotaDeRelease salvar(NotaDeRelease notaderelease) {
		

		return notadereleaseRepository.save(notaderelease);
	}
	


	public NotaDeRelease atualizar(Long codigo, NotaDeRelease notaderelease) {
		NotaDeRelease notadereleaseSalvo = buscarnotadereleaseExistente(codigo);
		atualizarListaClientes(notaderelease);
		atualizarListaModulos(notaderelease);
		atualizarListaProcessos(notaderelease);
		BeanUtils.copyProperties(notaderelease, notadereleaseSalvo, "codigo");
		
		return notadereleaseRepository.save(notadereleaseSalvo);
	}
	
	private void atualizarListaProcessos(NotaDeRelease notaderelease) {
		Processo processoVelho = new Processo();
		for (Processo processo : notaderelease.getProcessos()) {
			
			if (processo.getCodigo() != null) {
				processoVelho = processoRepository.findOne(processo.getCodigo());
			}
			BeanUtils.copyProperties(processo,processoVelho, "codigo");
			processoRepository.save(processoVelho);
		}
	}
	
	
	private void atualizarListaModulos(NotaDeRelease notaderelease) {
		Modulo moduloVelho = new Modulo();
		for (Modulo modulo : notaderelease.getModulos()) {
			
			if (modulo.getCodigo() != null) {
				moduloVelho = moduloRepository.findOne(modulo.getCodigo());
			}
			BeanUtils.copyProperties(modulo,moduloVelho, "codigo");
			moduloRepository.save(moduloVelho);
		}
	}
	
	
	
	private void atualizarListaClientes(NotaDeRelease notaderelease) {
		Cliente clienteVelho = new Cliente();
		for (Cliente cliente : notaderelease.getClientes()) {
			
			if (cliente.getCodigo() != null) {
				clienteVelho = clienteRepository.findOne(cliente.getCodigo());
			}
			BeanUtils.copyProperties(cliente,clienteVelho, "codigo");
			clienteRepository.save(clienteVelho);
		}
	}
	

	private NotaDeRelease buscarnotadereleaseExistente(Long codigo) {
		NotaDeRelease notadereleaseSalvo = notadereleaseRepository.findOne(codigo);
		if (notadereleaseSalvo == null) {
			throw new IllegalArgumentException();
		}
		return notadereleaseSalvo;
	}

}
