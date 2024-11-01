package com.server.computer_science.question.license_question.domain;

import com.server.computer_science.question.common.domain.Question;
import com.server.computer_science.question.major_question.admin.dto.RequestMakeMajorMultipleChoiceQuestionDto;
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
public class LicenseMultipleChoiceQuestion extends Question {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name ="license_session_id")
    private LicenseSession licenseSession;
    @Enumerated(value = EnumType.STRING)
    protected LicenseCategory licenseCategory;

    @OneToMany(mappedBy = "licenseNormalQuestion",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<LicenseQuestionChoice> normalQuestionChoices;

    @Override
    public void initDefaults() {
        this.normalQuestionChoices = new ArrayList<>();
    }

    public static LicenseMultipleChoiceQuestion makeWithDto(RequestMakeMajorMultipleChoiceQuestionDto dto, LicenseSession licenseSession, LicenseCategory licenseCategory){
        LicenseMultipleChoiceQuestion licenseMultipleChoiceQuestion = LicenseMultipleChoiceQuestion.builder()
                .content(dto.getContent())
                .questionCategory(dto.getQuestionCategory())
                .questionLevel(dto.getQuestionLevel())
                .description(dto.getDescription())
                .imageUrl(null)
                .licenseSession(licenseSession)
                .licenseCategory(licenseCategory)
                .build();
        licenseMultipleChoiceQuestion.initDefaults();
        return licenseMultipleChoiceQuestion;
    }
    /**
     * Dto 반환시 Generic을 쓰기위해 상위 추상 클래스에 포함한 메소드
     */
    @Override
    public Long getId() {
        return id;
    }
}
