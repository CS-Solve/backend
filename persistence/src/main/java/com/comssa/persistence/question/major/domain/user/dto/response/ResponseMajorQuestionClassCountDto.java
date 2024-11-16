package com.comssa.persistence.question.major.domain.user.dto.response;

import com.comssa.persistence.question.common.domain.QuestionCategory;
import com.comssa.persistence.question.common.domain.QuestionLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ResponseMajorQuestionClassCountDto {
	private QuestionCategory questionCategory;
	private QuestionLevel questionLevel;
	private int count;

	@Builder
	public ResponseMajorQuestionClassCountDto(QuestionCategory questionCategory, QuestionLevel questionLevel,
											  int count) {
		this.questionCategory = questionCategory;
		this.questionLevel = questionLevel;
		this.count = count;
	}

	public static ResponseMajorQuestionClassCountDto of(QuestionCategory questionCategory, QuestionLevel questionLevel,
														int count) {
		return ResponseMajorQuestionClassCountDto.builder()
			.questionLevel(questionLevel)
			.questionCategory(questionCategory)
			.count(count)
			.build();
	}
}
