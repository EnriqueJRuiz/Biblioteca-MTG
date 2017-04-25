package com.ipartek.formacion.dbms.persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Carta implements Serializable, Comparable<Carta> {

	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final long CODIGO_NULO = -1;
	
	protected long 	codigo;
	private String 	nombre;
	private String 	texto;
	private String 	rareza ;
	private String 	costeDeMana;
	private String 	supertipo;
	private String 	tipo;
	private String 	subtipo;
	private int 	numero;
	private String imagen;
	private Ampliacion ampliacion;
	//private String ampliacion_nombre;
	private List<Color> colores;
	
	public Carta() {
		super();
		this.codigo 			= CODIGO_NULO;
		this.nombre 			= "";
		this.texto 				= "";
		this.rareza 			= "";
		this.costeDeMana 		= "";
		this.supertipo 			= "";
		this.tipo 				= "";
		this.subtipo 			= "";
		this.numero 			= 0;
		this.imagen 			= "";
		this.ampliacion	= new Ampliacion();
		colores = new ArrayList<Color>();
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getRareza() {
		return rareza;
	}

	public void setRareza(String rareza) {
		this.rareza = rareza;
	}

	public String getCosteDeMana() {
		return costeDeMana;
	}

	public void setCosteDeMana(String costeDeMana) {
		this.costeDeMana = costeDeMana;
	}

	public String getSupertipo() {
		return supertipo;
	}

	public void setSupertipo(String supertipo) {
		this.supertipo = supertipo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getSubtipo() {
		return subtipo;
	}

	public void setSubtipo(String subtipo) {
		this.subtipo = subtipo;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}


	public Ampliacion getAmpliacion() {
		return ampliacion;
	}

	public void setAmpliacion(Ampliacion ampliacion) {
		this.ampliacion = ampliacion;
	}

	public List<Color> getColores() {
		return colores;
	}

	public void setColores(List<Color> colores) {
		this.colores = colores;
	}

	

	
	@Override
	public String toString() {
		return "Carta [codigo=" + codigo + ", nombre=" + nombre 
				+ ", texto=" + texto + ", rareza=" + rareza
				+ ", costeDeMana=" + costeDeMana + ", supertipo=" 
				+ supertipo + ", tipo=" + tipo + ", subtipo="
				+ subtipo + ", numero=" + numero + ", imagen=" + imagen 
				+ ", ampliacion=" + ampliacion.codigo + ", colores="
				+ colores + "]";
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
		if (obj instanceof Carta) {
			Carta carta = (Carta) obj;
			if (this.codigo == carta.getCodigo()) {
				iguales = true;
			}
		}
		return iguales;
	}

	@Override
	public int compareTo(Carta o) {
		return this.getNombre().compareToIgnoreCase(o.getNombre());
	}
	
	
}
