package com.server.computerscience.chatbot.domain;

import lombok.Getter;

@Getter
public enum ChatContentType {
	TEXT("text");
	private final String lower;

	ChatContentType(String lower) {
		this.lower = lower;
	}
}
