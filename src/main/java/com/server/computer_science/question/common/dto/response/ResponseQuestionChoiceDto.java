package com.server.computer_science.question.common.dto.response;


import com.server.computer_science.question.common.domain.QuestionChoice;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Builder
@Getter
public class ResponseQuestionChoiceDto {

    private String text;
    private int selectedCount;
    private boolean answerStatus;

    public static <T extends QuestionChoice> ResponseQuestionChoiceDto of(T questionChoice) {
        return ResponseQuestionChoiceDto.builder()
                .text(questionChoice.getText())
                .selectedCount(questionChoice.getSelectedCount())
                .answerStatus(questionChoice.isAnswerStatus())
                .build();
    }

    @Builder
    public ResponseQuestionChoiceDto(String text, int selectedCount, boolean answerStatus) {
        this.text = text;
        this.selectedCount = selectedCount;
        this.answerStatus = answerStatus;
    }
}
