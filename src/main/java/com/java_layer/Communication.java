package com.java_layer;

import java.util.Map;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Communication {
	public static String getMessage(String messageName) {
		return messageData.get(messageName);
	}
	
	public static void showMessage(String messageName) {
	    String message = getMessage(messageName);
		JOptionPane.showMessageDialog(null, message);
	}

	public static void askQuestion(String questionName) {
		Question question = questionData.get(questionName);
		if (question.type.equals("boolean") || question.type.equals("choice")) {
			askBooleanQuestion(question);
		}
		else if (question.type.equals("multiple")) {
			askMultipleQuestion(question);
		}
	}
	
	static void askBooleanQuestion(Question question) {
		Object[] options = { "Tak", "Nie" };
		int data = JOptionPane.showOptionDialog(null, question.message, "Choose an answer", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
		boolean result = data == 0;//JOptionPane.YES_OPTION;
		if(result && question.type.equals("choice")) {
			KnowledgeSession.addFact(new Fact("chosen", true));
		}
		else {
			KnowledgeSession.addFact(new Fact(question.name, result));
		}
	}
	
	static void askMultipleQuestion(Question question) {
		ArrayList<Object> temp = new ArrayList<Object>();
		
		for (Choice choice : question.choices) {
			temp.add(choice.message);
		}
		Object[] options = temp.toArray(new Object[temp.size()]);
		int data = JOptionPane.showOptionDialog(null, question.message, "Choose an answer", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
		try {
			Choice result = question.choices.get(data);
			
			/*	Int or name	 */
			KnowledgeSession.addFact(new Fact(question.name, result.name));
		}
		catch (java.lang.ArrayIndexOutOfBoundsException t) {
			askMultipleQuestion(question);
		}
	}
	
	static Map<String, String> messageData = Parser.parseMessages("assets/messages.yml");
	static Map<String, Question> questionData = Parser.parseQuestions("assets/questions.yml");
}
