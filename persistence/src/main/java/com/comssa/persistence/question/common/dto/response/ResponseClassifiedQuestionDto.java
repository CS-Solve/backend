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
public class ResponseClassifiedQuestionDto {
	private QuestionCategory questionCategory;
	private List<ResponseQuestionDto> responseQuestionDtos;

	/**
	 * 유저 객관식 문제 전용
	 */
	public static <T extends Question & ChoiceProvider> ResponseClassifiedQuestionDto multipleQuestionForUser(
		QuestionCategory questionCategory, List<T> multipleChoiceQuestions) {
		return ResponseClassifiedQuestionDto.builder()
			.questionCategory(questionCategory)
			.responseQuestionDtos(
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
	public static ResponseClassifiedQuestionDto multipleQuestionForAdmin(
		QuestionCategory questionCategory,
		List<MajorMultipleChoiceQuestion> majorMultipleChoiceQuestions) {
		return ResponseClassifiedQuestionDto.builder()
			.questionCategory(questionCategory)
			.responseQuestionDtos(
				majorMultipleChoiceQuestions.stream()
					.map(ResponseMultipleChoiceQuestionDto::forMajor)
					.collect(Collectors.toList())
			)
			.build();
	}

//	/**
//	 * 유저 서술형
//	 */
//	public static ResponseClassifiedQuestionDto majorDescriptiveQuestionForUser(
//		QuestionCategory questionCategory,
//		List<MajorDescriptiveQuestion> majorDescriptiveQuestions
//	) {
//		return ResponseClassifiedQuestionDto.builder()
//			.questionCategory(questionCategory)
//			.responseQuestionDtos(
//				majorDescriptiveQuestions
//			)
//			.build();
//	}
}
