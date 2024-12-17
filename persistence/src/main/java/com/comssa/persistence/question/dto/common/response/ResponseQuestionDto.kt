package com.comssa.persistence.question.dto.common.response;

import com.comssa.persistence.question.domain.common.Question;
import com.comssa.persistence.question.domain.common.QuestionCategory;
import com.comssa.persistence.question.domain.common.QuestionLevel;
import com.comssa.persistence.question.domain.license.LicenseMultipleChoiceQuestion;
import com.comssa.persistence.question.domain.major.MajorDescriptiveQuestion;
import com.comssa.persistence.question.domain.major.MajorMultipleChoiceQuestion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@SuperBuilder
public class ResponseQuestionDto {
    private Long id;
    private String content;
    private String description;
    private String imageUrl;
    private QuestionCategory questionCategory;
    private QuestionLevel questionLevel;
    private boolean ifApproved;
    private boolean ifMultipleChoice;

    public static ResponseQuestionDto from(Question question) {
        if (question instanceof LicenseMultipleChoiceQuestion) {
            return ResponseMultipleChoiceQuestionDto.forLicense((LicenseMultipleChoiceQuestion) question);
        }
        if (question instanceof MajorMultipleChoiceQuestion) {
            return ResponseMultipleChoiceQuestionDto.forMajor((MajorMultipleChoiceQuestion) question);
        }
        if (question instanceof MajorDescriptiveQuestion) {
            return ResponseDescriptiveQuestionDto.forMajor((MajorDescriptiveQuestion) question);
        }
        throw new IllegalArgumentException("Unsupported question type: " + question.getClass().getName());
    }
}
