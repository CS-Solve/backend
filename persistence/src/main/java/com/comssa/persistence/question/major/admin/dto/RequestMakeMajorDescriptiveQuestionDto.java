package com.comssa.persistence.question.major.admin.dto;

import com.comssa.persistence.question.common.dto.request.RequestMakeQuestionDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
public class RequestMakeMajorDescriptiveQuestionDto extends RequestMakeQuestionDto {
	private String gradeStandard;
}
