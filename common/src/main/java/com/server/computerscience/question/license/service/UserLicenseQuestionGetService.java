package com.server.computerscience.question.license.service;

import com.server.computerscience.question.common.domain.QuestionCategory;
import com.server.computerscience.question.license.domain.LicenseMultipleChoiceQuestion;
import com.server.computerscience.question.license.repository.LicenseMultipleChoiceQuestionRepository;
import com.server.computerscience.question.major.common.service.QuestionClassifyByCategoryService;
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