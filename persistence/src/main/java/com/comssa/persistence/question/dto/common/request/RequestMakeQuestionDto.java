package com.comssa.persistence.question.dto.common.request;

import com.comssa.persistence.question.domain.common.QuestionCategory;
import com.comssa.persistence.question.domain.common.QuestionLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
public class RequestMakeQuestionDto {
    private String content;
    private QuestionCategory questionCategory;
    private QuestionLevel questionLevel;
    private String description;
}
