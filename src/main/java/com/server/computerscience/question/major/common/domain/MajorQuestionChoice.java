package com.server.computerscience.question.major.common.domain;

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
public class MajorQuestionChoice extends QuestionChoice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "major_multiple_choice_question_id")
	private MajorMultipleChoiceQuestion question;

	public MajorQuestionChoice(String text, int selectedCount, boolean answerStatus,
		MajorMultipleChoiceQuestion question) {
		super(text, selectedCount, answerStatus);
		this.question = question;
	}

	public static MajorQuestionChoice fromMajorQuestion(RequestMakeQuestionChoiceDto dto,
		MajorMultipleChoiceQuestion majorMultipleChoiceQuestion) {
		MajorQuestionChoice majorQuestionChoice = MajorQuestionChoice.builder()
			.text(dto.getText())
			.selectedCount(0)
			.answerStatus(dto.isAnswerStatus())
			.question(majorMultipleChoiceQuestion)
			.build();
		majorMultipleChoiceQuestion.getQuestionChoices().add(majorQuestionChoice);
		return majorQuestionChoice;
	}
}
