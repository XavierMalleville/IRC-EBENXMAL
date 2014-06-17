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
		return "<img width=\"20\" height=\"20\" src=\"" + Emoticon.class.getResource("emoticon/" + getCode().replace(":", "") + ".png").toString()+"\"/>";
	}

	public Emoticon(String code, String resource) {
		super();
		this.code = code;
		this.resource = resource;
	}
	
	
}
