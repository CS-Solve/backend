package com.server.computerscience.question.license.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.server.computerscience.question.common.domain.QuestionChoice;
import com.server.computerscience.question.major.admin.dto.RequestMakeQuestionChoiceDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@NoArgsConstructor
@SuperBuilder
public class LicenseQuestionChoice extends QuestionChoice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "license_multiple_choice_question_id")
	private LicenseMultipleChoiceQuestion licenseMultipleChoiceQuestion;

	public LicenseQuestionChoice(String text, int selectedCount, boolean answerStatus,
		LicenseMultipleChoiceQuestion licenseMultipleChoiceQuestion) {
		super(text, selectedCount, answerStatus);
		this.licenseMultipleChoiceQuestion = licenseMultipleChoiceQuestion;
	}

	public static LicenseQuestionChoice from(
		RequestMakeQuestionChoiceDto dto,
		LicenseMultipleChoiceQuestion licenseMultipleChoiceQuestion) {
		LicenseQuestionChoice questionChoice = LicenseQuestionChoice.builder()
			.text(dto.getText())
			.selectedCount(0)
			.answerStatus(dto.isAnswerStatus())
			.licenseMultipleChoiceQuestion(licenseMultipleChoiceQuestion)
			.build();
		licenseMultipleChoiceQuestion.getQuestionChoices().add(questionChoice);
		return questionChoice;
	}
}
