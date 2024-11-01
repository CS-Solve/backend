package com.server.computer_science.question.major_question.admin.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class RequestMakeMajorQuestionChoiceDto {
    private String text;
    private boolean answerStatus;
}
