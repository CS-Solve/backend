package com.comssa.persistence.question.common.dto.response;

import com.comssa.persistence.question.common.domain.Question;
import com.comssa.persistence.question.common.domain.QuestionCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@SuperBuilder
public class ResponseClassifiedQuestionDto {
	private QuestionCategory questionCategory;
	private List<ResponseQuestionDto> responseQuestionDtos;


	public static <T extends Question> ResponseClassifiedQuestionDto getQuestions(
		QuestionCategory questionCategory,
		List<T> question) {
		return ResponseClassifiedQuestionDto.builder()
			.questionCategory(questionCategory)
			.responseQuestionDtos(
				question.stream()
					.map(ResponseQuestionDto::from)
					.collect(Collectors.toList())
			)
			.build();
	}
}
