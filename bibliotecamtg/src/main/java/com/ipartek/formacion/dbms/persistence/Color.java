package com.ipartek.formacion.dbms.persistence;

import java.io.Serializable;









public class Color implements Serializable, Comparable<Color> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int CODIGO_NULO = -1;
	
	protected int 	codigo;
	private String 	nombre;

	private String  icono;
	
	public Color() {
		super();
		this.codigo 			= CODIGO_NULO;
		this.nombre 			= "";
		this.icono				= "";
	}

	public Integer getCodigo() {
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

	@Override
	public String toString() {
		return "Color [" + codigo + "]";
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
		if (obj instanceof Color) {
			Color color = (Color) obj;
			if (this.codigo == color.getCodigo()) {
				iguales = true;
			}
		}
		return iguales;
	}

	@Override
	public int compareTo(Color o) {
		return this.getNombre().compareToIgnoreCase(o.getNombre());
	}

	public String getIcono() {
		return icono;
	}

	public void setIcono(String icono) {
		this.icono = icono;
	}

	
}
