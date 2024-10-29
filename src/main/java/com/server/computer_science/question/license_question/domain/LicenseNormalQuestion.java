package com.server.computer_science.question.license_question.domain;

import com.server.computer_science.question.normal_question.common.domain.QuestionChoice;
import com.server.computer_science.question.normal_question.user.dto.request.RequestMakeNormalQuestionDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
public class LicenseNormalQuestion extends LicenseQuestion{
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;

    @OneToMany(mappedBy = "licenseNormalQuestion",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<QuestionChoice> questionChoices;

    @Override
    public void initDefaults() {
        this.questionChoices = new ArrayList<>();
    }

    public static LicenseNormalQuestion makeWithDto(RequestMakeNormalQuestionDto dto,LicenseSession licenseSession){
        LicenseNormalQuestion licenseNormalQuestion = LicenseNormalQuestion.builder()
                .content(dto.getContent())
                .questionCategory(dto.getQuestionCategory())
                .questionLevel(dto.getQuestionLevel())
                .description(dto.getDescription())
                .imageUrl(null)
                .licenseSession(licenseSession)
                .build();
        licenseNormalQuestion.initDefaults();
        return licenseNormalQuestion;
    }
}
