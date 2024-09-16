package com.server.computer_science.question.normal_question.dto.request;


import com.server.computer_science.question.common.ProblemCategory;
import com.server.computer_science.question.common.ProblemLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class RequestNormalQuestionDto {
    private String question;
    private ProblemCategory problemCategory;
    private ProblemLevel problemLevel;
    private List<RequestNormalQuestionChoiceDto> normalQuestionChoices;
}
