package com.algaworks.algafood.api.controller;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.Groups;
import com.algaworks.algafood.api.assembler.FormaPagamentoModelAssembler;
import com.algaworks.algafood.api.assembler.RestauranteDtoAssembler;
import com.algaworks.algafood.api.assembler.RestauranteDtoDisassembler;
import com.algaworks.algafood.api.model.CozinhaDto;
import com.algaworks.algafood.api.model.FormaPagamentoDto;
import com.algaworks.algafood.api.model.RestauranteDto;
import com.algaworks.algafood.api.model.input.RestauranteDtoInput;
import com.algaworks.algafood.domain.exception.CidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.Valid;




@RestController
@RequestMapping(value = "/restaurantes/{restauranteId}/formas-pagamento")
public class RestauranteFormaPagamentoController {

	
	
	@Autowired
	private CadastroRestauranteService cadastroRestaurante;
	
	@Autowired
	private FormaPagamentoModelAssembler formaPagamentoModelAssembler;
	
	
	@GetMapping
	public List<FormaPagamentoDto> listar(@PathVariable Long restauranteId) {
		Restaurante restaurante=cadastroRestaurante.buscarOuFalhar(restauranteId);
		
		return formaPagamentoModelAssembler.toCollectionModel(restaurante.getFormasPagamento());
	}
	
	@DeleteMapping("/{formaPagamentoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desassociar(@PathVariable Long restauranteId,@PathVariable Long formaPagamentoId) {
		cadastroRestaurante.desassociarFormaPagamento(restauranteId, formaPagamentoId);
	}
	
	@PutMapping("/{formaPagamentoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void associar(@PathVariable Long restauranteId,@PathVariable Long formaPagamentoId) {
		cadastroRestaurante.associarFormaPagamento(restauranteId, formaPagamentoId);
	}
			
}