package com.comssa.api.question.service.common.implement;


import com.comssa.api.question.service.common.QuestionSelectorService;
import com.comssa.persistence.question.common.domain.Question;
import com.comssa.persistence.question.common.domain.QuestionCategory;
import com.comssa.persistence.question.common.domain.QuestionLevel;
import com.comssa.persistence.question.license.domain.LicenseCategory;
import com.comssa.persistence.question.license.domain.LicenseSession;
import com.comssa.persistence.question.license.repository.LicenseMultipleChoiceQuestionRepository;
import com.comssa.persistence.question.license.repository.LicenseSessionRepository;
import com.comssa.persistence.question.major.repository.MajorMultipleChoiceQuestionRepository;
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
	private final MajorMultipleChoiceQuestionRepository majorMultipleChoiceQuestionRepository;
	private final LicenseMultipleChoiceQuestionRepository licenseMultipleChoiceQuestionRepository;

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
			questions.addAll(licenseMultipleChoiceQuestionRepository.findAllByQuestionCategoriesFetchChoices(
				questionCategories));
			questions.addAll(majorMultipleChoiceQuestionRepository.findAllByQuestionCategoriesFetchChoices(
				questionCategories));
		}
		return questions;
	}
}
