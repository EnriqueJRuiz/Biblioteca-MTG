package com.ipartek.formacion.dbms.dao;

import java.sql.Types;
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

import com.ipartek.formacion.dbms.dao.interfaces.AmpliacionDAO;
import com.ipartek.formacion.dbms.mapper.AmpliacionExteractor;
import com.ipartek.formacion.dbms.mapper.AmpliacionMapper;
import com.ipartek.formacion.dbms.mapper.CartaExtractor;
import com.ipartek.formacion.dbms.persistence.Ampliacion;
import com.ipartek.formacion.dbms.persistence.Carta;

public class AmpliacionDAOImp implements AmpliacionDAO {
	private DataSource dataSource;
	private SimpleJdbcCall jdbcCall;
	private JdbcTemplate template;
	private static final Logger LOGGER = LoggerFactory.getLogger(CartaDAOImp.class);
		
	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.template = new JdbcTemplate(dataSource);
		
	}

	@Override
	public Ampliacion create(Ampliacion ampliacion) {
		final String SQL="ampliacionCreate";
		this.jdbcCall = new SimpleJdbcCall(dataSource);
		jdbcCall.withProcedureName(SQL);
		SqlParameterSource in = new MapSqlParameterSource()
				.addValue("pnombre", ampliacion.getNombre())
				.addValue("pflanzamiento", ampliacion.getfLanzamiento())
				.addValue("picono", ampliacion.getIcono())
				.addValue("pimagen", ampliacion.getImagen())
				.addValue("psiglas", ampliacion.getSiglas())
				.addValue("pprincipal", ampliacion.getPrincipal().getCodigo())
				.addValue("pespecial",ampliacion.getEspecial(),Types.BOOLEAN)
				.addValue("pbasica",ampliacion.getBasica(),Types.BOOLEAN);
				
		Map<String, Object> out = jdbcCall.execute(in);	
		ampliacion.setCodigo((Integer)out.get("pcodigo"));
		
		if(ampliacion.getPrincipal().getCodigo()<=0){
			final String SQLup="ampliacionUpdatePrincipal";
			this.jdbcCall = new SimpleJdbcCall(dataSource);
			jdbcCall.withProcedureName(SQLup);
			SqlParameterSource inup = new MapSqlParameterSource()
					.addValue("pcodigo", ampliacion.getCodigo());
			jdbcCall.execute(inup);	
		}	
		return ampliacion;
	}

	@Override
	public Ampliacion getById(int codigo) {
		final String SQL = "CALL ampliaciongetById(?)";
		Ampliacion ampliacion = null;
		try{
			ampliacion = template.queryForObject(SQL, 
					new AmpliacionMapper(),new Object[] { codigo }) ;
		}catch(EmptyResultDataAccessException e){
			ampliacion = new Ampliacion();
		}
		return ampliacion;
	}

	@Override
	public int delete(long codigo) {
		int valor=0;
		final String SQL= "ampliacionDeleteF";
		this.jdbcCall = new SimpleJdbcCall(dataSource);
		jdbcCall.withProcedureName(SQL);
		SqlParameterSource in = new MapSqlParameterSource()
				.addValue("pcodigo", codigo);
		LOGGER.info(String.valueOf(codigo));
		jdbcCall.execute(in);
		LOGGER.info("borrar colorDao");
		
		return valor;
		
	}

	@Override
	public List<Ampliacion> ampliacionPricipalGetAll() {
		final String SQL = "CALL ampliaciongetPrincial();";
		List<Ampliacion> ampliaciones = null;
		try {
			 ampliaciones = template.query(SQL, new AmpliacionMapper());		
		}catch(EmptyResultDataAccessException e){
			LOGGER.trace(e.getMessage());	
		}
		return ampliaciones;
	}
	
	@Override
	public List<Ampliacion> getAll() {
		final String SQL="CALL ampliaciongetAll()";
		List<Ampliacion> ampliaciones= null;
		try {
			ampliaciones= template.query(SQL, new AmpliacionMapper());
		} catch(EmptyResultDataAccessException e) {
			LOGGER.trace(e.getMessage());	
		}
		return ampliaciones;
	}

	@Override
	public Ampliacion cartasGetById(int codigo) {
		final String SQL = "CALL ampliacionCartaContenido(?);";
		Ampliacion ampliacion = null;
		try{
			Map<Integer, Ampliacion> ampliaciones = template.query(SQL, 
					new AmpliacionExteractor(), new Object[] { codigo });
			ampliacion = ampliaciones.get(codigo);
			LOGGER.info("Ampliacion: " +toString());
			 
		} catch (EmptyResultDataAccessException e){
			ampliacion = null;
			LOGGER.info("sin datos " + e.getMessage() + " " +SQL);
		}
		return ampliacion;
	}

	@Override
	public Ampliacion update(Ampliacion ampliacion) {
		final String SQL="ampliacionUpdate";
		this.jdbcCall = new SimpleJdbcCall(dataSource);
		jdbcCall.withProcedureName(SQL);
		SqlParameterSource in = new MapSqlParameterSource()
				.addValue("pnombre", ampliacion.getNombre())
				.addValue("pflanzamiento", ampliacion.getfLanzamiento())
				.addValue("picono", ampliacion.getIcono())
				.addValue("pimagen", ampliacion.getImagen())
				.addValue("psiglas", ampliacion.getSiglas())
				.addValue("pprincipal", ampliacion.getPrincipal().getCodigo())
				.addValue("pespecial",ampliacion.getEspecial())
				.addValue("pbasica",ampliacion.getBasica())
				.addValue("pcodigo",ampliacion.getCodigo());
		jdbcCall.execute(in);
		return ampliacion;
	}

	@Override
	public List<Ampliacion> cartaAmpliacionGetAll() {
		final String SQL = "CALL AmpliacionGetAll();";
		List<Ampliacion> ampliaciones = null;
		try{
			 ampliaciones = template.query(SQL, new AmpliacionMapper());		
		}catch(EmptyResultDataAccessException e){
			LOGGER.trace(e.getMessage());	
		}
		return ampliaciones;
	}

	@Override
	public List<Ampliacion> getAllByNombre() {
		final String SQL = "CALL ampliaciongetByNombre();";
		List<Ampliacion> ampliaciones = null;
		try{
			 ampliaciones = template.query(SQL, new AmpliacionMapper());		
		}catch(EmptyResultDataAccessException e){
			LOGGER.trace(e.getMessage());	
		}
		return ampliaciones;
	}

	@Override
	public Map<Long, Carta> getByAmpliacion(int codigo) {
		final String SQL = "CALL cartagetByAmpliacion(?)";
		Map<Long, Carta> cartas = null;
		try{
			cartas =  template.query(SQL, 
					new CartaExtractor(), new Object[] { codigo });
		}catch(EmptyResultDataAccessException e){
			LOGGER.trace(e.getMessage());	
		}
		return cartas;
	}
	

}
