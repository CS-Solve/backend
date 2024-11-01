package com.server.computer_science.question.major_question.common.domain;


import com.server.computer_science.question.common.domain.QuestionChoice;
import com.server.computer_science.question.major_question.admin.dto.RequestMakeMajorQuestionChoiceDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@SuperBuilder
public class MajorQuestionChoice extends QuestionChoice {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "major_multiple_choice_question_id")
    private MajorMultipleChoiceQuestion majorMultipleChoiceQuestion;

    public static MajorQuestionChoice fromNormalQuestion(RequestMakeMajorQuestionChoiceDto dto, MajorMultipleChoiceQuestion majorMultipleChoiceQuestion) {
        MajorQuestionChoice majorQuestionChoice =  MajorQuestionChoice.builder()
                .text(dto.getText())
                .selectedCount(0)
                .answerStatus(dto.isAnswerStatus())
                .majorMultipleChoiceQuestion(majorMultipleChoiceQuestion)
                .build();
        majorMultipleChoiceQuestion.getQuestionChoices().add(majorQuestionChoice);
        return majorQuestionChoice;
    }

    public MajorQuestionChoice(String text, int selectedCount, boolean answerStatus, MajorMultipleChoiceQuestion majorMultipleChoiceQuestion) {
        super(text, selectedCount, answerStatus);
        this.majorMultipleChoiceQuestion = majorMultipleChoiceQuestion;
    }

}
