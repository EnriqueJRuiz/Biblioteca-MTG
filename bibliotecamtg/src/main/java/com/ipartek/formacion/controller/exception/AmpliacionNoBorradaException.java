package com.ipartek.formacion.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Expansión no borrada")
public class AmpliacionNoBorradaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AmpliacionNoBorradaException(long codigo){
		super("AmpliacionNoBorradaException"  + codigo);
	}
	
}
