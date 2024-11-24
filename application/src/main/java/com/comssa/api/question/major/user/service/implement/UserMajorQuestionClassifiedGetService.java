package com.comssa.api.question.major.user.service.implement;


import com.comssa.api.question.major.common.service.QuestionClassifyByCategoryService;
import com.comssa.api.question.major.common.service.implement.MajorMultipleChoiceQuestionDbService;
import com.comssa.api.question.major.user.service.MajorQuestionClassifiedGetService;
import com.comssa.persistence.question.common.domain.QuestionCategory;
import com.comssa.persistence.question.major.domain.common.MajorDescriptiveQuestion;
import com.comssa.persistence.question.major.domain.common.MajorMultipleChoiceQuestion;
import com.comssa.persistence.question.major.repository.MajorDescriptiveQuestionRepository;
import com.comssa.persistence.question.major.user.dto.request.RequestGetQuestionByCategoryAndLevelDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserMajorQuestionClassifiedGetService implements MajorQuestionClassifiedGetService {
	private final MajorMultipleChoiceQuestionDbService majorMultipleChoiceQuestionDbService;
	private final QuestionClassifyByCategoryService questionClassifyByCategoryService;
	private final MajorDescriptiveQuestionRepository majorDescriptiveQuestionRepository;

	/**
	 * 분야, 난이도 파라미터로 문제를 조회하는 경우 - 객관식.
	 * 문제 선택지들을 섞어준다.
	 */

	@Override
	public Map<QuestionCategory, List<MajorMultipleChoiceQuestion>> getApprovedClassifiedMajorMultipleChoiceQuestions(
		RequestGetQuestionByCategoryAndLevelDto requestGetQuestionByCategoryAndLevelDto) {
		List<MajorMultipleChoiceQuestion> majorMultipleChoiceQuestions = majorMultipleChoiceQuestionDbService
			.findAllFetchChoicesByCategoriesAndLevelsApproved(
				requestGetQuestionByCategoryAndLevelDto.getQuestionCategories(),
				requestGetQuestionByCategoryAndLevelDto.getQuestionLevels());
		for (MajorMultipleChoiceQuestion majorMultipleChoiceQuestion : majorMultipleChoiceQuestions) {
			Collections.shuffle(majorMultipleChoiceQuestion.getQuestionChoices());
		}
		return questionClassifyByCategoryService.classifyQuestionByCategoryOrdered(majorMultipleChoiceQuestions);
	}

	/**
	 * 분야, 난이도 파라미터로 문제를 조회하는 경우 - 주관식
	 */
	@Override
	public Map<QuestionCategory, List<MajorMultipleChoiceQuestion>> getApprovedClassifiedShortAnsweredMajorQuestions(
		RequestGetQuestionByCategoryAndLevelDto requestGetQuestionByCategoryAndLevelDto) {
		List<MajorMultipleChoiceQuestion> majorMultipleChoiceQuestions = majorMultipleChoiceQuestionDbService
			.findAllFetchChoicesByCategoriesAndLevelsApprovedAndShortAnswered(
				requestGetQuestionByCategoryAndLevelDto.getQuestionCategories(),
				requestGetQuestionByCategoryAndLevelDto.getQuestionLevels());
		return questionClassifyByCategoryService.classifyQuestionByCategoryOrdered(majorMultipleChoiceQuestions);
	}

	/**
	 * 분야, 난이도 파라미터로 문제를 조회하는 경우 - 서술형
	 */
	@Override
	public Map<QuestionCategory, List<MajorDescriptiveQuestion>> getApprovedClassifiedDescriptiveQuestions(
		RequestGetQuestionByCategoryAndLevelDto requestGetQuestionByCategoryAndLevelDto) {
		List<MajorDescriptiveQuestion> majorDescriptiveQuestions = majorDescriptiveQuestionRepository
			.findWithCategoriesAndLevelsAndIfApproved(
				requestGetQuestionByCategoryAndLevelDto.getQuestionCategories(),
				requestGetQuestionByCategoryAndLevelDto.getQuestionLevels());
		return questionClassifyByCategoryService.classifyQuestionByCategoryOrdered(majorDescriptiveQuestions);
	}

}
