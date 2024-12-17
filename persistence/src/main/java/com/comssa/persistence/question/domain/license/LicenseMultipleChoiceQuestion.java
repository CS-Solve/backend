package com.comssa.persistence.question.domain.license;

import com.comssa.persistence.question.domain.common.ChoiceProvider;
import com.comssa.persistence.question.domain.common.Question;
import com.comssa.persistence.question.domain.common.QuestionCategory;
import com.comssa.persistence.question.domain.common.QuestionLevel;
import com.comssa.persistence.question.dto.major.request.RequestMakeMajorMultipleChoiceQuestionDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@ToString
@DiscriminatorValue("LM")
public class LicenseMultipleChoiceQuestion extends Question implements ChoiceProvider {

	@Enumerated(value = EnumType.STRING)
	protected LicenseCategory licenseCategory;
	@ManyToOne
	@JoinColumn(name = "license_session_id")
	private LicenseSession licenseSession;
	@OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<LicenseQuestionChoice> questionChoices;

	public static LicenseMultipleChoiceQuestion makeWithDto(
		RequestMakeMajorMultipleChoiceQuestionDto dto,
		LicenseSession licenseSession,
		LicenseCategory licenseCategory) {
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

	public static LicenseMultipleChoiceQuestion makeForTest(String test) {
		LicenseMultipleChoiceQuestion licenseMultipleChoiceQuestion = LicenseMultipleChoiceQuestion.builder()
			.content(test)
			.questionCategory(QuestionCategory.ALGORITHM)
			.questionLevel(QuestionLevel.LOW)
			.description(test)
			.imageUrl(null)
			.licenseSession(LicenseSession.builder()
				.licenseCategory(LicenseCategory.SQLD)
				.content(test)
				.build())
			.licenseCategory(LicenseCategory.SQLD)
			.build();
		licenseMultipleChoiceQuestion.initDefaults();
		return licenseMultipleChoiceQuestion;
	}

	@Override
	public void initDefaults() {
		this.questionChoices = new ArrayList<>();
	}

}
