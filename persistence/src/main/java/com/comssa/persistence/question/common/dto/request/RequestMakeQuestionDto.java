package com.comssa.persistence.question.common.dto.request;

import com.comssa.persistence.question.common.domain.QuestionCategory;
import com.comssa.persistence.question.common.domain.QuestionLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
public class RequestMakeQuestionDto {
	private String content;
	private QuestionCategory questionCategory;
	private QuestionLevel questionLevel;
	private String description;
}
