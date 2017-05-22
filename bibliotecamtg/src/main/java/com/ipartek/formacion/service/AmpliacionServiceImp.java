package com.ipartek.formacion.service;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.formacion.dbms.dao.interfaces.AmpliacionDAO;
import com.ipartek.formacion.dbms.persistence.Ampliacion;
import com.ipartek.formacion.dbms.persistence.Carta;
import com.ipartek.formacion.service.interfaces.AmpliacionService;

@Service
public class AmpliacionServiceImp implements AmpliacionService {

	@Autowired
	private AmpliacionDAO ampliacionDAO;
	
	@Override
	public Ampliacion create(Ampliacion ampliacion) {
		return ampliacionDAO.create(ampliacion);
	}

	@Override
	public Ampliacion getById(int codigo) {
		return ampliacionDAO.getById(codigo);
	}

	@Override
	public int delete(int codigo) {
		return ampliacionDAO.delete(codigo);
		
	}

	@Override
	public List<Ampliacion> getAll() {
		return ampliacionDAO.getAll();
	}

	@Override
	public List<Ampliacion> ampliacionPricipalGetAll() {
		return ampliacionDAO.ampliacionPricipalGetAll();
	}


	@Override
	public Ampliacion cartasGetById(int codigo) {
		return ampliacionDAO.cartasGetById(codigo);
	}

	@Override
	public Ampliacion update(Ampliacion ampliacion) {
		return ampliacionDAO.update(ampliacion);
		
	}

	@Override
	public List<Ampliacion> cartaAmpliacionGetAll() {
		return ampliacionDAO.cartaAmpliacionGetAll();
	}

	@Override
	public List<Ampliacion> getAllByNombre() {
		
		return ampliacionDAO.getAllByNombre();
	}

	@Override
	public Map<Long, Carta> getByAmpliacion(int codigo) {
		return ampliacionDAO.getByAmpliacion(codigo);
	}

	@Override
	public Boolean cartagetByAmpliacion(int id) {
		return ampliacionDAO.cartagetByAmpliacion(id);
	}



}
