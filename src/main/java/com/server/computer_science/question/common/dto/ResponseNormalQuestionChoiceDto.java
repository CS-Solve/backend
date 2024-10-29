package com.server.computer_science.question.common.dto;


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

    public static ResponseNormalQuestionChoiceDto of(NormalQuestionChoice normalQuestionChoice) {
        return ResponseNormalQuestionChoiceDto.builder()
                .text(normalQuestionChoice.getText())
                .selectedCount(normalQuestionChoice.getSelectedCount())
                .answerStatus(normalQuestionChoice.isAnswerStatus())
                .build();
    }
    public static ResponseNormalQuestionChoiceDto of(LicenseNormalQuestionChoice normalQuestionChoice) {
        return ResponseNormalQuestionChoiceDto.builder()
                .text(normalQuestionChoice.getText())
                .selectedCount(normalQuestionChoice.getSelectedCount())
                .answerStatus(normalQuestionChoice.isAnswerStatus())
                .build();
    }


    @Builder
    public ResponseNormalQuestionChoiceDto(String text, int selectedCount, boolean answerStatus) {
        this.text = text;
        this.selectedCount = selectedCount;
        this.answerStatus = answerStatus;
    }
}
