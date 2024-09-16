package com.server.computer_science.question.normal_question.dto.response;


import com.server.computer_science.question.common.ProblemCategory;
import com.server.computer_science.question.common.ProblemLevel;
import com.server.computer_science.question.normal_question.domain.NormalQuestion;
import com.server.computer_science.question.normal_question.dto.request.RequestNormalQuestionChoiceDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class ResponseNormalQuestionDto {
    private String question;
    private ProblemCategory problemCategory;
    private ProblemLevel problemLevel;
    private List<ResponseNormalQuestionChoiceDto> normalQuestionChoices;


    public static ResponseNormalQuestionDto of(NormalQuestion question){
        return ResponseNormalQuestionDto.builder()
                .question(question.getQuestion())
                .normalQuestionChoices(
                        question.getNormalQuestionChoices()
                                .stream()
                                .map(ResponseNormalQuestionChoiceDto::of)
                                .collect(Collectors.toList())
                )
                .problemCategory(question.getProblemCategory())
                .problemLevel(question.getProblemLevel())
                .build();
    }
    @Builder
    public ResponseNormalQuestionDto(String question, ProblemCategory problemCategory, ProblemLevel problemLevel, List<ResponseNormalQuestionChoiceDto> normalQuestionChoices) {
        this.question = question;
        this.problemCategory = problemCategory;
        this.problemLevel = problemLevel;
        this.normalQuestionChoices = normalQuestionChoices;
    }
}
