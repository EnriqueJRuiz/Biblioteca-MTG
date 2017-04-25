package com.ipartek.formacion.dbms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ipartek.formacion.dbms.persistence.Ampliacion;

public class AmpliacionPrincipalMapper {
	public class AmpliacionMapper implements RowMapper<Ampliacion> {

		@Override
		public Ampliacion mapRow(ResultSet rs, int rownum) throws SQLException {
			Ampliacion ampliacion = new Ampliacion();
			ampliacion.setCodigo(rs.getInt("codigo"));
			ampliacion.setNombre(rs.getString("nombre"));
			return ampliacion;
		}
	}
}
