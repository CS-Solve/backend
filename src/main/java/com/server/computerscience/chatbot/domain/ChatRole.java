package com.server.computerscience.chatbot.domain;

import lombok.Getter;

@Getter
public enum ChatRole {
	SYSTEM("system"), USER("user"), ASSISTANT("assistant");
	private final String lower;

	ChatRole(String lower) {
		this.lower = lower;
	}
}
