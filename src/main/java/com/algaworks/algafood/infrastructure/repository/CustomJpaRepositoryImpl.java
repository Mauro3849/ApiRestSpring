package com.algaworks.algafood.infrastructure.repository;


import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import com.algaworks.algafood.domain.repository.CustomJpaRepository;

import jakarta.persistence.EntityManager;

public class CustomJpaRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements CustomJpaRepository<T, ID> {

	private EntityManager manager;

	public CustomJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
	
		this.manager = entityManager;
	}

	@Override
	public void detach(T entity) {
		manager.detach(entity);
		
	}

//	@Override
//	public Optional<T> buscarPrimeiro() {
//		var jpql = "from " + getClass().getName();
//	
//		@SuppressWarnings("unchecked")
//		T entity = (T) manager.createQuery(jpql, getClass())
//		.setMaxResults(1)
//		.getSingleResult();
//	
//		return Optional.ofNullable(entity);
//	}

//	@Override
//	public void detach(T entity) {
//		manager.detach(entity);
//	}

}

