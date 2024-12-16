package com.comssa.api.question.domain;

import lombok.Getter;

@Getter
public enum QuestionType {
	MULTIPLE_CHOICE("multiple"),
	DESCRIPTIVE("descriptive");
	private String name;

	QuestionType(String name) {
		this.name = name;
	}
}
