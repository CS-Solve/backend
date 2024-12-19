package com.comssa.persistence.question.domain.license;

import com.comssa.persistence.question.domain.common.QuestionChoice;
import com.comssa.persistence.question.dto.common.request.RequestMakeQuestionChoiceDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@NoArgsConstructor
@SuperBuilder
@DiscriminatorValue("LC")
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
			.answerStatus(dto.getAnswerStatus())
			.question(licenseMultipleChoiceQuestion)
			.build();
		licenseMultipleChoiceQuestion.getQuestionChoices().add(questionChoice);
		return questionChoice;
	}
}
