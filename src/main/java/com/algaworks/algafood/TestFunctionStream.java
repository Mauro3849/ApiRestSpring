package com.algaworks.algafood;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.teste.Pessoa;



public class TestFunctionStream {


	public static void main(String[] args) {
		
//		ArrayList<String> listaString = new ArrayList<>(); 
//		
//		
//	    listaString.add("Mauro");
//		listaString.add("Joao Mauro");
//		listaString.add("Péle");
//		
//		List<String> listaNomes = listaString.stream().
//				filter(rest-> rest.contains("Mauro")).collect(Collectors.toList());
//		
//		 System.out.println(listaNomes);
//		 
//		 
//		 System.out.println("Imprime o quadrado de todos os elementos da lista!");
//		 List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
//		 list.forEach(n -> System.out.println(n * n));
//		
		 
//		        List<Integer> numbers = Arrays.asList(1,2,3,4,5);
//
//		        List<Integer> lessThanThree = numbers.stream()
//		                .filter((Integer number) -> {
//		                    return number < 3;
//		                })
//		                .collect(Collectors.toList());
//
//		        System.out.println(lessThanThree);
		   
		 List<Pessoa> listPessoas = Arrays.asList(new Pessoa("Eduardo", 29),
				 									new Pessoa("Luiz", 32), new Pessoa("Bruna", 26));
		 
				    System.out.println("Ordenando pessoas pelo nome:");
				    
				    Collections.sort(listPessoas, (Pessoa pessoa1, Pessoa pessoa2)
				    -> pessoa1.getNome().compareTo(pessoa2.getNome()));
				    listPessoas.forEach(p -> System.out.println(p.getNome()));
				    
//				    System.out.println("Ordenando pessoas pela idade:");
//				    
//				    Collections.sort(listPessoas, (Comparator<? super T>) (Pessoa pessoa1, Pessoa pessoa2)
//				    -> pessoa1.getIdade().compareTo(pessoa2.getIdade()));
//				    listPessoas.forEach(p -> System.out.println(p.getNome()));
//		
		
	}
		
		
	}


