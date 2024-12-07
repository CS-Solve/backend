package com.comssa.persistence.question.license.dto.request;

import com.comssa.persistence.question.license.domain.LicenseCategory;
import com.comssa.persistence.question.license.domain.LicenseMultipleChoiceQuestion;
import com.comssa.persistence.question.license.domain.LicenseQuestionChoice;
import com.comssa.persistence.question.major.admin.dto.RequestMakeMajorMultipleChoiceQuestionDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
public class RequestMakeLicenseMultipleChoiceQuestionDto {
	private List<RequestMakeMajorMultipleChoiceQuestionDto> questions;
	private String licenseSession;
	private LicenseCategory licenseCategory;

	public static RequestMakeLicenseMultipleChoiceQuestionDto from(
		String licenseSession,
		LicenseCategory licenseCategory,
		List<LicenseMultipleChoiceQuestion> questions,
		Function<LicenseMultipleChoiceQuestion, List<LicenseQuestionChoice>> questionChoiceExtractor) {
		return RequestMakeLicenseMultipleChoiceQuestionDto.builder()
			.questions(
				questions.stream()
					.map(
						q -> RequestMakeMajorMultipleChoiceQuestionDto.from(q, questionChoiceExtractor.apply(q))
					)
					.collect(Collectors.toList())

			)
			.licenseSession(licenseSession)
			.licenseCategory(licenseCategory)
			.build();
	}
}
