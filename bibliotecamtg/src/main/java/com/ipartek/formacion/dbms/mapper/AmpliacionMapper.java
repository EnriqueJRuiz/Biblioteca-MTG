package com.ipartek.formacion.dbms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ipartek.formacion.dbms.persistence.Ampliacion;

public class AmpliacionMapper implements RowMapper<Ampliacion> {

	@Override
	public Ampliacion mapRow(ResultSet rs, int rownum) throws SQLException {
		Ampliacion ampliacion = new Ampliacion();
		ampliacion.setCodigo(rs.getInt("codigo"));
		ampliacion.setNombre(rs.getString("nombre"));
		ampliacion.setfLanzamiento(rs.getDate("flanzamiento"));
		ampliacion.setIcono(rs.getString("icono"));
		ampliacion.setImagen(rs.getString("imagen"));
		ampliacion.setSiglas(rs.getString("siglas"));
		ampliacion.setEspecial(rs.getBoolean("especial"));
		ampliacion.setBasica(rs.getBoolean("basica"));
		Ampliacion principalampliacion = new Ampliacion();
		principalampliacion.setNombre(rs.getString("principalnombre"));
		principalampliacion.setCodigo(rs.getInt("principalcodigo"));
		ampliacion.setPrincipal(principalampliacion);
		return ampliacion;
	}

}
