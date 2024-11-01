package com.server.computer_science.question.major_question.admin.dto;


import com.server.computer_science.question.common.dto.request.RequestMakeQuestionDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
public class RequestMakeMajorMultipleChoiceQuestionDto extends RequestMakeQuestionDto {
    private List<RequestMakeMajorQuestionChoiceDto> questionChoices;
}
