package com.api.computerscience.question.license.service;


import com.api.computerscience.question.major.common.service.QuestionClassifyByCategoryService;
import com.persistence.computerscience.question.common.domain.QuestionCategory;
import com.persistence.computerscience.question.license.domain.LicenseMultipleChoiceQuestion;
import com.persistence.computerscience.question.license.repository.LicenseMultipleChoiceQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserLicenseQuestionGetService implements LicenseQuestionGetService {
	private final LicenseMultipleChoiceQuestionRepository licenseMultipleChoiceQuestionRepository;
	private final QuestionClassifyByCategoryService questionClassifyByCategoryService;

	public Map<QuestionCategory, List<LicenseMultipleChoiceQuestion>> getClassifiedLicenseMultipleChoiceQuestion(
		Long sessionId) {
		List<LicenseMultipleChoiceQuestion> licenseMultipleChoiceQuestions = licenseMultipleChoiceQuestionRepository
			.findAllByLicenseSessionIdFetchChoicesAndIfApproved(
				sessionId);
		for (LicenseMultipleChoiceQuestion licenseMultipleChoiceQuestion : licenseMultipleChoiceQuestions) {
			Collections.shuffle(licenseMultipleChoiceQuestion.getQuestionChoices());
		}
		return questionClassifyByCategoryService.classifyQuestionByCategoryOrdered(licenseMultipleChoiceQuestions);
	}
}
