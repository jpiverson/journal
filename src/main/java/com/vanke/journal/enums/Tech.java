package com.vanke.journal.enums;

public enum Tech {

	OTHER("其他", 0), JAVA("JAVA", 1), PYTHON("PYTHON", 2), H5("H5", 3), ANDROID("ANDROID", 4), IOS("IOS", 5);

	private String name;
	private int index;

	private Tech(String name, int index) {
		this.name = name;
		this.index = index;
	}

	public static Tech getTech(int index) {
		if (index == 1) {
			return JAVA;
		} else if (index == 2) {
			return PYTHON;
		} else if (index == 3) {
			return H5;
		} else if (index == 4) {
			return ANDROID;
		} else if (index == 5) {
			return IOS;
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
