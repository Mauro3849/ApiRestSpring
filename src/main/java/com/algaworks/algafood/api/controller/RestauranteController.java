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
import com.algaworks.algafood.api.assembler.RestauranteDtoAssembler;
import com.algaworks.algafood.api.assembler.RestauranteDtoDisassembler;
import com.algaworks.algafood.api.model.CozinhaDto;
import com.algaworks.algafood.api.model.RestauranteDto;
import com.algaworks.algafood.api.model.input.RestauranteDtoInput;
import com.algaworks.algafood.domain.exception.CidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.Valid;




@RestController
@RequestMapping(value = "/restaurantes")
public class RestauranteController {

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CadastroRestauranteService cadastroRestaurante;
	
	@Autowired
	private RestauranteDtoAssembler restauranteDtoAssembler;
	
	@Autowired
	private RestauranteDtoDisassembler restauranteInputDisassembler;
	
	
	@GetMapping
	public List<RestauranteDto> listar() {
		return restauranteDtoAssembler.toCollectionDto(restauranteRepository.findAll());
	}
	
	@GetMapping("/{restauranteId}")
	public RestauranteDto buscar(@PathVariable Long restauranteId) {
		
		
		Restaurante restaurante =cadastroRestaurante.buscarOuFalhar(restauranteId);
		
		return restauranteDtoAssembler.toDto(restaurante);
		
	}

		
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RestauranteDto adicionar(@RequestBody @Valid RestauranteDtoInput restauranteDtoInput) {
		try {
			
			Restaurante restaurante=restauranteInputDisassembler.toDomainObject(restauranteDtoInput);
			
			return restauranteDtoAssembler.toDto(cadastroRestaurante.salvar(restaurante));
		} catch (CozinhaNaoEncontradaException|CidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	@PutMapping("/{restauranteId}")
	public RestauranteDto atualizar(@PathVariable Long restauranteId,
			@RequestBody @Valid RestauranteDtoInput restauranteDtoInput) {
		try {
			
//			Restaurante restaurante=restauranteInputDisassembler.toDomainObject(restauranteDtoInput);
			Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);
			
			
	restauranteInputDisassembler.copyToDomainObject(restauranteDtoInput, restauranteAtual);
//		BeanUtils.copyProperties(restaurante, restauranteAtual, 
//					"id", "formasPagamento", "endereco", "dataCadastro", "produtos");

			return restauranteDtoAssembler.toDto(cadastroRestaurante.salvar(restauranteAtual));
		} catch (CozinhaNaoEncontradaException |CidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	


	private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino) {
		ObjectMapper objectMapper = new ObjectMapper();
		Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);
		
		dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
			Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
			field.setAccessible(true);
			
			Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
			
//			System.out.println(nomePropriedade + " = " + valorPropriedade + " = " + novoValor);
			
			ReflectionUtils.setField(field, restauranteDestino, novoValor);
		});
	}
	
	@PutMapping("/{restauranteId}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void ativar(@PathVariable Long restauranteId) {
		
		cadastroRestaurante.ativar(restauranteId);
	}

	
//	Acredito que nesse metodo podera usar o putmapping Perguntar por que não foi usado
	@DeleteMapping("/{restauranteId}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void inativar(@PathVariable Long restauranteId) {
		
		cadastroRestaurante.inativar(restauranteId);
	}
	
	@PutMapping("/ativacoes")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void ativarMultiplos(@RequestBody List<Long> restauranteIds) {
		try {
			cadastroRestaurante.ativar(restauranteIds);
		} catch (RestauranteNaoEncontradoException e) {
		    throw new NegocioException(e.getMessage(),e);
		}
		
	}
	
	@DeleteMapping("/ativacoes")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void inativarMultiplos(@RequestBody List<Long> restauranteIds) {
		try {
			cadastroRestaurante.inativar(restauranteIds);
		} catch (RestauranteNaoEncontradoException e) {
		    throw new NegocioException(e.getMessage(),e);		}
		
	}
		
}