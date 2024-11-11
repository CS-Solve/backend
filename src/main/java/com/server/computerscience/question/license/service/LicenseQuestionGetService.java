package com.server.computerscience.question.license.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.server.computerscience.question.common.domain.QuestionCategory;
import com.server.computerscience.question.license.domain.LicenseMultipleChoiceQuestion;

@Service
public interface LicenseQuestionGetService {
	public Map<QuestionCategory, List<LicenseMultipleChoiceQuestion>> getClassifiedLicenseMultipleChoiceQuestion(
		Long sessionId);
}
