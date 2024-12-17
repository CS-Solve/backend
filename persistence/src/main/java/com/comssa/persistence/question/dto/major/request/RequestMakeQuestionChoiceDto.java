package com.comssa.persistence.question.dto.major.request;

import com.comssa.persistence.question.domain.common.QuestionChoice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class RequestMakeQuestionChoiceDto {
	private String text;
	private boolean answerStatus;

	public static RequestMakeQuestionChoiceDto from(QuestionChoice questionChoice) {
		return RequestMakeQuestionChoiceDto.builder()
			.answerStatus(questionChoice.isAnswerStatus())
			.text(questionChoice.getContent())
			.build();
	}
}
