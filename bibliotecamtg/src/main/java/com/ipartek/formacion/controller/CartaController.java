package com.ipartek.formacion.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.ServletContext;
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
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ipartek.formacion.controller.pojo.Mensaje;
import com.ipartek.formacion.controller.pojo.MensajeType;
import com.ipartek.formacion.dbms.persistence.Ampliacion;
import com.ipartek.formacion.dbms.persistence.Carta;
import com.ipartek.formacion.dbms.persistence.Color;
import com.ipartek.formacion.service.interfaces.AmpliacionService;
import com.ipartek.formacion.service.interfaces.CartaService;
import com.ipartek.formacion.service.interfaces.ColorService;

@Controller
@RequestMapping(value = "/cartas")
public class CartaController {
	@Inject
	private CartaService cS;
	private static final Logger LOGGER = LoggerFactory.getLogger(CartaController.class);
	@Autowired 
	private AmpliacionService aS;
	@Autowired
	private ColorService coS;
	ModelAndView mav = null;
	@Resource(name="cartaValidator")
	private Validator validator = null;
	@Autowired
	private ServletContext servletContext;
	@InitBinder("carta")
	private void initBinder(WebDataBinder binder, Locale locale){
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		binder.addValidators(validator);
	}
	@InitBinder("imgcarta")
	public void initBinder(WebDataBinder binder) {
		//binder.addValidators(new FileValidator());
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getAll(){
		mav= new ModelAndView("cartas");
		Map<Long,Carta> cartas = cS.getAll();
		mav.addObject("listadocartas", cartas);
		LOGGER.trace("realiza getAll() de cartas");
		return mav;
	}
	
	@RequestMapping(value="/addCarta", method = RequestMethod.GET)
	public ModelAndView addAlumno(Model model){
		model.addAttribute("carta", new Carta());
		mav= new ModelAndView("carta");
		List<Color> colores = coS.getAll();
		mav.addObject("listadocolores", colores);
		List<Ampliacion> ampliaciones = aS.cartaAmpliacionGetAll();
		mav.addObject("listadoampliaciones", ampliaciones);
		LOGGER.trace("realiza getAll() de ampliaciones");
		return mav;
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String saveCarta(@Valid @RequestPart("imgcarta") MultipartFile fileimg,
			@ModelAttribute("carta") @Valid Carta carta,BindingResult bindingResult, ModelMap model,
			RedirectAttributes redirectMap) throws IOException { //validate spring valid java
		String destino = "";
		String txt="";
		Mensaje mensaje = null;
		
		if (bindingResult.hasErrors()) {
			LOGGER.info("Carta tiene errores");
			model.addAttribute("listadoampliaciones",aS.cartaAmpliacionGetAll());
			model.addAttribute("listadocolores", coS.getAll());
			destino = "carta";	
			txt = "Los datos de formulario contienen errores";
			mensaje = new Mensaje(MensajeType.MSG_TYPE_DANGER);
		}else{
			destino = "redirect:/cartas";
			if(fileimg.getOriginalFilename().isEmpty() == false){
				InputStream in = fileimg.getInputStream();
				String root= File.separator +"resources" +File.separator + "images" +File.separator + "cards" +File.separator ;
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
				carta.setImagen(fileimg.getOriginalFilename());
				LOGGER.info("filname: "+fileimg.getOriginalFilename());
			}
			
			if(carta.getCodigo() > Carta.CODIGO_NULO){
				try{
					LOGGER.info(carta.toString());
					cS.update(carta);
					txt = "La carta se ha actualizado correctamente.";
					mensaje = new Mensaje(MensajeType.MSG_TYPE_SUCCESS);
				} catch (Exception e) {
					LOGGER.info("Se ha lanzadado una excepcion update. " + e.toString());
					mensaje = new Mensaje(MensajeType.MSG_TYPE_DANGER);
					txt = "Ha habido problemas en la actualización.";
				}
			}else{
				try{
					LOGGER.info(carta.toString());
					cS.create(carta);
					txt = "La carta se ha creado correctamente.";
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
	
	@RequestMapping(value="/deleteCarta/{id}", method = RequestMethod.GET)
	public ModelAndView deleteCarta(@PathVariable("id") long id, RedirectAttributes redirectMap){
		String destino = "";
		String txt="";
		Mensaje mensaje = null;
	
		LOGGER.info(Long.toString(id));
		destino = "redirect:/cartas";
		mav= new ModelAndView(destino);
		
		try {
			cS.delete(id);
			txt = "La carta se ha borrado correctamente.";
			mensaje = new Mensaje(MensajeType.MSG_TYPE_SUCCESS);
		} catch (Exception e) {
			LOGGER.info("Se ha lanzadado una excepcion Delete. " + e.toString());
			mensaje = new Mensaje(MensajeType.MSG_TYPE_DANGER);
			txt = "Ha habido problemas al borrar la carta.";
		} 
	
		mensaje.setMsg(txt);
		redirectMap.addFlashAttribute("mensaje", mensaje);
		
		return mav;
	}
	
	@RequestMapping(value="/{id}")
	public ModelAndView getById(@PathVariable("id") int id){
		mav= new ModelAndView("carta");
		mav.addObject("carta",cS.getById(id));
		
		List<Color> colores = coS.getAll();
		mav.addObject("listadocolores", colores);
		List<Ampliacion> ampliaciones = aS.cartaAmpliacionGetAll();
		mav.addObject("listadoampliaciones", ampliaciones);
		LOGGER.trace("realiza getById de ampliaciones");
		return mav;
	}
	
	@RequestMapping(value="/verCarta/{id}")
	public ModelAndView getVerCartaById(@PathVariable("id") int id){
		mav= new ModelAndView("contenidocarta");
		mav.addObject("carta",cS.getById(id));
		return mav;
	}
}
