package com.comssa.persistence.question.domain.major;

import com.comssa.persistence.question.domain.common.QuestionChoice;
import com.comssa.persistence.question.dto.major.request.RequestMakeQuestionChoiceDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@NoArgsConstructor
@SuperBuilder
@DiscriminatorValue("MC")
public class MajorQuestionChoice extends QuestionChoice {

    @ManyToOne
    @JoinColumn(name = "major_multiple_choice_question_id")
    private MajorMultipleChoiceQuestion question;

    public MajorQuestionChoice(
            String text, int selectedCount, boolean answerStatus,
            MajorMultipleChoiceQuestion question) {
        super(text, selectedCount, answerStatus);
        this.question = question;
    }

    public static MajorQuestionChoice fromMajorQuestion(RequestMakeQuestionChoiceDto dto,
                                                        MajorMultipleChoiceQuestion majorMultipleChoiceQuestion) {
        MajorQuestionChoice majorQuestionChoice = MajorQuestionChoice.builder()
                .content(dto.getText())
                .selectedCount(0)
                .answerStatus(dto.isAnswerStatus())
                .question(majorMultipleChoiceQuestion)
                .build();
        majorMultipleChoiceQuestion.getQuestionChoices().add(majorQuestionChoice);
        return majorQuestionChoice;
    }
}
