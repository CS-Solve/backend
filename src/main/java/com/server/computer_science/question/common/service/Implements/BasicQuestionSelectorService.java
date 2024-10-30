package com.server.computer_science.question.common.service.Implements;

import com.server.computer_science.question.common.domain.QuestionCategory;
import com.server.computer_science.question.common.domain.QuestionLevel;
import com.server.computer_science.question.common.service.QuestionSelectorService;
import com.server.computer_science.question.license_question.domain.LicenseCategory;
import com.server.computer_science.question.license_question.domain.LicenseSession;
import com.server.computer_science.question.license_question.repository.LicenseSessionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BasicQuestionSelectorService implements QuestionSelectorService {
    private LicenseSessionRepository licenseSessionRepository;

    @Override
    public List<String> getCategories() {
        return Arrays.stream(QuestionCategory.values())
                .filter(QuestionCategory::isCanBeShow)
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
