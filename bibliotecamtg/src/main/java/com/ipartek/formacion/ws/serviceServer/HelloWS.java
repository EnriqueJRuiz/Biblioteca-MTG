package com.ipartek.formacion.ws.serviceServer;

import javax.jws.WebService;

@WebService
public class HelloWS {
	public String hello(String name){
		return "hello "+name;
		
	}
	
}
