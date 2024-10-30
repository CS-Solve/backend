package com.server.computer_science.question.license_question.domain;

import com.server.computer_science.question.common.domain.QuestionChoice;
import com.server.computer_science.question.normal_question.admin.dto.RequestMakeNormalQuestionChoiceDto;
import com.server.computer_science.question.normal_question.common.domain.NormalQuestionChoice;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@SuperBuilder
public class LicenseNormalQuestionChoice extends QuestionChoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "license_normal_question_id")
    private LicenseNormalQuestion licenseNormalQuestion;

    public static LicenseNormalQuestionChoice fromLicenseNormalQuestion(
            RequestMakeNormalQuestionChoiceDto dto,
            LicenseNormalQuestion licenseNormalQuestion) {
        LicenseNormalQuestionChoice questionChoice =  LicenseNormalQuestionChoice.builder()
                .text(dto.getText())
                .selectedCount(0)
                .answerStatus(dto.isAnswerStatus())
                .licenseNormalQuestion(licenseNormalQuestion)
                .build();
        licenseNormalQuestion.getNormalQuestionChoices().add(questionChoice);
        return questionChoice;
    }

    public LicenseNormalQuestionChoice(String text, int selectedCount, boolean answerStatus, LicenseNormalQuestion licenseNormalQuestion) {
        super(text, selectedCount, answerStatus);
        this.licenseNormalQuestion = licenseNormalQuestion;
    }
}
