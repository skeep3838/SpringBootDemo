package com.nlb.nlbEnum;

public enum FieldingPosition {
	PICTHER("P", "投手"),
	CATCHER("C", "捕手"),
	FIRST_BASEMAN("1B", "一壘手"),
	SECOND_BASEMAN("2B", "二壘手"),
	THIRD_BASEMAN("3B", "三壘手"),
	SHORT_STOP("SS", "游擊手"),
	LEFT_FIELDER("LF", "左外野手"),
	CENTER_FIELDER("CF", "中外野手"),
	RIGHT_FIELDER("RF", "右外野手"),
	DESIGNATED_HITTER("DH", "指定打擊"),
	;

	private String value;

	private String name;

	FieldingPosition(String value, String name) {
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
