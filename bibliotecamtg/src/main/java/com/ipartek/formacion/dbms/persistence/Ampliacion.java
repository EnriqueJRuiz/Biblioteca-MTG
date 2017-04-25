package com.ipartek.formacion.dbms.persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public class Ampliacion implements Serializable, Comparable<Ampliacion> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int CODIGO_NULO = -1;
	
	protected int 	codigo;
	private String 	nombre;
	private String 	siglas;
	private String 	icono;
	private String 	imagen;
	private Date 	fLanzamiento;
	private boolean especial;
	private Map<Long,Carta> cartas;
	private Ampliacion principal;
	private boolean basica;


	public Ampliacion() {
		super();
		this.codigo 			= CODIGO_NULO;
		this.nombre 			= "";
		this.siglas				= "";
		this.icono				= "";
		this.imagen				= "";
		this.fLanzamiento 		= new Date();
		this.especial			= false;
		this.cartas				= null;
		this.basica				= false;
	}

	
	public int getCodigo() {
		return codigo;
	}


	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getSiglas() {
		return siglas;
	}


	public void setSiglas(String siglas) {
		this.siglas = siglas;
	}


	public String getIcono() {
		return icono;
	}


	public void setIcono(String icono) {
		this.icono = icono;
	}


	public String getImagen() {
		return imagen;
	}


	public void setImagen(String imagen) {
		this.imagen = imagen;
	}


	public Date getfLanzamiento() {
		return fLanzamiento;
	}


	public void setfLanzamiento(Date fLanzamiento) {
		this.fLanzamiento = fLanzamiento;
	}

	public boolean getEspecial() {
		return especial;
	}


	public void setEspecial(boolean especial) {
		this.especial = especial;
	}
	
	public Map<Long, Carta> getCartas() {
		return cartas;
	}


	public void setCartas(Map<Long, Carta> cartas) {
		this.cartas = cartas;
	}


	public Ampliacion getPrincipal() {
		return principal;
	}


	public void setPrincipal(Ampliacion ampliacion) {
		this.principal = ampliacion;
	}
	

	public boolean getBasica() {
		return basica;
	}


	public void setBasica(boolean basica) {
		this.basica = basica;
	}

	@Override
	public String toString() {
		return "Ampliacion [nombre=" + nombre + ", siglas=" + siglas + ", icono=" + icono + ", imagen=" + imagen
				+ ", fLanzamiento=" + fLanzamiento + ", especial=" + especial + ", cartas=" + cartas + ", principal="
				+ principal + ", basica=" + basica + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (codigo ^ (codigo >>> 32));
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		boolean iguales = false;
		if (obj instanceof Ampliacion) {
			Ampliacion ampliacion = (Ampliacion) obj;
			if (this.codigo == ampliacion.getCodigo()) {
				iguales = true;
			}
		}
		return iguales;
	}


	@Override
	public int compareTo(Ampliacion o) {
		return this.getNombre().compareToIgnoreCase(o.getNombre());
	}

}
