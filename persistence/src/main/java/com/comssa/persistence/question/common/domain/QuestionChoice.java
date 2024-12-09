package com.comssa.persistence.question.common.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Getter
@SuperBuilder
@NoArgsConstructor
@MappedSuperclass
@ToString(exclude = "question") //재귀로 StackOverFlow 발생
public abstract class QuestionChoice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
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

	public void toggleAnswerStatus() {
		this.answerStatus = !this.answerStatus;
	}

	public void select() {
		this.selectedCount++;
	}
}
