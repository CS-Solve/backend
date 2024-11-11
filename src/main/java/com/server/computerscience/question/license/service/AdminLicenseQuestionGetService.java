package com.server.computerscience.question.license.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.server.computerscience.question.common.domain.QuestionCategory;
import com.server.computerscience.question.license.domain.LicenseMultipleChoiceQuestion;
import com.server.computerscience.question.license.repository.LicenseMultipleChoiceQuestionRepository;
import com.server.computerscience.question.major.common.service.QuestionClassifyByCategoryService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminLicenseQuestionGetService implements LicenseQuestionGetService {
	private final LicenseMultipleChoiceQuestionRepository licenseMultipleChoiceQuestionRepository;
	private final QuestionClassifyByCategoryService questionClassifyByCategoryService;

	/**
	 * 관리자 조회시 허용 여부와 관련 없이 모든 문제를 가져온다.
	 * 문제지도 섞지 않는다.
	 * 허용 여부 순대로 섞는다
	 */
	@Override
	public Map<QuestionCategory, List<LicenseMultipleChoiceQuestion>> getClassifiedLicenseMultipleChoiceQuestion(
		Long sessionId) {
		List<LicenseMultipleChoiceQuestion> licenseMultipleChoiceQuestions = licenseMultipleChoiceQuestionRepository
			.findAllByLicenseSessionIdFetchChoicesOrderByApproved(sessionId);
		return questionClassifyByCategoryService.classifyQuestionByCategoryOrdered(licenseMultipleChoiceQuestions);
	}
}
