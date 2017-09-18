package com.vanke.journal.enums;

public enum Type {

	OTHER("其他", 0), RELEASE("发布版本", 1), PA("生产事故", 2);

	private String name;
	private int index;

	private Type(String name, int index) {
		this.name = name;
		this.index = index;
	}

	public static Type getType(int index) {
		if (index == 1) {
			return RELEASE;
		} else if (index == 2) {
			return PA;
		} else {
			return OTHER;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
