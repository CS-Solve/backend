package com.server.computer_science.question.license_question.domain;

import com.server.computer_science.question.common.domain.Question;
import com.server.computer_science.question.normal_question.common.domain.NormalQuestionChoice;
import com.server.computer_science.question.normal_question.admin.dto.RequestMakeNormalQuestionDto;
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
public class LicenseNormalQuestion extends Question {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name ="license_session_id")
    private LicenseSession licenseSession;
    @Enumerated(value = EnumType.STRING)
    protected LicenseCategory licenseCategory;

    @OneToMany(mappedBy = "licenseNormalQuestion",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<LicenseNormalQuestionChoice> normalQuestionChoices;

    @Override
    public void initDefaults() {
        this.normalQuestionChoices = new ArrayList<>();
    }

    public static LicenseNormalQuestion makeWithDto(RequestMakeNormalQuestionDto dto,LicenseSession licenseSession,LicenseCategory licenseCategory){
        LicenseNormalQuestion licenseNormalQuestion = LicenseNormalQuestion.builder()
                .content(dto.getContent())
                .questionCategory(dto.getQuestionCategory())
                .questionLevel(dto.getQuestionLevel())
                .description(dto.getDescription())
                .imageUrl(null)
                .licenseSession(licenseSession)
                .licenseCategory(licenseCategory)
                .build();
        licenseNormalQuestion.initDefaults();
        return licenseNormalQuestion;
    }
}
