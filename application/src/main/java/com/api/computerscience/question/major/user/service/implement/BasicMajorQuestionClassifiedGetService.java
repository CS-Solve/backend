package com.api.computerscience.question.major.user.service.implement;


import com.api.computerscience.question.major.common.service.QuestionClassifyByCategoryService;
import com.api.computerscience.question.major.common.service.implement.MajorMultipleChoiceQuestionDbService;
import com.api.computerscience.question.major.user.service.MajorQuestionClassifiedGetService;
import com.persistence.computerscience.question.common.domain.QuestionCategory;
import com.persistence.computerscience.question.major.domain.common.MajorMultipleChoiceQuestion;
import com.persistence.computerscience.question.major.domain.user.dto.request.RequestGetQuestionByCategoryAndLevelDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BasicMajorQuestionClassifiedGetService implements MajorQuestionClassifiedGetService {
	private final MajorMultipleChoiceQuestionDbService majorMultipleChoiceQuestionDbService;
	private final QuestionClassifyByCategoryService questionClassifyByCategoryService;

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
}
