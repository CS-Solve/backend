package com.comssa.api.question.domain;

public enum QuestionType {
	MULTIPLE_CHOICE("multiple"),
	DESCRIPTIVE("descriptive");
	private final String urlPath;

	public String getUrlPath() {
		return urlPath;
	}

	QuestionType(String urlPath) {
		this.urlPath = urlPath;
	}
}
