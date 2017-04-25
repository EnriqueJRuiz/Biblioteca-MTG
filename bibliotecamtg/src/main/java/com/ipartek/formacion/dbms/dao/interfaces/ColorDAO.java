package com.ipartek.formacion.dbms.dao.interfaces;

import java.util.List;

import com.ipartek.formacion.dbms.persistence.Color;

public interface ColorDAO extends DAOSetter {
	
	public Color create(Color color);
	
	public Color update(Color color);
	
	public Color getById(int codigo);
	
	public List<Color>  getAll();
	
	public void delete(int codigo);

	public Color compararNombre(String nombre);

	public Color getByIcono(String icono);

	public List<Color> getBycarta(int codigo);
}
