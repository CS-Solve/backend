package com.server.computerscience.question.license.service;

import com.server.computerscience.question.common.domain.QuestionCategory;
import com.server.computerscience.question.license.domain.LicenseMultipleChoiceQuestion;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface LicenseQuestionGetService {
	Map<QuestionCategory, List<LicenseMultipleChoiceQuestion>> getClassifiedLicenseMultipleChoiceQuestion(
		Long sessionId);
}
