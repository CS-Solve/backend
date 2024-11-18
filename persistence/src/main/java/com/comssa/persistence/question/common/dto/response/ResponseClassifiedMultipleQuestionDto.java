package com.comssa.persistence.question.common.dto.response;

import com.comssa.persistence.question.common.domain.ChoiceProvider;
import com.comssa.persistence.question.common.domain.Question;
import com.comssa.persistence.question.common.domain.QuestionCategory;
import com.comssa.persistence.question.major.domain.common.MajorMultipleChoiceQuestion;
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
	private List<ResponseMultipleChoiceQuestionDto> responseMultipleChoiceQuestionDtoList;

	public ResponseClassifiedMultipleQuestionDto(
		QuestionCategory questionCategory,
		List<ResponseMultipleChoiceQuestionDto> responseMultipleChoiceQuestionDtoList) {
		this.questionCategory = questionCategory;
		this.responseMultipleChoiceQuestionDtoList = responseMultipleChoiceQuestionDtoList;
	}

	/**
	 * 유저 객관식 문제 전용
	 */
	public static <T extends Question & ChoiceProvider> ResponseClassifiedMultipleQuestionDto forUser(
		QuestionCategory questionCategory, List<T> multipleChoiceQuestions) {
		return ResponseClassifiedMultipleQuestionDto.builder()
			.questionCategory(questionCategory)
			.responseMultipleChoiceQuestionDtoList(
				multipleChoiceQuestions.stream()
					.map(question -> ResponseMultipleChoiceQuestionDto.forUser(
						question, question.getQuestionChoices()
					))
					.collect(Collectors.toList())
			)
			.build();
	}

	/**
	 * 관리자 객관식
	 */
	public static ResponseClassifiedMultipleQuestionDto forAdmin(
		QuestionCategory questionCategory,
		List<MajorMultipleChoiceQuestion> majorMultipleChoiceQuestions) {
		return ResponseClassifiedMultipleQuestionDto.builder()
			.questionCategory(questionCategory)
			.responseMultipleChoiceQuestionDtoList(
				majorMultipleChoiceQuestions.stream()
					.map(ResponseMultipleChoiceQuestionDto::forMajor)
					.collect(Collectors.toList())
			)
			.build();
	}
}
