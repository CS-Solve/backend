package com.comssa.persistence.question.major.admin.dto;

import com.comssa.persistence.question.common.dto.response.ResponseMultipleChoiceQuestionDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class ResponseMajorMultipleChoiceQuestionForAdminDto extends ResponseMultipleChoiceQuestionDto {
	private boolean canBeShortAnswered;
}
