package com.server.computerscience.chatbot.domain;

import lombok.Getter;

@Getter
public enum ChatGptRole {
	SYSTEM("system"), USER("user");
	private String lower;

	ChatGptRole(String lower) {
		this.lower = lower;
	}
}
