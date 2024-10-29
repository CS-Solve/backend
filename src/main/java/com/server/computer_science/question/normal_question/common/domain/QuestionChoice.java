package com.server.computer_science.question.normal_question.common.domain;


import com.server.computer_science.question.license_question.domain.LicenseNormalQuestion;
import com.server.computer_science.question.normal_question.admin.dto.RequestMakeNormalQuestionChoiceDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class QuestionChoice {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private int selectedCount;
    private boolean answerStatus;

    @ManyToOne
    @JoinColumn(name = "normal_question_id")
    private NormalQuestion normalQuestion;

    @ManyToOne
    @JoinColumn(name = "license_normal_question_id")
    private LicenseNormalQuestion licenseNormalQuestion;

    public static QuestionChoice fromNormalQuestion(RequestMakeNormalQuestionChoiceDto dto, NormalQuestion normalQuestion) {
        QuestionChoice questionChoice =  QuestionChoice.builder()
                .text(dto.getText())
                .selectedCount(0)
                .answerStatus(dto.isAnswerStatus())
                .normalQuestion(normalQuestion)
                .build();
        normalQuestion.getQuestionChoices().add(questionChoice);
        return questionChoice;
    }

    public static QuestionChoice fromLicenseNormalQuestion(
            RequestMakeNormalQuestionChoiceDto dto,
            LicenseNormalQuestion licenseNormalQuestion) {
        QuestionChoice questionChoice =  QuestionChoice.builder()
                .text(dto.getText())
                .selectedCount(0)
                .answerStatus(dto.isAnswerStatus())
                .licenseNormalQuestion(licenseNormalQuestion)
                .build();
        licenseNormalQuestion.getQuestionChoices().add(questionChoice);
        return questionChoice;
    }
    @Builder
    public QuestionChoice(String text, int selectedCount, boolean answerStatus, NormalQuestion normalQuestion,LicenseNormalQuestion licenseNormalQuestion ) {
        this.text = text;
        this.selectedCount = selectedCount;
        this.answerStatus = answerStatus;
        this.normalQuestion = normalQuestion;
        this.licenseNormalQuestion = licenseNormalQuestion;
    }

}
