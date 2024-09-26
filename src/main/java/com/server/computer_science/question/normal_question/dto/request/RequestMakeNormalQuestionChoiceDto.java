package com.server.computer_science.question.normal_question.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class RequestMakeNormalQuestionChoiceDto {
    private String text;
    private boolean answerStatus;
}