package com.comssa.persistence.question.dto.common.request;

import com.comssa.persistence.question.domain.common.QuestionCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestQuestionCommandDto {
    private String command;
    private boolean multipleChoice;
    private List<QuestionCategory> questionCategories;
}
