package com.ipartek.formacion.api.restfulservers.color;

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

import com.ipartek.formacion.dbms.persistence.Color;
import com.ipartek.formacion.service.interfaces.ColorService;

@RestController
@RequestMapping(value = "/api/colores")
public class ColorRestController {

	@Autowired
	ColorService coS;

	@InitBinder
	 protected void initBinder(WebDataBinder binder) {
	 		
	}
	
	@RequestMapping(value = "/{codigo}", 
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
  	public ResponseEntity<Color> getById(@PathVariable("codigo") int id) {
  		Color color = coS.getById(id);
 		ResponseEntity<Color> response = null;
 
 		if (color == null) {// 404
 			response = new ResponseEntity<Color>(HttpStatus.NOT_FOUND);
 		} else {// 200
 			response = new ResponseEntity<Color>(color, HttpStatus.OK);
 		}
 
 		return response;
 	}
 
 	@RequestMapping(method = RequestMethod.GET,
 			produces = MediaType.APPLICATION_JSON_VALUE)
 	public ResponseEntity<List<Color>> getAll() {
 		List<Color> colores = coS.getAll();
 		ResponseEntity<List<Color>> response = null;
 
 		if (colores == null || colores.isEmpty()) {
 			response = new ResponseEntity<List<Color>>(HttpStatus.NO_CONTENT);
 		} else {
 			response = new ResponseEntity<List<Color>>(colores, HttpStatus.OK);
 		}
 
 		return response;
  	}
 	@RequestMapping(value = "/{codigo}",
 			method = RequestMethod.DELETE,
 			produces = { MediaType.APPLICATION_JSON_VALUE,
 					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Color> deleteColor(@PathVariable("codigo") int id){
 		Color color= coS.getById(id);
 		ResponseEntity<Color> response = null;
 		if (color == null) {
 			response = new ResponseEntity<Color>(HttpStatus.NOT_FOUND);
 		} else {
 			coS.delete(id);
 			response = new ResponseEntity<Color>(color, HttpStatus.NO_CONTENT);
 		}
 		return response;
 	}
	@RequestMapping(value = "/{codigo}", 
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Color> update(@PathVariable("codigo") int id,@RequestBody Color color){
		Color col = coS.getById(id);
		ResponseEntity<Color> response = null;
		if(col == null){
			response= new ResponseEntity<Color> (HttpStatus.NOT_FOUND);
		}else{
			col = coS.update(color);
			response = new ResponseEntity<Color>(col, HttpStatus.ACCEPTED);
		}
		return response;
	}
	
	@RequestMapping( method = RequestMethod.POST,
			consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE }, 
			produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Void> create(@Valid @RequestBody Color color, UriComponentsBuilder ucBuilder){
		ResponseEntity<Void> response=null;
		try{
			Color aux = coS.create(color);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/api/colores/{codigo}").buildAndExpand(aux.getCodigo()).toUri());
			response = new ResponseEntity<Void>(headers,HttpStatus.CREATED);
		}catch(Error e){
			response = new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		return response;
	}
}
