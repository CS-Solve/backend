package com.server.computer_science.question.normal_question.domain;


import com.server.computer_science.question.normal_question.dto.request.RequestMakeNormalQuestionChoiceDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class NormalQuestionChoice {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private int selectedCount;
    private boolean answerStatus;

    @ManyToOne
    @JoinColumn(name = "normal_question_id")
    private NormalQuestion normalQuestion;

    public static NormalQuestionChoice makeWithDto(RequestMakeNormalQuestionChoiceDto dto, NormalQuestion normalQuestion) {
        NormalQuestionChoice normalQuestionChoice=  NormalQuestionChoice.builder()
                .text(dto.getText())
                .selectedCount(0)
                .answerStatus(dto.isAnswerStatus())
                .normalQuestion(normalQuestion)
                .build();
        normalQuestion.getNormalQuestionChoices().add(normalQuestionChoice);
        return normalQuestionChoice;
    }
    @Builder
    public NormalQuestionChoice(String text, int selectedCount, boolean answerStatus, NormalQuestion normalQuestion) {
        this.text = text;
        this.selectedCount = selectedCount;
        this.answerStatus = answerStatus;
        this.normalQuestion = normalQuestion;
    }
}
