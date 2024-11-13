package com.server.computerscience.chatbot.domain;

public enum ChatRole {
	SYSTEM("system"), USER("user"), ASSISTANT("assistant");
	private final String lower;

	ChatRole(String lower) {
		this.lower = lower;
	}

	public String getLower() {
		return lower;
	}
}
