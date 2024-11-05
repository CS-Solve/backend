package com.server.computer_science.question.common.domain;

public enum QuestionLevel {

	LOW("하"), MEDIUM("중"), HIGH("상");
	private final String korean;

	QuestionLevel(String korean) {
		this.korean = korean;
	}

	public String getKorean() {
		return korean;
	}
}
