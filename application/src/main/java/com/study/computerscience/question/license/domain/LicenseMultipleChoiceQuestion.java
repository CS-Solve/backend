package com.study.computerscience.question.license.domain;

import com.study.computerscience.question.common.domain.ChoiceProvider;
import com.study.computerscience.question.common.domain.Question;
import com.study.computerscience.question.common.domain.QuestionCategory;
import com.study.computerscience.question.common.domain.QuestionLevel;
import com.study.computerscience.question.major.admin.dto.RequestMakeMultipleChoiceQuestionDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@ToString
public class LicenseMultipleChoiceQuestion extends Question implements ChoiceProvider {
	@Enumerated(value = EnumType.STRING)
	protected LicenseCategory licenseCategory;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "license_session_id")
	private LicenseSession licenseSession;
	@OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<LicenseQuestionChoice> questionChoices;

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

	@Override
	public void initDefaults() {
		this.questionChoices = new ArrayList<>();
	}

	/**
	 * Dto 반환시 Generic을 쓰기위해 상위 추상 클래스에 포함한 메소드
	 */
	@Override
	public Long getId() {
		return id;
	}
}
