package com.comssa.persistence.question.dto.common.response;

import com.comssa.persistence.question.domain.major.MajorDescriptiveQuestion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@SuperBuilder
public class ResponseDescriptiveQuestionDto extends ResponseQuestionDto {
    private String gradeStandard;

    public static ResponseDescriptiveQuestionDto forMajor(MajorDescriptiveQuestion question) {
        return ResponseDescriptiveQuestionDto.builder()
                .id(question.getId())
                .content(question.getContent())
                .questionCategory(question.getQuestionCategory())
                .questionLevel(question.getQuestionLevel())
                .description(question.getDescription())
                .imageUrl(question.getImageUrl())
                .ifMultipleChoice(false)
                .ifApproved(question.isIfApproved())
                .gradeStandard(question.getGradeStandard())
                .build();
    }

}
