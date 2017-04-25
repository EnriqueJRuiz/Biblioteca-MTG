package com.ipartek.formacion.dbms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ipartek.formacion.dbms.persistence.Color;

public class ColorMapper implements RowMapper<Color> {

	@Override
	public Color mapRow(ResultSet rs, int rownum) throws SQLException {
		Color color = new Color();
		color.setCodigo(rs.getInt("codigo"));
		color.setNombre(rs.getString("nombre"));
		color.setIcono(rs.getString("icono"));
		return color;
		
	}
}
