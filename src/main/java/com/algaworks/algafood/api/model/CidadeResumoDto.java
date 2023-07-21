package com.algaworks.algafood.api.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CidadeResumoDto {
	
	private Long id;
	private String nome;
	
//	já funcionaria com o nome do atributo nome estado, porem para aparecer na representação estado sera trocado ppara estado e sera necessario fazer alguns ajustes
//	private String nomeEstado;
	private String estado;
}

