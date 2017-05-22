package com.ipartek.formacion.dbms.dao.interfaces;

import java.util.List;
import java.util.Map;

import com.ipartek.formacion.dbms.persistence.Carta;;

public interface CartaDAO extends DAOSetter {

	public Carta create(Carta carta);
	
	public Carta getById(long codigo);
	
	public List<Carta> getAll();
	
	public Carta update(Carta carta);
	
	public void delete(long codigo);

	public Map<Long, Carta> cartaByAmpliacion(int id);
	
 }
