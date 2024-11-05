package com.server.computer_science.question.common.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.server.computer_science.question.license_question.domain.LicenseCategory;
import com.server.computer_science.question.license_question.domain.LicenseSession;

@Service
public interface QuestionSelectorService {
	public List<String> getCategories();

	public List<String> getLevels();

	public List<LicenseCategory> getLicenseCategories();

	public List<LicenseSession> getLicenseSessions(LicenseCategory licenseCategory);
}
