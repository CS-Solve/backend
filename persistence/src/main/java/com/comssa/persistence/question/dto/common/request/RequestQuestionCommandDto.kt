package com.comssa.persistence.question.dto.common.request

import com.comssa.persistence.question.domain.common.QuestionCategory
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Getter
import lombok.NoArgsConstructor

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
class RequestQuestionCommandDto(
	val command: String,
	val multipleChoice: Boolean,
	val questionCategories: List<QuestionCategory>,
)
