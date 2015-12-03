package com.java;

import org.drools.runtime.StatefulKnowledgeSession;

public class KnowledgeSession {
	public static void setSession(StatefulKnowledgeSession ksession) {
		session = ksession;
	}
	
	public static void addFact(Fact fact) {
		session.insert(fact);
	}
	
	static StatefulKnowledgeSession session;
}
