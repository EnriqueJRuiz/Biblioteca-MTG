package com.ipartek.formacion.service.interfaces;

import com.ipartek.formacion.dbms.persistence.Carta;

import java.util.List;
import java.util.Map;

public interface CartaService {

	public Carta create(Carta carta);
	
	public Carta getById(long codigo);
	
	public List<Carta> getAll();
	
	public Carta update(Carta carta);
	
	public void delete(long codigo);

	public Map<Long, Carta> cartaByAmpliacion(int id);

}
