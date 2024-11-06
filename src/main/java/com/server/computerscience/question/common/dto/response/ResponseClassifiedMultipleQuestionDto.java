package com.server.computerscience.question.common.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import com.server.computerscience.question.common.domain.ChoiceProvider;
import com.server.computerscience.question.common.domain.Question;
import com.server.computerscience.question.common.domain.QuestionCategory;
import com.server.computerscience.question.major.common.domain.MajorMultipleChoiceQuestion;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@SuperBuilder
public class ResponseClassifiedMultipleQuestionDto {
	private QuestionCategory questionCategory;
	private List<ResponseQuestionDto> responseQuestionDtoList;

	public ResponseClassifiedMultipleQuestionDto(QuestionCategory questionCategory,
		List<ResponseQuestionDto> responseQuestionDtoList) {
		this.questionCategory = questionCategory;
		this.responseQuestionDtoList = responseQuestionDtoList;
	}

	/**
	 * 유저 객관식 문제 전용
	 */
	public static <T extends Question & ChoiceProvider> ResponseClassifiedMultipleQuestionDto forUser(
		QuestionCategory questionCategory, List<T> multipleChoiceQuestions) {
		return ResponseClassifiedMultipleQuestionDto.builder()
			.questionCategory(questionCategory)
			.responseQuestionDtoList(
				multipleChoiceQuestions.stream()
					.map(question -> ResponseQuestionDto.forUser(
						question, question.getQuestionChoices()
					))
					.collect(Collectors.toList())
			)
			.build();
	}

	/**
	 * 관리자 객관식
	 */
	public static ResponseClassifiedMultipleQuestionDto forAdmin(QuestionCategory questionCategory,
		List<MajorMultipleChoiceQuestion> majorMultipleChoiceQuestions) {
		return ResponseClassifiedMultipleQuestionDto.builder()
			.questionCategory(questionCategory)
			.responseQuestionDtoList(
				majorMultipleChoiceQuestions.stream()
					.map(ResponseQuestionDto::forAdmin)
					.collect(Collectors.toList())
			)
			.build();
	}
}
