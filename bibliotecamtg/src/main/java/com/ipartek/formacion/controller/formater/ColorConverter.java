package com.ipartek.formacion.controller.formater;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

import com.ipartek.formacion.dbms.persistence.Color;
import com.ipartek.formacion.service.interfaces.ColorService;

public class ColorConverter implements Converter<String, Color> {
	@Inject
	ColorService coS;
	private static final Logger LOGGER = LoggerFactory.getLogger(ColorConverter.class);
	
	@Override
	public Color convert(String codigo) {
		LOGGER.info("color "+ codigo.toString());
		return coS.getById(Integer.parseInt(codigo));
	}

}
