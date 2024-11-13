package com.server.computerscience.chatbot.domain;

public enum ChatContentType {
	TEXT("text");
	private final String lower;

	ChatContentType(String lower) {
		this.lower = lower;
	}

	public String getLower() {
		return lower;
	}
}
