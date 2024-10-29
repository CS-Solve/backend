package com.server.computer_science.question.normal_question.user.dto.request;


import com.server.computer_science.question.common.domain.QuestionCategory;
import com.server.computer_science.question.common.domain.QuestionLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class RequestMakeNormalQuestionDto {
    private String question;
    private QuestionCategory questionCategory;
    private QuestionLevel questionLevel;
    private List<RequestMakeNormalQuestionChoiceDto> normalQuestionChoices;
    private String description;
}
