package com.server.computer_science.question.major_question.admin.dto;


import com.server.computer_science.question.common.domain.QuestionChoice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class RequestMakeQuestionChoiceDto {
    private String text;
    private boolean answerStatus;
    public static RequestMakeQuestionChoiceDto from(QuestionChoice questionChoice){
        return RequestMakeQuestionChoiceDto.builder()
                .answerStatus(questionChoice.isAnswerStatus())
                .text(questionChoice.getText())
                .build();
    }
}
