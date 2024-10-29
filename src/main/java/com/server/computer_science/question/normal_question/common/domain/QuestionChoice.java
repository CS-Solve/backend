package com.server.computer_science.question.normal_question.common.domain;


import com.server.computer_science.question.license_question.domain.LicenseNormalQuestion;
import com.server.computer_science.question.normal_question.user.dto.request.RequestMakeNormalQuestionChoiceDto;
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

    public static QuestionChoice MakeNormalQuestionWithDto(RequestMakeNormalQuestionChoiceDto dto, NormalQuestion normalQuestion) {
        QuestionChoice questionChoice =  QuestionChoice.builder()
                .text(dto.getText())
                .selectedCount(0)
                .answerStatus(dto.isAnswerStatus())
                .normalQuestion(normalQuestion)
                .build();
        normalQuestion.getQuestionChoices().add(questionChoice);
        return questionChoice;
    }
    @Builder
    public QuestionChoice(String text, int selectedCount, boolean answerStatus, NormalQuestion normalQuestion) {
        this.text = text;
        this.selectedCount = selectedCount;
        this.answerStatus = answerStatus;
        this.normalQuestion = normalQuestion;
    }
}
