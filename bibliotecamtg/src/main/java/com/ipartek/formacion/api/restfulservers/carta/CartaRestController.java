package com.ipartek.formacion.api.restfulservers.carta;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ipartek.formacion.dbms.persistence.Carta;
import com.ipartek.formacion.service.interfaces.CartaService;

@RestController
@RequestMapping(value = "/api/cartas")
public class CartaRestController {
	@Inject
	CartaService cS;
	
	@InitBinder
	 protected void initBinder(WebDataBinder binder) {
	 		
	 }
	@RequestMapping(value = "/{codigo}", 
			method = RequestMethod.DELETE, 
			produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Carta> deleteCarta(@PathVariable("codigo") int id){
		Carta carta = cS.getById(id);
		ResponseEntity<Carta> response = null;
		if (carta == null) {
 			response = new ResponseEntity<Carta>(HttpStatus.NOT_FOUND);
 		} else {
 			cS.delete(id);
 			response = new ResponseEntity<Carta>(carta, HttpStatus.NO_CONTENT);
 		}
		
		return response;
	}
	@RequestMapping(method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
 	public ResponseEntity<Map<Long, Carta>> getAll() {
 		Map<Long, Carta> cartas = cS.getAll();
 		ResponseEntity<Map<Long, Carta>> response = null;
 
 		if (cartas == null || cartas.isEmpty()) {
 			response = new ResponseEntity<Map<Long, Carta>>(HttpStatus.NO_CONTENT);
 		} else {
 			response = new ResponseEntity<Map<Long, Carta>>(cartas, HttpStatus.OK);
 		}
 
 		return response;
  	}
	
	@RequestMapping(value = "/{codigo}", 
			method = RequestMethod.GET,
			produces =   MediaType.APPLICATION_JSON_VALUE)
  	public ResponseEntity<Carta> getById(@PathVariable("codigo") int id) {
		Carta carta = cS.getById(id);
 		ResponseEntity<Carta> response = null;
 
 		if (carta == null) {// 404
 			response = new ResponseEntity<Carta>(HttpStatus.NOT_FOUND);
 		} else {// 200
 			response = new ResponseEntity<Carta>(carta, HttpStatus.OK);
 		}
 
 		return response;
 	}
	@RequestMapping(value = "/{codigo}", 
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Carta> update(@PathVariable("codigo") int id,@RequestBody Carta carta){
		Carta cat = cS.getById(id);
		ResponseEntity<Carta> response = null;
		if(cat == null){
			response= new ResponseEntity<Carta> (HttpStatus.NOT_FOUND);
		}else{
			cat = cS.update(carta);
			response = new ResponseEntity<Carta>(cat, HttpStatus.ACCEPTED);
		}
		return response;
	}
	
	@RequestMapping( method = RequestMethod.POST, 
			consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, 
			produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Void> create(@Valid @RequestBody Carta carta, UriComponentsBuilder ucBuilder){
		ResponseEntity<Void> response=null;
		try{
			Carta aux = cS.create(carta);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/api/ampliaciones/{codigo}").buildAndExpand(aux.getCodigo()).toUri());
			response = new ResponseEntity<Void>(headers,HttpStatus.CREATED);
		}catch(Error e){
			response = new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		return response;
	}
	
}
