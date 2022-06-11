package com.nlb.nlbEnum;

public enum PlayerStatus {

	//	FIRST_TEAM("1T", "一軍"),
	//	SECOND_TEAM("2T", "二軍"),
	ACTIVE("ACTIVE", "活動中"),
	INJURY("INJURY", "傷兵"),
	RELEASE("RELEASE", "釋出"),
	;

	private String value;

	private String name;

	PlayerStatus(String value, String name) {
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
