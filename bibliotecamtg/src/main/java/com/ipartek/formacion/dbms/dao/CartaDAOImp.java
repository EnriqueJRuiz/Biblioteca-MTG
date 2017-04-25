package com.ipartek.formacion.dbms.dao;

import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import com.ipartek.formacion.dbms.dao.interfaces.CartaDAO;
import com.ipartek.formacion.dbms.mapper.CartaExtractor;
import com.ipartek.formacion.dbms.persistence.Carta;

public class CartaDAOImp implements CartaDAO {
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
	public Carta create(Carta carta) {
		final String SQL="cartaCreate";
		final String SQLNM = "cartaColorCreate";
		this.jdbcCall = new SimpleJdbcCall(dataSource);
		jdbcCall.withProcedureName(SQL);
		SqlParameterSource in = new MapSqlParameterSource()
				.addValue("pnombre", carta.getNombre())
				.addValue("pnumero", carta.getNumero())
				.addValue("pcosteDeMana", carta.getCosteDeMana())
				.addValue("pimagen",carta.getImagen())
				.addValue("ptexto",carta.getTexto())
				.addValue("prareza",carta.getRareza())
				.addValue("psubtipo", carta.getSubtipo())
				.addValue("psupertipo", carta.getSupertipo())
				.addValue("ptipo", carta.getTipo())
				.addValue("pcodigo_ampliacion", carta.getAmpliacion().getCodigo());
		Map<String, Object> out = jdbcCall.execute(in);
		carta.setCodigo((Integer)out.get("pcodigo"));
		
		this.jdbcCall = new SimpleJdbcCall(dataSource);
		jdbcCall.withProcedureName(SQLNM);
		
		for(int i = 0 ; i<carta.getColores().size(); i++){
			SqlParameterSource inNM = new MapSqlParameterSource()
					.addValue("pcarta_codigo", carta.getCodigo())
					.addValue("pcolor_codigo", carta.getColores()
							.get(i).getCodigo());
				LOGGER.info("Carta: "+ carta.getCodigo() 
				+ ", color"+carta.getColores().get(i).getCodigo());
			jdbcCall.execute(inNM);		
		}
		return carta;
	}

	@Override
	public Carta getById(long codigo) {
		final String SQL = "CALL cartagetById(?)";
		Carta carta = null;
		try{
			Map<Long, Carta> cartas =  template.query(SQL, 
					new CartaExtractor(), new Object[] { codigo });
			carta = cartas.get(codigo);
			LOGGER.info("CartaRestController: "+ toString());
		}catch(EmptyResultDataAccessException e){
			carta = new Carta();
			 LOGGER.info("no se he encontrado Carta: "+ codigo + " "+e.getMessage());
		}
		return carta;
	}

	@Override
	public Map<Long, Carta> getAll() {
		final String SQL = "CALL cartagetAllOnly();";
		Map<Long, Carta> cartas = null;
		try{
			 cartas = template.query(SQL, 
					 new CartaExtractor(), new Object[]{});		
		}catch(EmptyResultDataAccessException e){
			LOGGER.trace(e.getMessage());	
		}
		return cartas;
	}

	@Override
	public void delete(long codigo) {
		final String SQL= "cartaDeleteF";
		final String SQLNM= "cartaColorDeleteF";
		this.jdbcCall = new SimpleJdbcCall(dataSource);
		jdbcCall.withProcedureName(SQL);
		SqlParameterSource in = new MapSqlParameterSource()
				.addValue("pcodigo", codigo);
		LOGGER.info(String.valueOf(codigo));
		jdbcCall.execute(in);
		
		this.jdbcCall = new SimpleJdbcCall(dataSource);
		jdbcCall.withProcedureName(SQLNM);
		SqlParameterSource inNM = new MapSqlParameterSource()
				.addValue("pcodigo", codigo);
		jdbcCall.execute(inNM);
		
		
	}


	@Override
	public Carta update(Carta carta) {
		final String SQL="cartaUpdate";
		final String SQLNMD= "cartaColorDeleteF";
		final String SQLNMC = "cartaColorCreate";
		this.jdbcCall = new SimpleJdbcCall(dataSource);
		jdbcCall.withProcedureName(SQL);
		
		SqlParameterSource in = new MapSqlParameterSource()
				.addValue("pnombre", carta.getNombre())
				.addValue("pnumero", carta.getNumero())
				.addValue("pcosteDeMana", carta.getCosteDeMana())
				.addValue("pimagen",carta.getImagen())
				.addValue("ptexto",carta.getTexto())
				.addValue("prareza",carta.getRareza())
				.addValue("psubtipo", carta.getSubtipo())
				.addValue("psupertipo", carta.getSupertipo())
				.addValue("ptipo", carta.getTipo())
				.addValue("pcodigo_ampliacion", carta.getAmpliacion().getCodigo())
				.addValue("pcodigo", carta.getCodigo());
		jdbcCall.execute(in);
		//primero borramos los registros en la tabla NM
		this.jdbcCall = new SimpleJdbcCall(dataSource);
		jdbcCall.withProcedureName(SQLNMD);
		SqlParameterSource inNMD = new MapSqlParameterSource()
				.addValue("pcodigo", carta.getCodigo());
		jdbcCall.execute(inNMD);
		//despues creamos todos los registros de nuevo NM
		this.jdbcCall = new SimpleJdbcCall(dataSource);
		jdbcCall.withProcedureName(SQLNMC);
		
		for(int i = 0 ; i<carta.getColores().size(); i++){
			SqlParameterSource inNMC = new MapSqlParameterSource()
					.addValue("pcarta_codigo", carta.getCodigo())
					.addValue("pcolor_codigo", carta.getColores()
							.get(i).getCodigo());
				LOGGER.info("Carta: "+ carta.getCodigo() 
				+ ", color"+carta.getColores().get(i).getCodigo());
			jdbcCall.execute(inNMC);	
		}
		
		return carta;
	}

	@Override
	public Map<Long, Carta> cartaByAmpliacion(int id) {
		final String SQL = "CALL cartaByAmpliacion(?)";
		Map<Long, Carta> cartas = null;
		try{
			cartas =  template.query(SQL, 
					new CartaExtractor(), new Object[] { id });
			
			LOGGER.info("Hay cartas en la ampliacion");
		}catch(EmptyResultDataAccessException e){
			 LOGGER.info("no se he encontrado Carta: "+ id + " "+e.getMessage());
			 
		}
		return cartas;
	}


}
