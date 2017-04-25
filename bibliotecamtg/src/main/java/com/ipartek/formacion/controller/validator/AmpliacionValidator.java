package com.ipartek.formacion.controller.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ipartek.formacion.dbms.persistence.Ampliacion;
import com.ipartek.formacion.dbms.persistence.Carta;
import com.ipartek.formacion.dbms.persistence.Color;
import com.ipartek.formacion.service.interfaces.AmpliacionService;

public class AmpliacionValidator implements Validator{
	@Autowired
	AmpliacionService aS;
	private static final Logger LOGGER = LoggerFactory.getLogger(AmpliacionValidator.class);
	
	
	@Override
	public boolean supports(Class<?> paramClass) {
		return Ampliacion.class.equals(paramClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		LOGGER.info("pasas por la validacion de Ampliacion");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "form.nombreRequerido", "Tienes que introducir un nombre");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "siglas", "form.siglasRequerido", "Tienes que introducir el codigo del Set");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fLanzamiento", "form.fLanzamientoRequerido", "Tienes que introducir la fecha de lanzamiento");	
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "principal", "form.principalRequerido", "Tienes que seleccionar como minimo 'Sin bloque' ");
	
		Ampliacion ampli = (Ampliacion) obj;
		
		if(ampli.getCodigo() < Color.CODIGO_NULO){
			errors.reject("codigo", new Object[] { "'codigo'" }, "no puede ser menor que "+Carta.CODIGO_NULO);
		}
		if(ampli.getNombre().length() > 100){
			errors.rejectValue("nombre", "form.longitudNombreIncorrecta", new Object[] {"'nombre'"}, "· Tamaño del nombre maximo es de 45");
		}  
		if(ampli.getSiglas().length() > 3){
			errors.rejectValue("siglas", "form.longitudSiglasIncorrecta", new Object[] {"'Siglas'"}, "· El codigo del Set son 3 caracteres");
		} 
		if(ampli.getImagen().length() > 250){
			errors.rejectValue("imagen", "form.longitudImagenIncorrecta", new Object[] {"'imagen'"}, "· Tamaño del nombre de la imagen maximo es de 250 caracteres");
		}
		if(ampli.getIcono().length() > 250){
			errors.rejectValue("icono", "form.longitudIconoIncorrecta", new Object[] {"'icono'"}, "· Tamaño del nombre del icono maximo es de 250 caracteres");
		}
		if(ampli.getPrincipal().getCodigo() > 999999999){
			errors.rejectValue("principal", "form.longitudPrincipalIncorrecta", new Object[] {"'principal'"}, "· Tamaño del bloque maximo es de 11 digitos");
		}
		
	}
}
