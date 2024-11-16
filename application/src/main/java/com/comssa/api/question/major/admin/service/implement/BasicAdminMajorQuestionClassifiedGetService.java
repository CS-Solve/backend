package com.comssa.api.question.major.admin.service.implement;


import com.comssa.api.question.major.admin.service.AdminMajorQuestionClassifiedGetService;
import com.comssa.api.question.major.common.service.QuestionClassifyByCategoryService;
import com.comssa.api.question.major.common.service.implement.MajorMultipleChoiceQuestionDbService;
import com.comssa.persistence.question.common.domain.QuestionCategory;
import com.comssa.persistence.question.major.domain.common.MajorMultipleChoiceQuestion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BasicAdminMajorQuestionClassifiedGetService implements AdminMajorQuestionClassifiedGetService {
	private final MajorMultipleChoiceQuestionDbService majorMultipleChoiceQuestionDbService;
	private final QuestionClassifyByCategoryService questionClassifyByCategoryService;

	/**
	 * 관리자가 조회시 Approve됐는지 기준으로 정렬되며(false인 것부터),
	 * 이후엔 객관식 -> 주관식으로 정렬된다.
	 *
	 * @return
	 */
	@Override
	public Map<QuestionCategory, List<MajorMultipleChoiceQuestion>> getClassifiedAllMajorQuestions() {
		List<MajorMultipleChoiceQuestion> majorMultipleChoiceQuestions = majorMultipleChoiceQuestionDbService
			.findAllFetchChoicesSortedByApproveAndShortAnswered();
		return questionClassifyByCategoryService.classifyQuestionByCategoryOrdered(majorMultipleChoiceQuestions);
	}
}