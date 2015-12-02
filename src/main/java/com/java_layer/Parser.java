package com.java_layer;

import java.util.ArrayList;
import java.util.HashMap;

import org.yaml.snakeyaml.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

public class Parser {
	
	public static Map<String, Question> parseQuestions(String filename) {
		Yaml yaml = new Yaml();
    	InputStream input;
		Map<String, Question> result = new HashMap<String, Question>();
		Map<Object, Object> data;
		try {
			input = new FileInputStream(new File(filename));
			data = (Map<Object, Object>) yaml.load(input);
			for (Map.Entry<Object, Object> entry : data.entrySet()) {
	        	Map<String, Object> questionData = (Map<String, Object>) entry.getValue();
	        	String key = (String)entry.getKey();
	        	ArrayList<Choice> choices = new ArrayList<Choice>();
	        	String type = (String)questionData.get("type");
	        	if (type.equals("multiple")) {
	        		for (Map<Object, Object> choiceData : (ArrayList<Map<Object, Object>>)questionData.get("options")) {
	        			choices.add(new Choice((String)choiceData.get("key"), (String)choiceData.get("label")));
	        		}
	        	}
	            result.put(key, new Question(key, type, (String)questionData.get("message"), choices));
	        }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public static Map<String, String> parseMessages(String filename) {
		Yaml yaml = new Yaml();
    	InputStream input;
    	Map<String, String> data = new HashMap<String, String>();
		try {
			input = new FileInputStream(new File(filename));
	        data = (Map<String, String>) yaml.load(input);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        return data;
	}
}
