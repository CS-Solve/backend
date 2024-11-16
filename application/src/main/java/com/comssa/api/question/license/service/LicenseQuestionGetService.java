package com.comssa.api.question.license.service;


import com.comssa.persistence.question.common.domain.QuestionCategory;
import com.comssa.persistence.question.license.domain.LicenseMultipleChoiceQuestion;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface LicenseQuestionGetService {
	Map<QuestionCategory, List<LicenseMultipleChoiceQuestion>> getClassifiedLicenseMultipleChoiceQuestion(
		Long sessionId);
}
