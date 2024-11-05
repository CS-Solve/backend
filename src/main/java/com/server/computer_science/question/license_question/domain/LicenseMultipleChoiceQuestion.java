package com.server.computer_science.question.license_question.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.server.computer_science.question.common.domain.ChoiceProvider;
import com.server.computer_science.question.common.domain.Question;
import com.server.computer_science.question.common.domain.QuestionCategory;
import com.server.computer_science.question.common.domain.QuestionLevel;
import com.server.computer_science.question.major_question.admin.dto.RequestMakeMultipleChoiceQuestionDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
public class LicenseMultipleChoiceQuestion extends Question implements ChoiceProvider {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "license_session_id")
	private LicenseSession licenseSession;
	@Enumerated(value = EnumType.STRING)
	protected LicenseCategory licenseCategory;

	@OneToMany(mappedBy = "licenseMultipleChoiceQuestion", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<LicenseQuestionChoice> questionChoices;

	@Override
	public void initDefaults() {
		this.questionChoices = new ArrayList<>();
	}

	public static LicenseMultipleChoiceQuestion makeWithDto(RequestMakeMultipleChoiceQuestionDto dto,
		LicenseSession licenseSession, LicenseCategory licenseCategory) {
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

	/**
	 * Dto 반환시 Generic을 쓰기위해 상위 추상 클래스에 포함한 메소드
	 */
	@Override
	public Long getId() {
		return id;
	}
}
