package com.server.computer_science.question.normal_question.dto.request;


import com.server.computer_science.question.common.QuestionCategory;
import com.server.computer_science.question.common.QuestionLevel;
import com.server.computer_science.question.normal_question.dto.response.ResponseNormalQuestionDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    public static RequestGetNormalQuestionsDto fromString(List<String> questionCategories, List<String> questionLevels) {
        List<QuestionCategory> categories = null;
        List<QuestionLevel> levels = null;

        // questionCategories가 null이 아니면 String을 QuestionCategory로 매핑
        if (questionCategories != null) {
            categories = questionCategories.stream()
                    .map(category -> Arrays.stream(QuestionCategory.values())
                            .filter(q -> q.getKorean().equals(category)) // korean 필드 비교
                            .findFirst()
                            .orElseThrow(() -> new IllegalArgumentException("Invalid QuestionCategory: " + category)))
                    .collect(Collectors.toList());
        }

        // questionLevels가 null이 아니면 String을 QuestionLevel로 매핑
        if (questionLevels != null) {
            levels = questionLevels.stream()
                    .map(level -> Arrays.stream(QuestionLevel.values())
                            .filter(l -> l.getKorean().equals(level)) // korean 필드 비교
                            .findFirst()
                            .orElseThrow(() -> new IllegalArgumentException("Invalid QuestionLevel: " + level)))
                    .collect(Collectors.toList());
        }
        // DTO 빌드
        return RequestGetNormalQuestionsDto.builder()
                .questionCategories(categories)
                .questionLevels(levels)
                .build();
    }
}
