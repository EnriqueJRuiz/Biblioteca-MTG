package com.ipartek.formacion.api.restfulclients.cartas;

import java.util.Map;

import org.springframework.web.client.RestTemplate;
import com.ipartek.formacion.dbms.persistence.Carta;

public class CartaRestClientImp implements CartaRestClient{

	@Override
	public Carta create(Carta carta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Carta getById(long codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Long, Carta> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Carta update(Carta carta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(long codigo) {
		RestTemplate template = new RestTemplate();
		template.delete(CartaRestClient.URL + "/" + codigo);
		
	}

}
