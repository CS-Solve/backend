package com.server.computerscience.question.common.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.server.computerscience.question.license.domain.LicenseCategory;
import com.server.computerscience.question.license.domain.LicenseSession;

@Service
public interface QuestionSelectorService {
	public List<String> getCategories();

	public List<String> getLevels();

	public List<LicenseCategory> getLicenseCategories();

	public List<LicenseSession> getLicenseSessions(LicenseCategory licenseCategory);
}
