package com.algaworks.algafood;

import java.math.BigDecimal;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MultiploValidador implements ConstraintValidator<Multiplo,Number> {
	
	private int numeroMultiplo;
	
	@Override
	public void initialize(Multiplo constraintAnnotation) {
		this.numeroMultiplo=constraintAnnotation.numero();
	}
	
	@Override
	public boolean isValid(Number value, ConstraintValidatorContext context) {
		boolean valido=true;
		
		if(value!=null) {
			var valorDecimal=BigDecimal.valueOf(value.doubleValue());
			var multiploDecimal=BigDecimal.valueOf(this.numeroMultiplo);
			var resto=valorDecimal.remainder(multiploDecimal);
			
			valido=valorDecimal.ZERO.compareTo(resto)==0;
		}
		return valido;
	}

}
