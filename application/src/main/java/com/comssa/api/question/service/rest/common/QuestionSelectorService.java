package com.comssa.api.question.service.rest.common;


import com.comssa.persistence.question.domain.common.Question;
import com.comssa.persistence.question.domain.common.QuestionCategory;
import com.comssa.persistence.question.domain.license.LicenseCategory;
import com.comssa.persistence.question.domain.license.LicenseSession;
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
