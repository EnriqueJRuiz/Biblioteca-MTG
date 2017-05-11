package com.ipartek.formacion.api.restfulservers.ampliacion;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.ipartek.formacion.dbms.persistence.Ampliacion;
import com.ipartek.formacion.service.interfaces.AmpliacionService;


@RestController
@RequestMapping(value = "/api/ampliacipones")
public class AmpliacionRestController {

	@Autowired
	AmpliacionService aS;

	@InitBinder
	 protected void initBinder(WebDataBinder binder) {
	 		
	}
	
	@RequestMapping(value = "/{codigo}", method = RequestMethod.GET,produces =   MediaType.APPLICATION_JSON_VALUE)
  	public ResponseEntity<Ampliacion> getById(@PathVariable("codigo") int id) {
  		Ampliacion ampliacion = aS.getById(id);
 		ResponseEntity<Ampliacion> response = null;
 
 		if (ampliacion == null) {// 404
 			response = new ResponseEntity<Ampliacion>(HttpStatus.NOT_FOUND);
 		} else {// 200
 			response = new ResponseEntity<Ampliacion>(ampliacion, HttpStatus.OK);
 		}
 
 		return response;
 	}
 
 	@RequestMapping(method = RequestMethod.GET,produces =   MediaType.APPLICATION_JSON_VALUE)
 	public ResponseEntity<List<Ampliacion>> getAll() {
 		List<Ampliacion> ampliaciones = aS.getAll();
 		ResponseEntity<List<Ampliacion>> response = null;
 
 		if (ampliaciones == null || ampliaciones.isEmpty()) {
 			response = new ResponseEntity<List<Ampliacion>>(HttpStatus.NO_CONTENT);
 		} else {
 			response = new ResponseEntity<List<Ampliacion>>(ampliaciones, HttpStatus.OK);
 		}
 
 		return response;
  	}
 	/*
 	@RequestMapping(value="/ordenNombre",method = RequestMethod.GET)
  	public ResponseEntity<List<Ampliacion>> ampliaciongetByNombre(){
		List<Ampliacion> ampliaciones = aS.getAllByNombre();
 		ResponseEntity<List<Ampliacion>> response = null;
 
 		if (ampliaciones == null || ampliaciones.isEmpty()) {
 			response = new ResponseEntity<List<Ampliacion>>(HttpStatus.NO_CONTENT);
 		} else {
 			response = new ResponseEntity<List<Ampliacion>>(ampliaciones, HttpStatus.OK);
 		}
 
 		return response;
 	}*/
 	
 	@RequestMapping(value="/{codigo}", method = RequestMethod.DELETE)
 	public ResponseEntity<Ampliacion> deleteAmpliacion(@PathVariable("codigo") int id){
 		Ampliacion ampliacion = aS.getById(id);
 		ResponseEntity<Ampliacion> response = null;
 		if (ampliacion == null) {
 			response = new ResponseEntity<Ampliacion>(HttpStatus.NOT_FOUND);
 		} else {
 			aS.delete(id);
 			response = new ResponseEntity<Ampliacion>(ampliacion, HttpStatus.NO_CONTENT);
 		}
		return response;
 	}
	@RequestMapping(value = "/{codigo}", method = RequestMethod.POST,produces =MediaType.APPLICATION_JSON_VALUE,consumes =MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Ampliacion> update(@PathVariable("codigo") int id,@RequestBody Ampliacion ampliacion){
		Ampliacion ampli = aS.getById(id);
		ResponseEntity<Ampliacion> response = null;
		if(ampli == null){
			response= new ResponseEntity<Ampliacion> (HttpStatus.NOT_FOUND);
		}else{
			ampli = aS.update(ampliacion);
			response = new ResponseEntity<Ampliacion>(ampli, HttpStatus.ACCEPTED);
		}
		return response;
	}
	
	@RequestMapping( method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Void> create(@Valid @RequestBody Ampliacion ampliacion, UriComponentsBuilder ucBuilder){
		ResponseEntity<Void> response=null;
		try{
			Ampliacion aux = aS.create(ampliacion);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/api/ampliaciones/{codigo}").buildAndExpand(aux.getCodigo()).toUri());
			response = new ResponseEntity<Void>(headers,HttpStatus.CREATED);
		}catch(Error e){
			response = new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		return response;
	}
}

