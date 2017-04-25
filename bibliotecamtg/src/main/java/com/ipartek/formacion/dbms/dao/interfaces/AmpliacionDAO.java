package com.ipartek.formacion.dbms.dao.interfaces;

import java.util.List;
import java.util.Map;

import com.ipartek.formacion.dbms.persistence.Ampliacion;
import com.ipartek.formacion.dbms.persistence.Carta;

public interface AmpliacionDAO extends DAOSetter {
	
	public Ampliacion create(Ampliacion ampliacion);
	
	public Ampliacion getById(int codigo);
	
	public int delete(long codigo);
	
	public List<Ampliacion> getAll();
	
	public List<Ampliacion> ampliacionPricipalGetAll();

	public Ampliacion cartasGetById(int codigo);

	public Ampliacion update(Ampliacion ampliacion);

	public List<Ampliacion> cartaAmpliacionGetAll();

	public List<Ampliacion> getAllByNombre();
	
	public Map<Long, Carta> getByAmpliacion(int codigo);
}
