package com.algaworks.algafood.infrastructure.repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepositoryQueries;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;
	

	@Override
	public List<Restaurante>find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal){
		
		
		var jpql=new StringBuilder();
		var parametros=new HashMap<>();
		
		
		jpql.append("from Restaurante where 0=0 ");
		
		if (StringUtils.hasLength(nome)) {
			
			jpql.append("and nome like :nome ");
			parametros.put("nome", "%" + nome + "%");
		}
		
		if(taxaFreteInicial!=null) {
			
			jpql.append("and taxaFrete>= :taxaFreteInicial ");
			parametros.put("taxaFreteInicial", taxaFreteInicial);
		}
		
		if(taxaFreteFinal!=null) {
			
			jpql.append("and taxaFrete<= :taxaFreteFinal");
			parametros.put("taxaFreteFinal", taxaFreteFinal);
		}
		
	
//	return manager.createQuery(jpql.toString(), Restaurante.class)
//				.setParameter("nome", "%" + nome + "%")
//				.setParameter("taxaFreteInicial", taxaFreteInicial)
//				.setParameter("taxaFreteFinal", taxaFreteFinal)
//				.getResultList();
		
		TypedQuery<Restaurante> query = manager
				.createQuery(jpql.toString(), Restaurante.class);
		
		parametros.forEach((chave, valor) -> query.setParameter(chave.toString(), valor));

		return query.getResultList();
	
		
		
		
	}
}
