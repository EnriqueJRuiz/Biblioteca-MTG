package com.ipartek.formacion.dbms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.ipartek.formacion.dbms.persistence.Ampliacion;
import com.ipartek.formacion.dbms.persistence.Carta;
import com.ipartek.formacion.dbms.persistence.Color;

public class CartaExtractor implements ResultSetExtractor<Map<Long,Carta>> {
	
	private static Logger LOGGER = LoggerFactory.getLogger(CartaExtractor.class);
	
	@Override
	public Map<Long, Carta> extractData(ResultSet rs) throws SQLException, DataAccessException {
		Map<Long, Carta> cartas = new HashMap<Long, Carta>();
		
		while (rs.next()) {
		
			long codigo = rs.getLong("codigo");
			LOGGER.info("pasa por el while de CartaExtractor");
			Carta carta = cartas.get(codigo);
			
			if(carta == null){
				carta = new Carta();
				carta.setCodigo(rs.getLong("codigo"));
				carta.setNombre(rs.getString("nombre"));
				carta.setTexto(rs.getString("texto"));
				carta.setImagen(rs.getString("imagen"));
				carta.setCosteDeMana(rs.getString("coste"));
				carta.setRareza(rs.getString("rareza"));
				carta.setTipo(rs.getString("tipo"));
				carta.setSubtipo(rs.getString("subtipo"));
				carta.setSupertipo(rs.getString("supertipo"));
				carta.setNumero(rs.getInt("numero"));
				carta.setImagen(rs.getString("imagen"));
				Ampliacion ampliacion = new Ampliacion();
				ampliacion.setNombre(rs.getString("ampliacion"));
				ampliacion.setCodigo(rs.getInt("ampliacion_codigo"));
				carta.setAmpliacion(ampliacion);
			}
			LOGGER.trace("Codigo color:"+ rs.getInt("codicolor"));
			Integer cColor = rs.getInt("codicolor");
			if (cColor != null && cColor >0){
				Color color =  new Color();
				color.setCodigo(cColor);
				color.setNombre(rs.getString("nomcolor"));
				color.setIcono(rs.getString("iconcolor"));
				carta.getColores().add(color);
			}
			cartas.put(carta.getCodigo(), carta);
		}
		
		
		return cartas;
	}

}
