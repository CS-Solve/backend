package com.server.computer_science.question.normal_question.admin.dto;


import com.server.computer_science.question.common.domain.QuestionCategory;
import com.server.computer_science.question.common.domain.QuestionLevel;
import com.server.computer_science.question.common.dto.RequestMakeQuestionDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
public class RequestMakeNormalQuestionDto extends RequestMakeQuestionDto {
    private List<RequestMakeNormalQuestionChoiceDto> questionChoices;
}
