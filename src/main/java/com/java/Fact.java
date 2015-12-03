package com.java;

public class Fact {
	private String name;
	private boolean booleanValue;
	private String stringValue;

	Fact(String name) {
		this.name = name;
	}

	Fact(String name, boolean booleanValue) {
		this(name);
		this.booleanValue = booleanValue;
	}

	Fact(String name, String stringValue) {
		this(name);
		this.stringValue = stringValue;
	}

	public boolean name(String value) {
		return name.equals(value);
	}

	public boolean value(boolean value) {
		return booleanValue == value;
	}

	public boolean value(String value) {
		return value.equals(stringValue);
	}
}
