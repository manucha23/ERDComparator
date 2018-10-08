package com.amdocs.bellca.erdComparator.models;

public class ErdStandardModel {
	
	@Override
	public String toString() {
		return Id + "," + EnValue + "," + FrValue + ","	+ IsPresent + "\n";
	}
	private String Id;
	private String EnValue;
	private String FrValue;
	private boolean IsPresent;
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getEnValue() {
		return EnValue;
	}
	public void setEnValue(String enValue) {
		EnValue = enValue;
	}
	public String getFrValue() {
		return FrValue;
	}
	public void setFrValue(String frValue) {
		FrValue = frValue;
	}
	public boolean isIsPresent() {
		return IsPresent;
	}
	public void setIsPresent(boolean isPresent) {
		IsPresent = isPresent;
	}
}
