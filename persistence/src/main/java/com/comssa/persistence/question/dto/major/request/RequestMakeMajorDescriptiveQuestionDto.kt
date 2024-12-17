package com.comssa.persistence.question.dto.major.request;

import com.comssa.persistence.question.dto.common.request.RequestMakeQuestionDto;
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
