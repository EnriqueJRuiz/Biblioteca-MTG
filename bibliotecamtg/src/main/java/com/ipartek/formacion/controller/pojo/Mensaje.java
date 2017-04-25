package com.ipartek.formacion.controller.pojo;

import java.io.Serializable;

public class Mensaje implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String msg;
	MensajeType type;
	int code;
	
	public Mensaje(final String txt) {
		super();
		this.msg = txt;
		this.type = MensajeType.MSG_TYPE_SUCCESS;
	}

	public Mensaje(final MensajeType type) {
		super();
		this.msg = "";
		this.type = type;
	}

	public Mensaje(final String msg, final MensajeType type) {
		super();
		this.msg = msg;
		this.type = type;
	}
	
	
	
	@Override
	public String toString() {
		return "Mensaje [msg=" + msg + ", type=" + type + ", code=" + code + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + code;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mensaje other = (Mensaje) obj;
		if (code != other.code)
			return false;
		return true;
	}

	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public MensajeType getType() {
		return type;
	}
	public void setType(MensajeType type) {
		this.type = type;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	
	
	
}
