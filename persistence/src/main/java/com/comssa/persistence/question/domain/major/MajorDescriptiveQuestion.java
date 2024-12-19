package com.comssa.persistence.question.domain.major;

import com.comssa.persistence.question.domain.common.Question;
import com.comssa.persistence.question.dto.major.request.RequestMakeMajorDescriptiveQuestionDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@ToString
@DiscriminatorValue("MD")
public class MajorDescriptiveQuestion extends Question {
    private String gradeStandard;

    public static MajorDescriptiveQuestion makeWithDto(
            RequestMakeMajorDescriptiveQuestionDto dto) {
        MajorDescriptiveQuestion question = MajorDescriptiveQuestion.builder()
                .content(dto.getContent())
                .questionCategory(dto.getQuestionCategory())
                .questionLevel(dto.getQuestionLevel())
                .description(dto.getDescription())
                .gradeStandard(dto.getGradeStandard())
                .imageUrl(null)
                .build();
        question.initDefaults();
        return question;
    }

    @Override
    public void initDefaults() {

    }

    public void changeGradeStandard(String gradeStandard) {
        this.gradeStandard = gradeStandard;
    }
}
