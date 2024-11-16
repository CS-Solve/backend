package com.persistence.computerscience.question.common.domain.dto.response;

import com.persistence.computerscience.question.common.domain.ChoiceProvider;
import com.persistence.computerscience.question.common.domain.Question;
import com.persistence.computerscience.question.common.domain.QuestionCategory;
import com.persistence.computerscience.question.major.domain.common.MajorMultipleChoiceQuestion;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.stream.Collectors;

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
