package com.comssa.api.question.service.rest.major.implement;


import com.comssa.api.question.service.rest.common.QuestionClassifyByCategoryService;
import com.comssa.api.question.service.rest.major.AdminMajorQuestionClassifiedGetService;
import com.comssa.persistence.question.domain.common.Question;
import com.comssa.persistence.question.domain.common.QuestionCategory;
import com.comssa.persistence.question.domain.major.MajorDescriptiveQuestion;
import com.comssa.persistence.question.domain.major.MajorMultipleChoiceQuestion;
import com.comssa.persistence.question.repository.queryDslImpl.MajorDescriptiveRepositoryJpaQuery;
import com.comssa.persistence.question.repository.queryDslImpl.MajorMultipleChoiceQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class BasicAdminMajorQuestionClassifiedGetService implements AdminMajorQuestionClassifiedGetService {
	private final QuestionClassifyByCategoryService questionClassifyByCategoryService;
	private final MajorDescriptiveRepositoryJpaQuery majorDescriptiveQuestionChooseRepository;
	private final MajorMultipleChoiceQuestionRepository majorMultipleChoiceQuestionRepository;

	/**
	 * 관리자가 조회시 Approve됐는지 기준으로 정렬되며(false인 것부터),
	 * 이후엔 객관식 -> 주관식으로 정렬된다.
	 *
	 * @return
	 */
	@Override
	public Map<QuestionCategory, List<Question>> getClassifiedAllMajorMultipleChoiceQuestions() {
		List<MajorMultipleChoiceQuestion> majorMultipleChoiceQuestions = majorMultipleChoiceQuestionRepository.
			findAllOrderByIfApprovedAsc();
		return questionClassifyByCategoryService.classifyQuestionByCategoryOrdered(majorMultipleChoiceQuestions);
	}

	@Override
	public Map<QuestionCategory, List<Question>> getClassifiedAllMajorDescriptiveQuestions() {
		List<MajorDescriptiveQuestion> majorDescriptiveQuestions =
			majorDescriptiveQuestionChooseRepository.findAllOrderByIfApprovedAsc();
		return questionClassifyByCategoryService.classifyQuestionByCategoryOrdered(majorDescriptiveQuestions);
	}
}
