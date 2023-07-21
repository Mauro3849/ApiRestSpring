package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

import jakarta.transaction.Transactional;

@Service
public class CadastroCozinhaService {
	
	private static final String MSG_COZINHA_EM_USO = "Cozinha de código %d não pode ser removida, pois esta em uso";
	private static final String MSG_COZINHA_NAO_ENCONTRADA = "Não existe uma cozinha com o código %d ";
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	public Cozinha salvar(Cozinha cozinha) {
		
		return cozinhaRepository.save(cozinha);
		
	}
	
	@Transactional
	public void excluir(Long cozinhaId) {
		
		try {
			cozinhaRepository.deleteById(cozinhaId);
			cozinhaRepository.flush();
		}catch(EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format(MSG_COZINHA_NAO_ENCONTRADA, cozinhaId));
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_COZINHA_EM_USO,cozinhaId));
		}
	}
	
	
	public Cozinha buscarOuFalhar(Long cozinhaId) {
		return cozinhaRepository.findById(cozinhaId)
			.orElseThrow(() -> new CozinhaNaoEncontradaException(cozinhaId));
	}
	
}
