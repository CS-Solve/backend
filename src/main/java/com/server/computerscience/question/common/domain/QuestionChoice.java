package com.server.computerscience.question.common.domain;

import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@MappedSuperclass
@ToString(exclude = "question")
public abstract class QuestionChoice {
	private String content;
	private int selectedCount;
	private boolean answerStatus;

	public QuestionChoice(String content, int selectedCount, boolean answerStatus) {
		this.content = content;
		this.selectedCount = selectedCount;
		this.answerStatus = answerStatus;
	}

	public void changeContent(String content) {
		this.content = content;
	}
}
