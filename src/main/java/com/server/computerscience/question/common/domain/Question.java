package com.server.computerscience.question.common.domain;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@MappedSuperclass
@SuperBuilder
@NoArgsConstructor
public abstract class Question {
	protected String content;
	@Enumerated(value = EnumType.STRING)
	protected QuestionCategory questionCategory;
	@Enumerated(value = EnumType.STRING)
	protected QuestionLevel questionLevel;
	protected String description;
	protected String imageUrl;

	public Question(String content, QuestionCategory questionCategory, QuestionLevel questionLevel, String description,
		String imageUrl) {
		this.content = content;
		this.questionCategory = questionCategory;
		this.questionLevel = questionLevel;
		this.description = description;
		this.imageUrl = imageUrl;
	}

	public abstract Long getId();

	public void changeDescription(String description) {
		this.description = description;
	}

	public void changeContent(String content) {
		this.content = content;
	}

	public void updateImage(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public abstract void initDefaults();
}
