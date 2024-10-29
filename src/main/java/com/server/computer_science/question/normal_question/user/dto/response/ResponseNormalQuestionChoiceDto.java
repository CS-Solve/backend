package com.server.computer_science.question.normal_question.user.dto.response;


import com.server.computer_science.question.normal_question.common.domain.QuestionChoice;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Builder
@Getter
public class ResponseNormalQuestionChoiceDto {

    private String text;
    private int selectedCount;
    private boolean answerStatus;

    public static ResponseNormalQuestionChoiceDto of(QuestionChoice questionChoice) {
        return ResponseNormalQuestionChoiceDto.builder()
                .text(questionChoice.getText())
                .selectedCount(questionChoice.getSelectedCount())
                .answerStatus(questionChoice.isAnswerStatus())
                .build();
    }

    @Builder
    public ResponseNormalQuestionChoiceDto(String text, int selectedCount, boolean answerStatus) {
        this.text = text;
        this.selectedCount = selectedCount;
        this.answerStatus = answerStatus;
    }
}
