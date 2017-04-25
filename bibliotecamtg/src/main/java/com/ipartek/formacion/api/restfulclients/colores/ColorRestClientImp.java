package com.ipartek.formacion.api.restfulclients.colores;

import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.ipartek.formacion.dbms.persistence.Color;

public class ColorRestClientImp implements ColorRestClient{

	@Override
	public List<Color> getAll() {
		List<Color> colores = null;
		RestTemplate template = new RestTemplate();
		colores= template.getForObject(ColorRestClient.URL,List.class);
		return colores;
	}

	@Override
	public Color getById(int codigo) {
		Color color = null;
		RestTemplate template = new RestTemplate();
		color = template.getForObject(ColorRestClient.URL + "/" + codigo, Color.class);
		return color;
	}

	@Override
	public void delete(long codigo) {
		RestTemplate template = new RestTemplate();
		template.delete(ColorRestClient.URL+"/"+ codigo);
		
	}

}
