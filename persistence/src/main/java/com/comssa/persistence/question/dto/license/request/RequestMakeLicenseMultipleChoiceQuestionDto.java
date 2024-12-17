package com.comssa.persistence.question.dto.license.request;

import com.comssa.persistence.question.domain.license.LicenseCategory;
import com.comssa.persistence.question.domain.license.LicenseMultipleChoiceQuestion;
import com.comssa.persistence.question.domain.license.LicenseQuestionChoice;
import com.comssa.persistence.question.dto.common.request.RequestMakeMultipleChoiceQuestionDto;
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
	private List<RequestMakeMultipleChoiceQuestionDto> questions;
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
						q -> RequestMakeMultipleChoiceQuestionDto.from(q, questionChoiceExtractor.apply(q))
					)
					.collect(Collectors.toList())

			)
			.licenseSession(licenseSession)
			.licenseCategory(licenseCategory)
			.build();
	}
}
