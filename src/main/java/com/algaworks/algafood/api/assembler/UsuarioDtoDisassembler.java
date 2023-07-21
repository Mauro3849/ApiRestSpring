package com.algaworks.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.input.UsuarioDtoInput;
import com.algaworks.algafood.domain.model.Usuario;

@Component
public class UsuarioDtoDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Usuario toDomainObject(UsuarioDtoInput usuarioInput) {
		return modelMapper.map(usuarioInput, Usuario.class);
	}
	
	public void copyToDomainObject(UsuarioDtoInput usuarioInput, Usuario usuario) {
		modelMapper.map(usuarioInput, usuario);
	}
	
}