package com.ipartek.formacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.formacion.dbms.dao.interfaces.ColorDAO;
import com.ipartek.formacion.dbms.persistence.Color;
import com.ipartek.formacion.service.interfaces.ColorService;

@Service
public class ColorServiceImp implements ColorService{

	@Autowired
	private ColorDAO colorDAO;
	
	@Override
	public Color create(Color color) {
		return colorDAO.create(color);
	}

	@Override
	public Color update(Color color) {
		return colorDAO.update(color);
	}

	@Override
	public Color getById(int codigo) {
		return colorDAO.getById(codigo);
	}

	@Override
	public List<Color> getAll() {
		return colorDAO.getAll();
	}

	@Override
	public void delete(int codigo) {
		colorDAO.delete(codigo);
		
	}

	@Override
	public Color compararNombre(String nombre) {
		return colorDAO.compararNombre(nombre);
	}

	@Override
	public Color getByIcono(String icono) {
		return colorDAO.getByIcono(icono);
	}
	
	@Override
	public List<Color> getBycarta(int codigo) {
		return colorDAO.getBycarta(codigo);
	}
}
