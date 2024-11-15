package com.study.computerscience.question.license.service;

import com.study.computerscience.question.common.domain.QuestionCategory;
import com.study.computerscience.question.license.domain.LicenseMultipleChoiceQuestion;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface LicenseQuestionGetService {
	Map<QuestionCategory, List<LicenseMultipleChoiceQuestion>> getClassifiedLicenseMultipleChoiceQuestion(
		Long sessionId);
}
