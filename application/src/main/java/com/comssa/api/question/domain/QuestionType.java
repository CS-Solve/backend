package com.comssa.api.question.domain;

import lombok.Getter;

@Getter
public enum QuestionType {
	MULTIPLE_CHOICE("multiple"),
	DESCRIPTIVE("descriptive");
	private final String name;

	QuestionType(String name) {
		this.name = name;
	}
}
