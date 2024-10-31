package com.server.computer_science.question.common.dto.response;


import com.server.computer_science.question.common.domain.QuestionChoice;
import com.server.computer_science.question.license_question.domain.LicenseNormalQuestionChoice;
import com.server.computer_science.question.normal_question.common.domain.NormalQuestionChoice;
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

    public static <T extends QuestionChoice> ResponseNormalQuestionChoiceDto of(T questionChoice) {
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
