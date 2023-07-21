package com.algaworks.algafood.domain.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.algaworks.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

import jakarta.transaction.Transactional;

@Service
public class CadastroRestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CadastroCozinhaService cadastroCozinha;
	
	@Autowired
	private CadastroCidadeService cadastroCidade;
	
	@Autowired
	private CadastroFormaPagamentoService cadastroFormaPagamento;
	
	
	public Restaurante salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		Long cidadeId=restaurante.getEndereco().getCidade().getId();
		
		Cozinha cozinha = cadastroCozinha.buscarOuFalhar(cozinhaId);
		
		Cidade cidade=cadastroCidade.buscarOuFalhar(cidadeId);
		
		restaurante.setCozinha(cozinha);
		restaurante.getEndereco().setCidade(cidade);
		
		return restauranteRepository.save(restaurante);
	}
	
	@Transactional
	public void ativar(Long restauranteId) {
		
		Restaurante restauranteAtual= buscarOuFalhar(restauranteId);
		restauranteAtual.ativar();
		
	}
	
	
	@Transactional
	public void inativar(Long restauranteId) {
		
		Restaurante restauranteAtual= buscarOuFalhar(restauranteId);
		restauranteAtual.inativar();
		
	}
	
	@Transactional
	public void ativar (List<Long>restauranteIds) {
		restauranteIds.forEach(this::ativar);
	}
	
	@Transactional
	public void inativar (List<Long>restauranteIds) {
		restauranteIds.forEach(this::inativar);
	}
	
	@Transactional
	public void desassociarFormaPagamento(Long restauranteId,Long formaPagamentoId) {
		Restaurante restaurante=buscarOuFalhar(restauranteId);
		FormaPagamento formaPagamento=cadastroFormaPagamento.buscarOuFalhar(formaPagamentoId);
		restaurante.getFormasPagamento().remove(formaPagamento);
		
//		Nao precisa chamar o save ppois esta em uma transaction e o jpa vai sinconizar ao final com o banco de dados e remover
		
	}
	
	@Transactional
	public void associarFormaPagamento(Long restauranteId,Long formaPagamentoId) {
		Restaurante restaurante=buscarOuFalhar(restauranteId);
		FormaPagamento formaPagamento=cadastroFormaPagamento.buscarOuFalhar(formaPagamentoId);
		restaurante.getFormasPagamento().add(formaPagamento);
		
//		Nao precisa chamar o save ppois esta em uma transaction e o jpa vai sinconizar ao final com o banco de dados e remover
		
	}
	
	
	public Restaurante buscarOuFalhar(Long restauranteId) {
		return restauranteRepository.findById(restauranteId)
			.orElseThrow(() -> new RestauranteNaoEncontradoException(restauranteId));
	}
	
}