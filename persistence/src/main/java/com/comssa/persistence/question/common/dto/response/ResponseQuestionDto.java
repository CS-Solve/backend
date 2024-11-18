package com.comssa.persistence.question.common.dto.response;

import com.comssa.persistence.question.common.domain.Question;
import com.comssa.persistence.question.common.domain.QuestionCategory;
import com.comssa.persistence.question.common.domain.QuestionLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@SuperBuilder
public class ResponseQuestionDto {
	private Long id;
	private String content;
	private String description;
	private String imageUrl;
	private QuestionCategory questionCategory;
	private QuestionLevel questionLevel;
	private boolean ifApproved;

	public static <T extends Question> ResponseMultipleChoiceQuestionDto.ResponseMultipleChoiceQuestionDtoBuilder<?, ?> basic(
		T question) {
		return ResponseMultipleChoiceQuestionDto.builder()
			.id(question.getId())
			.content(question.getContent())
			.questionCategory(question.getQuestionCategory())
			.questionLevel(question.getQuestionLevel())
			.description(question.getDescription())
			.imageUrl(question.getImageUrl());
	}

	/**
	 * 유저와 관리자에 따라 다른 정적팩토리 메소드를 사용한다
	 */
	public static <T extends Question> ResponseMultipleChoiceQuestionDto forUser(
		T question) {
		return basic(question)
			.build();
	}
}
