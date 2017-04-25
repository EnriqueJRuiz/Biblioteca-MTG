package com.ipartek.formacion.api.restfulclients.ampliaciones;

import java.util.List;

import com.ipartek.formacion.dbms.persistence.Ampliacion;

public interface AmpliacionRestClient {
final static String URL = "http://localhost:8080/gestiondocente/api/colores";
	
	public  List<Ampliacion> getAll();
	
	public Ampliacion getById(int codigo);

	public List<Ampliacion> getAllByNombre();
	
	public void delete(int codigo);
	
}
