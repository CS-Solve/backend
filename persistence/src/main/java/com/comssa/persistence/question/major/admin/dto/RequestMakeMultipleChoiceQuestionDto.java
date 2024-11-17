package com.comssa.persistence.question.major.admin.dto;

import com.comssa.persistence.question.common.domain.Question;
import com.comssa.persistence.question.common.domain.QuestionChoice;
import com.comssa.persistence.question.common.dto.request.RequestMakeQuestionDto;
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
public class RequestMakeMultipleChoiceQuestionDto extends RequestMakeQuestionDto {
	private List<RequestMakeQuestionChoiceDto> questionChoices;

	public static <T extends QuestionChoice> RequestMakeMultipleChoiceQuestionDto from(
		Question question,
		List<T> questionChoices) {
		return RequestMakeMultipleChoiceQuestionDto.builder()
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
