package com.ipartek.formacion.service.interfaces;

import java.util.List;

import com.ipartek.formacion.dbms.persistence.Color;

public interface ColorService {

	
	public Color create(Color color);
	
	public Color update(Color color);
	
	public Color getById(int codigo);
	
	public List<Color>  getAll();
	
	public void delete(int codigo);

	public Color compararNombre(String nombre);

	public Color getByIcono(String keyValue);

	public List<Color> getBycarta(int codigo);
	
}
