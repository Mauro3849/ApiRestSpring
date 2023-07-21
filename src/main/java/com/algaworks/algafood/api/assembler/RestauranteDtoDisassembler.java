package com.algaworks.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.input.RestauranteDtoInput;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;

@Component
public class RestauranteDtoDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Restaurante toDomainObject(RestauranteDtoInput restauranteInput) {
		return modelMapper.map(restauranteInput, Restaurante.class);
	}
	
	public void copyToDomainObject(RestauranteDtoInput restauranteInput,Restaurante restaurante ) {
		
//		Para evitar exception Não apagar
	    restaurante.setCozinha(new Cozinha());
	    if(restaurante.getEndereco()!=null) {
	    	restaurante.getEndereco().setCidade(new Cidade());
	    }
		
		modelMapper.map(restauranteInput, restaurante);
		
		
	}
	
}
