package com.ipartek.formacion.dbms.persistence.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ipartek.formacion.dbms.persistence.Color;
import com.ipartek.formacion.service.interfaces.ColorService;

public class IconoUnicoValidator implements ConstraintValidator<IconoUnico, Object>{
	private static final Logger LOGGER = LoggerFactory.getLogger(IconoUnicoValidator.class);
	private String code;
	private String key;
	
	@Autowired
	private ColorService coS;
	
	@Override
 	public void initialize(IconoUnico iconoexist) {
		this.code = iconoexist.code();
		this.key = iconoexist.key();
	}
	
	
	
	@Override
	public boolean isValid(Object object, ConstraintValidatorContext ctx) {
		LOGGER.info(object.toString());
		boolean valido = true;
		Object obj = null;
		try {
			final String codeValue = BeanUtils.getProperty(object, code);
			final String keyValue = BeanUtils.getProperty(object, key);
			if (keyValue != null && keyValue != "") {
				obj = coS.getByIcono(keyValue);
				if ((Integer.parseInt(codeValue) == Color.CODIGO_NULO || !object.equals(obj)) && obj != null) {
					valido = false;
					LOGGER.info("deberia fallar " + obj.toString());
				}
			}
		}catch (final Exception e) {
  			valido = false;
 			LOGGER.info(e.getMessage());
  		}
		return valido;
	}

}
