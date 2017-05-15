package com.ipartek.formacion.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Expansión no encontrada")
public class AmpliacionNoEncontradoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AmpliacionNoEncontradoException(long codigo){
		super("AmpliacionNoEncontradoException"  + codigo);
	}
	
}
