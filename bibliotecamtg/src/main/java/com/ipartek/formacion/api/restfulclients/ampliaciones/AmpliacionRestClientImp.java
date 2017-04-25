package com.ipartek.formacion.api.restfulclients.ampliaciones;

import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.ipartek.formacion.dbms.persistence.Ampliacion;

public class AmpliacionRestClientImp implements AmpliacionRestClient{

	@Override
	public List<Ampliacion> getAll() {
		List<Ampliacion> ampliaciones = null;
		RestTemplate template = new RestTemplate();
		ampliaciones = template.getForObject(AmpliacionRestClient.URL,List.class);
		return ampliaciones;
	}

	@Override
	public Ampliacion getById(int codigo) {
		Ampliacion ampliacion = null;
		RestTemplate template = new RestTemplate();
		ampliacion = template.getForObject(AmpliacionRestClient.URL, Ampliacion.class);
		return ampliacion;
	}

	@Override
	public List<Ampliacion> getAllByNombre() {
		List<Ampliacion> ampliaciones = null;
		RestTemplate template = new RestTemplate();
		ampliaciones = template.getForObject(AmpliacionRestClient.URL,List.class);
		return ampliaciones;
	}

	@Override
	public void delete(int codigo) {
		RestTemplate template = new RestTemplate();
		template.delete(AmpliacionRestClient.URL + "/"+codigo);
	}

}
