package com.amdocs.bellca.erdComparator.models;

public class ErdXmlModel {

	private String ID;
	private String lang;
	private String Value;
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public String getValue() {
		return Value;
	}
	public void setValue(String value) {
		Value = value;
	}
	@Override
	public String toString() {
		return "ErdXmlModel [ID=" + ID + ", lang=" + lang + ", Value=" + Value + "]";
	}
	
}
