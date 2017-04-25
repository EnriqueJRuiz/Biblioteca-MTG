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

public class AmpliacionExteractor implements ResultSetExtractor<Map<Integer, Ampliacion>> {
	private final Logger LOGGER = LoggerFactory.getLogger(AmpliacionExteractor.class);
	
	@Override
	public Map<Integer, Ampliacion> extractData(ResultSet rs) 
			throws SQLException, DataAccessException {
		Map<Integer,Ampliacion> ampliaciones = new HashMap<Integer, Ampliacion>();
		
		while(rs.next()){
			int codigo = rs.getInt("codigo");
			LOGGER.info("pasa por el while de AmpliacionExtractor");
			Ampliacion ampliacion = ampliaciones.get(codigo);
			
			if(ampliacion==null){
			
				ampliacion= new Ampliacion();
				ampliacion.setCodigo(rs.getInt("codigo"));
				ampliacion.setNombre(rs.getString("nombre"));
				ampliacion.setfLanzamiento(rs.getDate("flanzamiento"));
				ampliacion.setEspecial(rs.getBoolean("especial"));
				ampliacion.setBasica(rs.getBoolean("basica"));
				ampliacion.setIcono(rs.getString("icono"));
				ampliacion.setImagen(rs.getString("imagen"));
				ampliacion.setSiglas(rs.getString("siglas"));
				
				Ampliacion principalampliacion = new Ampliacion();
				principalampliacion.setNombre(rs.getString("principalnombre"));
				principalampliacion.setCodigo(rs.getInt("principalcodigo"));
				ampliacion.setPrincipal(principalampliacion);
				ampliacion.setCartas(new HashMap<Long,Carta>());//
				
			}
			
			long cCarta = rs.getLong("cartacodigo");
			if(cCarta > 0){
				Carta carta = new Carta();
				carta.setCodigo(rs.getLong("cartacodigo"));
				carta.setNombre(rs.getString("cartanombre"));
				carta.setNumero(rs.getInt("cartanumero"));
				carta.setRareza(rs.getString("cartarareza"));
				ampliacion.getCartas().put(cCarta, carta);
				LOGGER.info("pasa por cartas de ampliacion");
			}
			ampliaciones.put(ampliacion.getCodigo(),ampliacion);
		}
		
		return ampliaciones;
	}

}
