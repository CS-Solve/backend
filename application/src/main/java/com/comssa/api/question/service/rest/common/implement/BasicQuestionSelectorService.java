package com.comssa.api.question.service.rest.common.implement;


import com.comssa.api.question.service.rest.common.QuestionSelectorService;
import com.comssa.persistence.question.domain.common.Question;
import com.comssa.persistence.question.domain.common.QuestionCategory;
import com.comssa.persistence.question.domain.common.QuestionLevel;
import com.comssa.persistence.question.domain.license.LicenseCategory;
import com.comssa.persistence.question.domain.license.LicenseSession;
import com.comssa.persistence.question.repository.jpa.LicenseSessionRepository;
import com.comssa.persistence.question.repository.querydsl.LicenseMultipleChoiceQuestionDslRepository;
import com.comssa.persistence.question.repository.querydsl.MajorMultipleChoiceQuestionDslRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BasicQuestionSelectorService implements QuestionSelectorService {
	private final LicenseSessionRepository licenseSessionRepository;
	private final MajorMultipleChoiceQuestionDslRepository majorMultipleChoiceQuestionDslRepository;
	private final LicenseMultipleChoiceQuestionDslRepository licenseMultipleChoiceQuestionDslRepository;

	@Override
	public List<String> getCategories() {
		return Arrays.stream(QuestionCategory.values())
			.filter(QuestionCategory::isCanBeShownInMajor)
			.map(QuestionCategory::getKorean)
			.collect(Collectors.toList());
	}

	@Override
	public List<String> getLevels() {
		return Arrays.stream(QuestionLevel.values())
			.filter(level -> level != QuestionLevel.NONE)
			.map(QuestionLevel::getKorean)
			.collect(Collectors.toList());
	}

	@Override
	public List<LicenseCategory> getLicenseCategories() {
		return Arrays.stream(LicenseCategory.values())
			.collect(Collectors.toList());
	}

	@Override
	public List<LicenseSession> getLicenseSessions(LicenseCategory licenseCategory) {
		return licenseSessionRepository.findAllByLicenseCategory(licenseCategory);
	}

	@Override
	public List<? extends Question> getAllQuestions(List<QuestionCategory> questionCategories, boolean multipleChoice) {
		List<Question> questions = new ArrayList<>();
		if (multipleChoice) {
			questions.addAll(licenseMultipleChoiceQuestionDslRepository.findAllWhereQuestionCategories(
				questionCategories));
			questions.addAll(majorMultipleChoiceQuestionDslRepository
				.findAllWhereQuestionCategories(
					questionCategories));
		}
		return questions;
	}
}
