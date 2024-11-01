package com.server.computer_science.question.license_question.domain;

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
public class LicenseQuestionChoice extends QuestionChoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "license_multiple_choice_question_id")
    private LicenseMultipleChoiceQuestion licenseMultipleChoiceQuestion;

    public static LicenseQuestionChoice from(
            RequestMakeMajorQuestionChoiceDto dto,
            LicenseMultipleChoiceQuestion licenseMultipleChoiceQuestion) {
        LicenseQuestionChoice questionChoice =  LicenseQuestionChoice.builder()
                .text(dto.getText())
                .selectedCount(0)
                .answerStatus(dto.isAnswerStatus())
                .licenseMultipleChoiceQuestion(licenseMultipleChoiceQuestion)
                .build();
        licenseMultipleChoiceQuestion.getQuestionChoices().add(questionChoice);
        return questionChoice;
    }

    public LicenseQuestionChoice(String text, int selectedCount, boolean answerStatus, LicenseMultipleChoiceQuestion licenseMultipleChoiceQuestion) {
        super(text, selectedCount, answerStatus);
        this.licenseMultipleChoiceQuestion = licenseMultipleChoiceQuestion;
    }
}
