package com.server.computerscience.question.common.service;

import com.server.computerscience.question.common.domain.Question;
import com.server.computerscience.question.common.domain.QuestionCategory;
import com.server.computerscience.question.license.domain.LicenseCategory;
import com.server.computerscience.question.license.domain.LicenseSession;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuestionSelectorService {
	List<String> getCategories();

	List<String> getLevels();

	List<LicenseCategory> getLicenseCategories();

	List<LicenseSession> getLicenseSessions(LicenseCategory licenseCategory);

	/*
	카테고리에 맞는 모든 문제를 가져온다(혼합될 수 있음)
	 */
	List<? extends Question> getAllQuestions(List<QuestionCategory> questionCategories, boolean multipleChoice);
}
