package com.server.computerscience.chatbot.domain;

import lombok.Getter;

@Getter
public enum ChatGptContentType {
	TEXT("text");
	private final String lower;

	ChatGptContentType(String lower) {
		this.lower = lower;
	}
}
