package com.server.computer_science.question.normal_question.common.domain;


import com.server.computer_science.question.common.domain.QuestionChoice;
import com.server.computer_science.question.normal_question.admin.dto.RequestMakeNormalQuestionChoiceDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@SuperBuilder
public class NormalQuestionChoice extends QuestionChoice {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "normal_question_id")
    private NormalQuestion normalQuestion;

    public static NormalQuestionChoice fromNormalQuestion(RequestMakeNormalQuestionChoiceDto dto, NormalQuestion normalQuestion) {
        NormalQuestionChoice normalQuestionChoice =  NormalQuestionChoice.builder()
                .text(dto.getText())
                .selectedCount(0)
                .answerStatus(dto.isAnswerStatus())
                .normalQuestion(normalQuestion)
                .build();
        normalQuestion.getQuestionChoices().add(normalQuestionChoice);
        return normalQuestionChoice;
    }

    public NormalQuestionChoice(String text, int selectedCount, boolean answerStatus, NormalQuestion normalQuestion) {
        super(text, selectedCount, answerStatus);
        this.normalQuestion = normalQuestion;
    }

}
