package com.server.computerscience.question.common.service.implement;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.server.computerscience.question.common.domain.QuestionCategory;
import com.server.computerscience.question.common.domain.QuestionLevel;
import com.server.computerscience.question.common.service.QuestionSelectorService;
import com.server.computerscience.question.license.domain.LicenseCategory;
import com.server.computerscience.question.license.domain.LicenseSession;
import com.server.computerscience.question.license.repository.LicenseSessionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BasicQuestionSelectorService implements QuestionSelectorService {
	private final LicenseSessionRepository licenseSessionRepository;

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
}
