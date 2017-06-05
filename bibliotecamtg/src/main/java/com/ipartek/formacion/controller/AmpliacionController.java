package com.ipartek.formacion.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ipartek.formacion.controller.exception.AmpliacionNoBorradaException;
import com.ipartek.formacion.controller.exception.AmpliacionNoEncontradoException;
import com.ipartek.formacion.controller.pojo.Mensaje;
import com.ipartek.formacion.controller.pojo.MensajeType;
import com.ipartek.formacion.dbms.persistence.Ampliacion;
import com.ipartek.formacion.dbms.persistence.Carta;
import com.ipartek.formacion.service.interfaces.AmpliacionService;


/**
 * 
 * @author Enrique J. Ruiz
 *
 */


@Controller
@RequestMapping(value = "/ampliaciones")
public class AmpliacionController {
	@Inject
	private AmpliacionService aS;
	private static final Logger LOGGER = LoggerFactory.getLogger(CartaController.class);
	@Resource(name="ampliacionValidator")
	private Validator validator = null;
	ModelAndView mav = null;

	@Autowired
	private ServletContext servletContext;
	@InitBinder("ampliacion")
	private void initBinder(WebDataBinder binder,Locale locale){
		DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, locale);
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		binder.addValidators(validator);
	}
	
	@InitBinder("imgampliacion")
	public void initBinder(WebDataBinder binder) {
		//binder.addValidators(new FileValidator());
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getAll(){
		mav= new ModelAndView("ampliaciones");
		List<Ampliacion> ampliaciones = aS.getAll();
		mav.addObject("listadoampliaciones",ampliaciones);
		LOGGER.trace("realiza getAll() de ampliaciones");
		return mav;
	}

	@RequestMapping(value="/verAmpliacion/{id}")
	public ModelAndView verAmpliacion(@PathVariable("id") int id){
		LOGGER.info("llega aqui");
		mav = new ModelAndView("contenidoampliacion");
		Ampliacion ampliacion = aS.getById(id);
		mav.addObject("ampliacion", ampliacion);//request
		mav.addObject("listadocartas",aS.getByAmpliacion(id));
		return mav;
	}
	
	@RequestMapping(value="/addAmpliacion", method = RequestMethod.GET)
	public ModelAndView addAmpliacion(Model model){
		model.addAttribute("ampliacion", new Ampliacion());
		mav= new ModelAndView("ampliacionform");
		List<Ampliacion> ampliaciones = aS.ampliacionPricipalGetAll();
		mav.addObject("listadoampliaciones", ampliaciones);
		return mav;
	}
	
	@RequestMapping(value="save", method = RequestMethod.POST)
	public String saveAmpliacion(@Valid @RequestPart("imgampliacion") MultipartFile fileimg,
			@ModelAttribute("ampliacion")@Valid Ampliacion ampliacion,BindingResult bindingResult,ModelMap model,
			RedirectAttributes redirectMap) throws IOException {
		String destino = "";
		String txt="";
		Mensaje mensaje = null;
		
		if (bindingResult.hasErrors()) {
			LOGGER.info("Ampliacion tiene errores");
			model.addAttribute("listadoampliaciones",aS.cartaAmpliacionGetAll());
			destino = "ampliacion";
			txt = "Los datos de formulario contienen errores";
			mensaje = new Mensaje(MensajeType.MSG_TYPE_DANGER);
			
		}else{
			destino = "redirect:/ampliaciones";
			if(fileimg.getOriginalFilename().isEmpty() == false){
				InputStream in = fileimg.getInputStream();
				String root= File.separator +"resources" +File.separator + "images" +File.separator + "expansion" +File.separator + "logo" +File.separator ;
				LOGGER.info("root: "+root);
				String ruta = servletContext.getRealPath(root);// si esta
																// desplegado en
																// wilfly
				// String ruta
				// ="C:\\Desarrollo\\svn\\bibliotecamtg\\src\\main\\webapp";
				File destination = new File(ruta + File.separator + fileimg.getOriginalFilename());
				LOGGER.info("destination: "+destination);
				FileUtils.copyInputStreamToFile(in,  destination);
				LOGGER.info("ruta: "+ruta);
				ampliacion.setImagen(fileimg.getOriginalFilename());
				LOGGER.info("filname: "+fileimg.getOriginalFilename());
			}
			/*if(file.getOriginalFilename().isEmpty() == false){
				InputStream in = file.getInputStream();
				String root= File.separator +"resources" +File.separator + "images" +File.separator + "colors" +File.separator ;
				LOGGER.info("root: "+root);
				//String ruta = servletContext.getRealPath(root);//si esta desplegado en wilfly
				String ruta ="C:\\Desarrollo\\svn\\bibliotecamtg\\src\\main\\webapp";
				File destination = new File(ruta + root +file.getOriginalFilename());
				LOGGER.info("destination: "+destination);
				FileUtils.copyInputStreamToFile(in,  destination);
				LOGGER.info("ruta: "+ruta);
				ampliacion.setIcono(file.getOriginalFilename());
				LOGGER.info("filname: "+file.getOriginalFilename());
			}*/
			if(ampliacion.getCodigo() > Ampliacion.CODIGO_NULO){
				try{
					LOGGER.info(ampliacion.toString());
					aS.update(ampliacion);
					txt = "La Expansión se ha actualizado correctamente.";
					mensaje = new Mensaje(MensajeType.MSG_TYPE_SUCCESS);
				} catch (Exception e) {
					LOGGER.info("Se ha lanzadado una excepcion update. " + e.toString());
					mensaje = new Mensaje(MensajeType.MSG_TYPE_DANGER);
					txt = "Ha habido problemas en la actualización.";
				}
			}else{
				try{
					LOGGER.info(ampliacion.toString());
					aS.create(ampliacion);
					txt = "La Expansión se ha creado correctamente.";
					mensaje = new Mensaje(MensajeType.MSG_TYPE_SUCCESS);
					
				} catch (Exception e) {
					LOGGER.info("Se ha lanzadado una excepcion create. " + e.toString());
					mensaje = new Mensaje(MensajeType.MSG_TYPE_DANGER);
					txt = "Ha habido problemas al crear.";
				}
			}
			mensaje.setMsg(txt);
			redirectMap.addFlashAttribute("mensaje", mensaje);
		}
		return destino;
		
	}
	
	@RequestMapping(value="/deleteAmpliacion/{id}", method = RequestMethod.GET)
	public ModelAndView deleteCarta(@PathVariable("id") int id,RedirectAttributes redirectMap){
		String destino = "";
		String txt="";
		Mensaje mensaje = null;
		LOGGER.info(Integer.toString(id));
		destino="redirect:/ampliaciones";
		mav= new ModelAndView(destino);
		try{
		aS.delete(id);		
		 			txt = "La expansión se ha borrado correctamente.";		
		 			mensaje = new Mensaje(MensajeType.MSG_TYPE_SUCCESS);		
		 		} catch (Exception e) {		
		 			LOGGER.info("Se ha lanzadado una excepcion Delete. " + e.toString());		
		 			mensaje = new Mensaje(MensajeType.MSG_TYPE_DANGER);		
		 			txt = "Ha habido problemas al borrar la Expansion porque hay cartas asociadas.";		
		 		} 		
		mensaje.setMsg(txt);
		redirectMap.addFlashAttribute("mensaje", mensaje);
		return mav;
				
	}
	
	@RequestMapping(value="/{id}")
	public ModelAndView getById(@PathVariable("id") int id) throws AmpliacionNoEncontradoException{
		Ampliacion ampliacion=aS.getById(id); 
		mav.addObject("ampliacion",ampliacion);
		List<Ampliacion> ampliaciones = aS.cartaAmpliacionGetAll();
		mav.addObject("listadoampliaciones", ampliaciones);
		
		mav= new ModelAndView("ampliacionform");
		return mav;
	}
	
	@ExceptionHandler(AmpliacionNoEncontradoException.class)
	public ModelAndView handleAmpliacionNoEncontradoExcepcion(HttpServletRequest request, Exception ex){
		ModelAndView mav = null;
		LOGGER.error("URL pedida"+request.getRequestURL());
		LOGGER.error("Excepcion lanzada"+ ex);
		mav = new ModelAndView();
		mav.addObject("exception", ex);
		mav.addObject("url", request.getRequestURL());
		mav.setViewName("error");
		return mav;
	}
	
	

}
