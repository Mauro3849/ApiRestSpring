package com.algaworks.algafood.teste;

public class Pessoa {
	
	private String nome;
	private int idade;
	public Pessoa(String nome, int i) {
		this.nome=nome;
		this.idade=i;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

}
