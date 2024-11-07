package com.server.computerscience.question.common.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.server.computerscience.question.license.domain.LicenseCategory;
import com.server.computerscience.question.license.domain.LicenseSession;

@Service
public interface QuestionSelectorService {
	List<String> getCategories();

	List<String> getLevels();

	List<LicenseCategory> getLicenseCategories();

	List<LicenseSession> getLicenseSessions(LicenseCategory licenseCategory);
}
