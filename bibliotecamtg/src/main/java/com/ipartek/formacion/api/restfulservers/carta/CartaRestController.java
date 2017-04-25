package com.ipartek.formacion.api.restfulservers.carta;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
	@RequestMapping(value = "/{codigo}", method = RequestMethod.DELETE)
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

	
	
}
