package com.ipartek.formacion.dbms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ipartek.formacion.dbms.persistence.Ampliacion;
import com.ipartek.formacion.dbms.persistence.Carta;

public class CartaMapper implements RowMapper<Carta> {

	@Override
	public Carta mapRow(ResultSet rs, int rownum) throws SQLException {
		Carta carta = new Carta();
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
		//CartaRestController.setAmpliacion_nombre(rs.getString("ampliacion"));
		Ampliacion ampliacion = new Ampliacion();
		ampliacion.setNombre(rs.getString("ampliacion"));
		ampliacion.setCodigo(rs.getInt("ampliacion_codigo"));
		carta.setAmpliacion(ampliacion);
		
		return carta;
	}
}
