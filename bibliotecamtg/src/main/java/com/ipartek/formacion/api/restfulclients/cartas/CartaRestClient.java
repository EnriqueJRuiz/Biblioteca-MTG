package com.ipartek.formacion.api.restfulclients.cartas;

import java.util.Map;

import com.ipartek.formacion.dbms.persistence.Carta;


public interface CartaRestClient {

	final static String URL = "http://localhost:8080/gestiondocente/api/cartas";
	
	public Carta create(Carta carta);
	
	public Carta getById(long codigo);
	
	public Map<Long, Carta> getAll();
	
	public Carta update(Carta carta);
	
	public void delete(long codigo);
	
}
