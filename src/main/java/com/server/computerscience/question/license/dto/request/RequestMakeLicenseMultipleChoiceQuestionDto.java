package com.server.computerscience.question.license.dto.request;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.server.computerscience.question.license.domain.LicenseCategory;
import com.server.computerscience.question.license.domain.LicenseMultipleChoiceQuestion;
import com.server.computerscience.question.license.domain.LicenseQuestionChoice;
import com.server.computerscience.question.major.admin.dto.RequestMakeMultipleChoiceQuestionDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
public class RequestMakeLicenseMultipleChoiceQuestionDto {
	private List<RequestMakeMultipleChoiceQuestionDto> questions;
	private String licenseSession;
	private LicenseCategory licenseCategory;

	public static RequestMakeLicenseMultipleChoiceQuestionDto from(
		String licenseSession
		, LicenseCategory licenseCategory
		, List<LicenseMultipleChoiceQuestion> questions
		, Function<LicenseMultipleChoiceQuestion, List<LicenseQuestionChoice>> questionChoiceExtractor) {
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
