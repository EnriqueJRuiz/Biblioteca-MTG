package com.ipartek.formacion.dbms.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import com.ipartek.formacion.dbms.dao.interfaces.ColorDAO;
import com.ipartek.formacion.dbms.mapper.CartaColorMapper;
import com.ipartek.formacion.dbms.mapper.ColorMapper;
import com.ipartek.formacion.dbms.persistence.Color;

public class ColorDAOImp implements ColorDAO{

	private DataSource dataSource;
	private SimpleJdbcCall jdbcCall;
	private JdbcTemplate template;
	private static final Logger LOGGER = LoggerFactory.getLogger(ColorDAOImp.class);
	
	
	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.template = new JdbcTemplate(dataSource);
	}


	@Override
	public Color create(Color color) {
		final String SQL="colorCreate";
		this.jdbcCall= new SimpleJdbcCall(dataSource);
		jdbcCall.withProcedureName(SQL);
		SqlParameterSource in = new MapSqlParameterSource()
				.addValue("pnombre", color.getNombre())
				.addValue("picono", color.getIcono());
		
		Map<String, Object> out = jdbcCall.execute(in);	
		color.setCodigo((Integer)out.get("pcodigo"));
		return color;
	}

	@Override
	public Color update(Color color) {
		final String SQL="colorUpdate";
		this.jdbcCall= new SimpleJdbcCall(dataSource);
		jdbcCall.withProcedureName(SQL);
		SqlParameterSource in = new MapSqlParameterSource()
				.addValue("pnombre", color.getNombre())
				.addValue("picono", color.getIcono())	
				.addValue("pcodigo", color.getCodigo());
		
		jdbcCall.execute(in);	
		return color;
	}

	@Override
	public Color getById(int codigo) {
		final String SQL = "CALL colorgetById(?)";
		Color color = null;
		try{
			color = template.queryForObject(SQL, new ColorMapper(),
					new Object[] { codigo }) ;
		}catch(EmptyResultDataAccessException e){
			color = new Color();
		}
		return color;
	}

	@Override
	public List<Color> getAll() {
		final String SQL = "CALL colorgetAll();";
		List<Color> colores = null;
		try{
			 colores = template.query(SQL, new ColorMapper());		
		}catch(EmptyResultDataAccessException e){
			LOGGER.trace(e.getMessage());	
		}
		return colores;
	}

	@Override
	public void delete(int codigo) {
		final String SQL= "colorDeleteF";
		this.jdbcCall = new SimpleJdbcCall(dataSource);
		jdbcCall.withProcedureName(SQL);
		SqlParameterSource in = new MapSqlParameterSource()
				.addValue("pcodigo", codigo);
		LOGGER.info(String.valueOf(codigo));
		jdbcCall.execute(in);
		
	}


	@Override
	public Color compararNombre(String nombre) {
		final String SQL = "CALL colorgetByNombre(?)";
		Color color = null;
		try{
			color=template.queryForObject(SQL, new ColorMapper(), new Object[] {nombre});
			LOGGER.info("HAY Colores con ese nombre");
		}catch(EmptyResultDataAccessException e){
			color = null;
			LOGGER.info("No hay Colores con ese nombre");
		}
		return color;
	}


	@Override
	public Color getByIcono(String icono) {
		
		final String SQL = "CALL colorgetByIcono(?)";
		Color color = null;
		try{
			color=template.queryForObject(SQL, new ColorMapper(), new Object[] {icono});
			LOGGER.info("HAY Colores con ese icono");
		}catch(EmptyResultDataAccessException e){
			color = null;
			LOGGER.info("No hay Colores con ese icono");
		}
		return color;
	}


	@Override
	public List<Color> getBycarta(int codigo) {
		final String SQL = "CALL colorgetBycarta(?)";
		List<Color> color = null;
		try{
			color = template.query(SQL, new CartaColorMapper(),
					new Object[] { codigo }) ;
			LOGGER.info("HAY cartas con ese color");
		}catch(EmptyResultDataAccessException e){
			color = new ArrayList<Color>();
			LOGGER.info("NO HAY cartas con ese color");
		}
		return color;
	}

}
