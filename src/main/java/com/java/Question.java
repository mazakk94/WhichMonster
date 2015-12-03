package com.java;

import java.util.ArrayList;

class Choice {
	String name;
	String message;
	
	public Choice(String name, String message) {
		this.name = name;
		this.message = message;
	}
}

public class Question {
	String type;
	String message;
	String name;
	ArrayList<Choice> choices;
	
	public Question(String name, String type, String message, ArrayList<Choice> choices) {
		this.name = name;
		this.type = type;
		this.message = message;
		this.choices = choices;
	}
}