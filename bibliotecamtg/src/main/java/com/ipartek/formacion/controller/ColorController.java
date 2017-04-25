package com.ipartek.formacion.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
//import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.ipartek.formacion.dbms.persistence.Color;
import com.ipartek.formacion.service.interfaces.ColorService;

@Controller
@RequestMapping(value = "/colores")
public class ColorController {
	@Autowired
	private ServletContext servletContext;
	private static final Logger LOGGER = LoggerFactory.getLogger(ColorController.class);
	@Autowired
	private ColorService coS;
	@Resource(name="colorValidator")
	private Validator validator = null;
	ModelAndView mav = null;
	
	@InitBinder("color")
	public void initBinder(WebDataBinder binder, Locale locale) {
		binder.addValidators(validator);
	}
	@InitBinder("fichero")
	public void initBinder(WebDataBinder binder) {
		//binder.addValidators(new FileValidator());
	}
			
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getAll(){
		mav= new ModelAndView("colores");
		
		List<Color> colores = coS.getAll();
		
		mav.addObject("listadocolores", colores);
		LOGGER.trace("realiza getAll() de colores");
		return mav;
	}
	
	@RequestMapping(value="/{id}")
	public ModelAndView getById(@PathVariable("id") int id){
		mav= new ModelAndView("color");
		mav.addObject("color",coS.getById(id));
		LOGGER.trace("realiza getById de colores");
		return mav;
	}

	@RequestMapping(value="/deleteColor/{id}", method = RequestMethod.GET)
	public ModelAndView deleteColor(@PathVariable("id") int id, RedirectAttributes redirectMap){
		String destino = "";
		String txt="";
		Mensaje mensaje = null;
		LOGGER.info(Integer.toString(id));
		
		destino = "redirect:/colores";
		mav= new ModelAndView(destino);
		
		try {	
			coS.delete(id);
			txt = "El Color se ha borrado correctamente.";
			mensaje = new Mensaje(MensajeType.MSG_TYPE_SUCCESS);
		} catch (Exception e) {
			LOGGER.info("Se ha lanzadado una excepcion Delete. " + e.toString());
			mensaje = new Mensaje(MensajeType.MSG_TYPE_DANGER);
			txt = "Ha habido problemas al borrar hay cartas usando ese color.";
		} 
		
		mensaje.setMsg(txt);
		redirectMap.addFlashAttribute("mensaje", mensaje);
		
		return mav;
		
	}
	
	@RequestMapping(value="/addColor", method = RequestMethod.GET)
	public ModelAndView addColor(Model model){
		model.addAttribute("color", new Color());
		mav= new ModelAndView("color");
		return mav;
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String saveColor(@Valid @RequestPart("fichero") MultipartFile file,
			@ModelAttribute("color")@Valid Color color,BindingResult bindingResult, ModelMap model,
			RedirectAttributes redirectMap) throws IOException {
		String destino = "";
		String txt="";
		Mensaje mensaje = null;
		if (bindingResult.hasErrors()) {
			LOGGER.info("Color tiene errores");
			destino = "color";
			txt = "Los datos de formulario contienen errores";
			mensaje = new Mensaje(MensajeType.MSG_TYPE_DANGER);
		}else{
			destino = "redirect:/colores";
			if(file.getOriginalFilename().isEmpty() == false){
				InputStream in = file.getInputStream();
				String root= File.separator +"resources" +File.separator + "images" +File.separator + "colors" +File.separator ;
				LOGGER.info("root: "+root);
				String ruta = servletContext.getRealPath(root);// si esta
																// desplegado en
																// wilfly
				// String ruta
				// ="C:\\Desarrollo\\svn\\bibliotecamtg\\src\\main\\webapp";
				File destination = new File(ruta + File.separator + file.getOriginalFilename());
				LOGGER.info("destination: "+destination);
				FileUtils.copyInputStreamToFile(in,  destination);
				LOGGER.info("ruta: "+ruta);
				color.setIcono(file.getOriginalFilename());
				LOGGER.info("filname: " + file.getOriginalFilename());
			}
			if(color.getCodigo() > Color.CODIGO_NULO){
				try {
					LOGGER.info(color.toString());
					coS.update(color);
					txt = "El color se ha actualizado correctamente.";
					mensaje = new Mensaje(MensajeType.MSG_TYPE_SUCCESS);
				} catch (Exception e) {
					LOGGER.info("Se ha lanzadado una excepcion update. " + e.toString());
					mensaje = new Mensaje(MensajeType.MSG_TYPE_DANGER);
					txt = "Ha habido problemas en la actualización.";
				}
			}else{
				try {
					LOGGER.info(color.toString());					
					coS.create(color);
					txt = "El color se ha creado correctamente.";
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
}
