package com.nlb.entity;

public enum Teams {

	LOS_ANGELES_ANGELS("Los Angeles Angels", "洛杉磯天使"),
	;

	private String value;

	private String name;

	Teams(String value, String name) {
		this.value = value;
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
