package com.comssa.persistence.question.major.domain.admin.dto;

import com.comssa.persistence.question.common.domain.QuestionCategory;
import com.comssa.persistence.question.common.domain.QuestionLevel;
import com.comssa.persistence.question.common.domain.dto.response.ResponseQuestionChoiceDto;
import com.comssa.persistence.question.common.domain.dto.response.ResponseQuestionDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class ResponseMajorQuestionForAdminDto extends ResponseQuestionDto {
	private boolean canBeShortAnswered;

	public ResponseMajorQuestionForAdminDto(Long id, String content, QuestionCategory questionCategory,
											QuestionLevel questionLevel, List<ResponseQuestionChoiceDto> normalQuestionChoices, String description,
											boolean ifApproved, boolean canBeShortAnswered, String imageUrl) {
		super(id, content, description, imageUrl, questionCategory, questionLevel, normalQuestionChoices, ifApproved);
		this.canBeShortAnswered = canBeShortAnswered;
	}
}
