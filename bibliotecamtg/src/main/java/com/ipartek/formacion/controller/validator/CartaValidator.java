package com.ipartek.formacion.controller.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ipartek.formacion.dbms.persistence.Carta;
import com.ipartek.formacion.dbms.persistence.Color;
import com.ipartek.formacion.service.interfaces.CartaService;

public class CartaValidator implements Validator{
	@Autowired
	CartaService cS;
	private static final Logger LOGGER = LoggerFactory.getLogger(CartaValidator.class);
	
	
	@Override
	public boolean supports(Class<?> paramClass) {
		return Carta.class.equals(paramClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		LOGGER.info("pasas por la validacion de CARTA");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "form.nombreRequerido", "Tienes que introducir un nombre");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ampliacion", "form.ampliacionRequerida", "Tienes que seleccionar una ampliación");
		
		Carta card =(Carta) obj;
		if(card.getCodigo() < Color.CODIGO_NULO){
			errors.reject("codigo", new Object[] { "'codigo'" }, "no puede ser menor que "+Carta.CODIGO_NULO);
		}
		if(card.getNombre().length() > 100){
			errors.rejectValue("nombre", "form.longitudNombreIncorrecta", new Object[] {"'nombre'"}, "· Tamaño del nombre maximo es de 45");
		}  
		if(card.getAmpliacion().getCodigo() > 999999999){
			errors.rejectValue("ampliacion", "form.longitudAmpliacionIncorrecta", new Object[] {"'ampliacion'"}, "· Tamaño de la expansión maximo es de 11 digitos");
		} 
		if(card.getRareza().length() > 100){
			errors.rejectValue("rareza", "form.longitudNombreIncorrecta", new Object[] {"'rareza'"}, "· Tamaño del nombre rareza de 100");
		} 
		if(card.getCosteDeMana().length() > 20){
			errors.rejectValue("costeDeMana", "form.longitudCosteDeManaIncorrecta", new Object[] {"'costeDeMana'"}, "· Tamaño del costeDeMana maximo de 20");
		} 
		if(card.getTipo().length() > 100){
			errors.rejectValue("tipo", "form.longitudTipoIncorrecta", new Object[] {"'tipo'"}, "· Tamaño del tipo maximo de 100");
		} 
		if(card.getImagen().length() > 250){
			errors.rejectValue("imagen", "form.longitudImagenIncorrecta", new Object[] {"'imagen'"}, "· Tamaño del nombre de la imagen maximo es de 250 caracteres");
		} 
		
		if(card.getNumero()> 999){
			errors.rejectValue("numero", "form.longitudNumeroIncorrecta", new Object[] {"'numero'"}, "· Tamaño del numero maximo de 999");
		} 
		
	}

}
