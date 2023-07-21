package com.algaworks.algafood.api.model.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioComSenhaDtoInput extends UsuarioDtoInput {
	
	@NotBlank
	private String senha;
}
