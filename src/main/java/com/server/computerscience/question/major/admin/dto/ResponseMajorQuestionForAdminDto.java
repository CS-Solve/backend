package com.server.computerscience.question.major.admin.dto;

import java.util.List;

import com.server.computerscience.question.common.domain.QuestionCategory;
import com.server.computerscience.question.common.domain.QuestionLevel;
import com.server.computerscience.question.common.dto.response.ResponseQuestionChoiceDto;
import com.server.computerscience.question.common.dto.response.ResponseQuestionDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class ResponseMajorQuestionForAdminDto extends ResponseQuestionDto {
	private boolean ifApproved;
	private boolean canBeShortAnswered;

	public ResponseMajorQuestionForAdminDto(Long id, String question, QuestionCategory questionCategory,
		QuestionLevel questionLevel, List<ResponseQuestionChoiceDto> normalQuestionChoices, String description,
		boolean ifApproved, boolean canBeShortAnswered) {
		super(id, question, questionCategory, questionLevel, normalQuestionChoices, description);
		this.ifApproved = ifApproved;
		this.canBeShortAnswered = canBeShortAnswered;
	}

}