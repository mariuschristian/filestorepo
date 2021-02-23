package org.kastiks.interpretor;

public class Parameter {
	
	private String key;
	private String value;

	private Parameter() {};
	
	public Parameter(String key) {
		this.key = key;
	}
	
	public Parameter(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	public String getKey() {
		return this.key;
	}
	
	public String getValue() {
		return this.value;
	}
	
	public String toString() {
		String s = null;
		s = "[" + this.key + " | " + this.getValue() + "]";
		return s;
	}

}
