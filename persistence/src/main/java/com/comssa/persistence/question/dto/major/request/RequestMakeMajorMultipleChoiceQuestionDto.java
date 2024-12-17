package com.comssa.persistence.question.dto.major.request;

import com.comssa.persistence.question.domain.common.Question;
import com.comssa.persistence.question.domain.common.QuestionChoice;
import com.comssa.persistence.question.dto.common.request.RequestMakeQuestionDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
public class RequestMakeMajorMultipleChoiceQuestionDto extends RequestMakeQuestionDto {
	private List<RequestMakeQuestionChoiceDto> questionChoices;

	public static <T extends QuestionChoice> RequestMakeMajorMultipleChoiceQuestionDto from(
		Question question,
		List<T> questionChoices) {
		return RequestMakeMajorMultipleChoiceQuestionDto.builder()
			.content(question.getContent())
			.description(question.getDescription())
			.questionCategory(question.getQuestionCategory())
			.questionLevel(question.getQuestionLevel())
			.questionChoices(questionChoices
				.stream()
				.map(RequestMakeQuestionChoiceDto::from)
				.collect(Collectors.toList()))
			.build();
	}
}
