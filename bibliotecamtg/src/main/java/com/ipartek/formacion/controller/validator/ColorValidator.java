package com.ipartek.formacion.controller.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ipartek.formacion.dbms.persistence.Color;
import com.ipartek.formacion.service.interfaces.ColorService;

public class ColorValidator implements Validator{

	@Autowired
	ColorService coS;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ColorValidator.class);
	
	public ColorValidator() {
	}
	
	@Override
	public boolean supports(Class<?> paramClass) {
		return Color.class.equals(paramClass);
		
	}
	
	@Override
	public void validate(Object obj, Errors errors) {
		
		LOGGER.info("pasas por la validacion de COLOR");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "form.nombreRequerido", "Tienes que introducir un nombre");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "icono", "form.iconoRequerido", "Tienes que introducir un icono");
		Color col = (Color) obj;
		if(col.getCodigo() < Color.CODIGO_NULO){
			errors.reject("codigo", new Object[] { "'codigo'" }, "no puede ser menor que "+Color.CODIGO_NULO);
		}
		if(col.getNombre().length() > 45){
			errors.rejectValue("nombre", "form.longitudNombreIncorrecta", new Object[] {"'nombre'"}, "· Tamaño del nombre maximo es de 45 caracteres");
		} 
		
		if(col.getIcono().length() > 250){
			errors.rejectValue("icono", "form.longitudIconoIncorrecta", new Object[] {"'icono'"}, "· Tamaño del nombre del icono maximo es de 250 caracteres");
		} 
		
		//errors.rejectValue("icono", "form.formatoIconoIncorrecto", new Object[] {"'icono'"}, "· Tiene que ser una extension de imagen");
		
		
		/*Color colorComproIcono = coS.getByIcono(col.getIcono());
		if(colorComproIcono != null){
			if(col.getCodigo()==Color.CODIGO_NULO || (!col.equals(colorComproIcono) && col.getIcono().equalsIgnoreCase(colorComproIcono.getIcono()))){
				errors.rejectValue("icono","form.iconoRepetido",new Object[]{"'icono'"},"el icono esta repetido");
			}
		}*/
		
	}
	
}
