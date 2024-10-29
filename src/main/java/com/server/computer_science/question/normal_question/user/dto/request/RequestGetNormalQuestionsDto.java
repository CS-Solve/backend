package com.server.computer_science.question.normal_question.user.dto.request;


import com.server.computer_science.question.common.domain.QuestionCategory;
import com.server.computer_science.question.common.domain.QuestionLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
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
        List<QuestionCategory> categories = mapOrGetAllEnumValues(questionCategories, QuestionCategory.class,QuestionCategory::getKorean);
        List<QuestionLevel> levels = mapOrGetAllEnumValues(questionLevels, QuestionLevel.class,QuestionLevel::getKorean);
        // DTO 빌드
        return RequestGetNormalQuestionsDto.builder()
                .questionCategories(categories)
                .questionLevels(levels)
                .build();
    }
    private static <T extends Enum<T>> List<T> mapOrGetAllEnumValues(List<String> values, Class<T> enumClass, Function<T, String> getKorean) {
        if (values == null || values.isEmpty()) {
            return Arrays.asList(enumClass.getEnumConstants());
        }
        return values.stream()
                .map(value -> Arrays.stream(enumClass.getEnumConstants())
                        .filter(e -> getKorean.apply(e).equals(value))
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("Invalid " + enumClass.getSimpleName() + ": " + value)))
                .collect(Collectors.toList());
    }
}
