package com.ipartek.formacion.api.restfulclients.colores;

import java.util.List;

import com.ipartek.formacion.dbms.persistence.Color;

public interface ColorRestClient {
	
	final static String URL = "http://localhost:8080/gestiondocente/api/colores";
	
	public  List<Color> getAll();
	
	public Color getById(int codigo);
	
	public void delete(long codigo);
}
