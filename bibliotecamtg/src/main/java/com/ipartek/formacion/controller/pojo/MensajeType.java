package com.ipartek.formacion.controller.pojo;

public enum MensajeType {
	MSG_TYPE_SUCCESS("alert alert-success alert-dismissable"), 
	MSG_TYPE_INFO("alert alert-info alert-dismissable"), 
	MSG_TYPE_WARNING("alert alert-warning alert-dismissable"), 
	MSG_TYPE_DANGER("alert alert-danger alert-dismissable");
	
	private String styles;

	private MensajeType(String s) {
		this.styles = s;
	}


	public String getStyles() {
		return styles;
	}


	public void setStyles(String styles) {
		this.styles = styles;
	}

	
}
