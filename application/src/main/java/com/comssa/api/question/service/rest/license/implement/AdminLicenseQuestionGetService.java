package com.comssa.api.question.service.rest.license.implement;


import com.comssa.api.question.service.rest.common.QuestionClassifyByCategoryService;
import com.comssa.api.question.service.rest.license.LicenseQuestionGetService;
import com.comssa.persistence.question.common.domain.Question;
import com.comssa.persistence.question.common.domain.QuestionCategory;
import com.comssa.persistence.question.license.domain.LicenseMultipleChoiceQuestion;
import com.comssa.persistence.question.license.repository.LicenseMultipleChoiceQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

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
	public Map<QuestionCategory, List<Question>> getClassifiedLicenseMultipleChoiceQuestion(
		Long sessionId) {
		List<LicenseMultipleChoiceQuestion> licenseMultipleChoiceQuestions = licenseMultipleChoiceQuestionRepository
			.findAllByLicenseSessionIdFetchChoicesOrderByApproved(sessionId);
		Map<QuestionCategory, List<Question>> questionsByCategory = questionClassifyByCategoryService.classifyQuestionByCategoryOrdered(licenseMultipleChoiceQuestions);
		return questionClassifyByCategoryService.classifyQuestionByCategoryOrdered(licenseMultipleChoiceQuestions);
	}
}
