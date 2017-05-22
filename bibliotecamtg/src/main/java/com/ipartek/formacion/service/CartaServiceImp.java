package com.ipartek.formacion.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.formacion.dbms.dao.interfaces.CartaDAO;
import com.ipartek.formacion.dbms.persistence.Carta;
import com.ipartek.formacion.service.interfaces.CartaService;

@Service
public class CartaServiceImp implements CartaService {

	@Autowired
	private CartaDAO cartaDAO;
	
	@Override
	public Carta create(Carta carta) {
		return cartaDAO.create(carta);
	}

	@Override
	public Carta getById(long codigo) {
		return cartaDAO.getById(codigo);
	}

	@Override
	public List<Carta> getAll() {
		return cartaDAO.getAll();
	}

	@Override
	public void delete(long codigo) {
		cartaDAO.delete(codigo);
		
	}

	@Override
	public Carta update(Carta carta) {
		return cartaDAO.update(carta);
	}

	@Override
	public Map<Long, Carta> cartaByAmpliacion(int id) {
		return cartaDAO.cartaByAmpliacion(id);
	}



}
