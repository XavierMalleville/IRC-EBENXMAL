package com.cfranc.irc.ui;

public class Emoticon {
	private String code;
	private String resource;
	
	public String getCode() {
		return code;
	}

	public String getResource() {
		return resource;
	}

	public String getHtml() {
		return "<img src=\"" + resource + "\">";
	}

	public Emoticon(String code, String resource) {
		super();
		this.code = code;
		this.resource = resource;
	}
	
	
}
