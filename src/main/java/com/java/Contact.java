package com.java;

import java.util.Map;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Contact {
	public static String getMessage(String messageName) {
		return messageData.get(messageName);
	}
	public static String path = "assets\\";
	
	public static void showMessage(String messageName) {
	    String message = getMessage(messageName);
	    System.out.println("MessageName = " + messageName);
	    final ImageIcon icon = new ImageIcon("files\\donkey.gif");
        JOptionPane.showMessageDialog(null, message, "", JOptionPane.INFORMATION_MESSAGE, icon);
		//JOptionPane.showMessageDialog(null, message);
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
		final Icon icon;
		Object[] options = { "Tak", "Nie" };
		/*if (question.type.equals("choice")){
			icon = new ImageIcon(path+"sheep.gif");//(path+ question.name +".gif");
		} else {
			icon = new ImageIcon(path+"smile.gif");
		}*/
		int data = JOptionPane.showOptionDialog(null, question.message, "", JOptionPane.DEFAULT_OPTION, /*icon*/ JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
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
		int data = JOptionPane.showOptionDialog(null, question.message, "", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		try {
			Choice result = question.choices.get(data);
			
			/*	Int or name	 */
			KnowledgeSession.addFact(new Fact(question.name, result.name));
		}
		catch (java.lang.ArrayIndexOutOfBoundsException t) {
			askMultipleQuestion(question);
		}
	}
	
	static Map<String, String> messageData = YamlParse.parseMessages("files/messages.yml");
	static Map<String, Question> questionData = YamlParse.parseQuestions("files/questions.yml");
}
