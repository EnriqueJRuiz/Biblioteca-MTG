package com.ipartek.formacion.controller.formater;

import com.ipartek.formacion.dbms.persistence.Ampliacion;
import com.ipartek.formacion.service.interfaces.AmpliacionService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;



public class AmpliacionConverter implements Converter<String, Ampliacion> {
	@Autowired
	AmpliacionService aS;
	private static final Logger LOGGER = LoggerFactory.getLogger(Ampliacion.class);
	
	@Override
	public Ampliacion convert(String codigo) {
		LOGGER.info(codigo.toString());
		return aS.getById(Integer.parseInt(codigo));
	}

}
