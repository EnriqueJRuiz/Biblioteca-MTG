package com.ipartek.formacion.service.interfaces;

import java.util.List;
import java.util.Map;

import com.ipartek.formacion.dbms.persistence.Ampliacion;
import com.ipartek.formacion.dbms.persistence.Carta;

public interface AmpliacionService {
	
	public Ampliacion create(Ampliacion ampliacion);
	
	public Ampliacion getById(int codigo);
	
	public int delete(int codigo);
	
	public List<Ampliacion> getAll();
	
	public List<Ampliacion> ampliacionPricipalGetAll();


	public Ampliacion cartasGetById(int codigo);

	public Ampliacion update(Ampliacion ampliacion);

	public List<Ampliacion> cartaAmpliacionGetAll();

	public List<Ampliacion> getAllByNombre();

	public Map<Long, Carta> getByAmpliacion(int codigo);

	
}
