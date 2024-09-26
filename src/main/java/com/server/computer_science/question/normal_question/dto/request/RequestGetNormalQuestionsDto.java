package com.server.computer_science.question.normal_question.dto.request;


import com.server.computer_science.question.common.QuestionCategory;
import com.server.computer_science.question.common.QuestionLevel;
import com.server.computer_science.question.normal_question.dto.response.ResponseNormalQuestionDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class RequestGetNormalQuestionsDto {
    private List<QuestionCategory> questionCategories;
    private List<QuestionLevel> questionLevels;

    public static RequestGetNormalQuestionsDto of(List<QuestionCategory> questionCategories, List<QuestionLevel> questionLevels) {
        return RequestGetNormalQuestionsDto.builder()
                .questionCategories(questionCategories)
                .questionLevels(questionLevels)
                .build();
    }
}
