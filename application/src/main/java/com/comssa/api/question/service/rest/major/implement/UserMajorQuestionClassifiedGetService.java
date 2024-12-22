package com.comssa.api.question.service.rest.major.implement;


import com.comssa.api.question.service.rest.common.QuestionClassifyByCategoryService;
import com.comssa.api.question.service.rest.major.MajorQuestionClassifiedGetService;
import com.comssa.persistence.question.domain.common.Question;
import com.comssa.persistence.question.domain.common.QuestionCategory;
import com.comssa.persistence.question.domain.major.MajorDescriptiveQuestion;
import com.comssa.persistence.question.domain.major.MajorMultipleChoiceQuestion;
import com.comssa.persistence.question.dto.major.request.RequestGetQuestionByCategoryAndLevelDto;
import com.comssa.persistence.question.repository.queryDslImpl.MajorDescriptiveQuestionRepository;
import com.comssa.persistence.question.service.QuestionRepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserMajorQuestionClassifiedGetService implements MajorQuestionClassifiedGetService<Question> {
	private final MajorMultipleChoiceQuestionDbService majorMultipleChoiceQuestionDbService;
	private final QuestionClassifyByCategoryService questionClassifyByCategoryService;
	private final MajorDescriptiveQuestionRepository majorDescriptiveQuestionChooseRepository;
	private final QuestionRepositoryService<MajorDescriptiveQuestion> questionRepositoryService;

	/**
	 * 분야, 난이도 파라미터로 문제를 조회하는 경우 - 객관식.
	 * 문제 선택지들을 섞어준다.
	 */
	@Override
	public Map<QuestionCategory, List<Question>> getApprovedClassifiedMajorMultipleChoiceQuestions(
		RequestGetQuestionByCategoryAndLevelDto requestGetQuestionByCategoryAndLevelDto) {
		List<MajorMultipleChoiceQuestion> majorMultipleChoiceQuestions = majorMultipleChoiceQuestionDbService
			.findAllFetchChoicesByCategoriesAndLevelsApproved(
				requestGetQuestionByCategoryAndLevelDto.getQuestionCategories(),
				requestGetQuestionByCategoryAndLevelDto.getQuestionLevels());
		for (MajorMultipleChoiceQuestion question : majorMultipleChoiceQuestions) {
			Collections.shuffle(question.getQuestionChoices());
		}
		return questionClassifyByCategoryService.classifyQuestionByCategoryOrdered(majorMultipleChoiceQuestions);
	}


	/**
	 * 분야, 난이도 파라미터로 문제를 조회하는 경우 - 서술형
	 */
	@Override
	public Map<QuestionCategory, List<Question>> getApprovedClassifiedDescriptiveQuestions(
		RequestGetQuestionByCategoryAndLevelDto dto) {
		List<MajorDescriptiveQuestion> majorDescriptiveQuestions = majorDescriptiveQuestionChooseRepository.
			findWithCategoriesAndLevelsAndIfApproved(
				dto.getQuestionCategories(),
				dto.getQuestionLevels(),
				true
			);
		return questionClassifyByCategoryService.classifyQuestionByCategoryOrdered(majorDescriptiveQuestions);
	}
}
