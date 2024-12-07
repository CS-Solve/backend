package com.comssa.persistence.question.license.domain;

import com.comssa.persistence.question.common.domain.QuestionChoice;
import com.comssa.persistence.question.major.admin.dto.RequestMakeQuestionChoiceDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@NoArgsConstructor
@SuperBuilder
public class LicenseQuestionChoice extends QuestionChoice {
	@ManyToOne
	@JoinColumn(name = "license_multiple_choice_question_id")
	private LicenseMultipleChoiceQuestion question;

	public LicenseQuestionChoice(
		String text, int selectedCount,
		boolean answerStatus,
		LicenseMultipleChoiceQuestion question) {
		super(text, selectedCount, answerStatus);
		this.question = question;
	}

	public static LicenseQuestionChoice from(
		RequestMakeQuestionChoiceDto dto,
		LicenseMultipleChoiceQuestion licenseMultipleChoiceQuestion) {
		LicenseQuestionChoice questionChoice = LicenseQuestionChoice.builder()
			.content(dto.getText())
			.selectedCount(0)
			.answerStatus(dto.isAnswerStatus())
			.question(licenseMultipleChoiceQuestion)
			.build();
		licenseMultipleChoiceQuestion.getQuestionChoices().add(questionChoice);
		return questionChoice;
	}
}
