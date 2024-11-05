package com.server.computer_science.question.major_question.user.service.implement;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.server.computer_science.question.common.domain.QuestionCategory;
import com.server.computer_science.question.major_question.common.domain.MajorMultipleChoiceQuestion;
import com.server.computer_science.question.major_question.common.service.QuestionClassifyByCategoryService;
import com.server.computer_science.question.major_question.common.service.implement.MajorMultipleChoiceQuestionDBService;
import com.server.computer_science.question.major_question.user.dto.request.RequestGetQuestionByCategoryAndLevelDto;
import com.server.computer_science.question.major_question.user.service.MajorQuestionClassifiedGetService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BasicMajorQuestionClassifiedGetService implements MajorQuestionClassifiedGetService {
	private final MajorMultipleChoiceQuestionDBService majorMultipleChoiceQuestionDBService;
	private final QuestionClassifyByCategoryService questionClassifyByCategoryService;

	/**
	 * 분야, 난이도 파라미터로 문제를 조회하는 경우 - 객관식.
	 * 문제 선택지들을 섞어준다.
	 */

	@Override
	public Map<QuestionCategory, List<MajorMultipleChoiceQuestion>> getApprovedClassifiedMajorMultipleChoiceQuestions(
		RequestGetQuestionByCategoryAndLevelDto requestGetQuestionByCategoryAndLevelDto) {
		List<MajorMultipleChoiceQuestion> majorMultipleChoiceQuestions = majorMultipleChoiceQuestionDBService.findAllFetchChoicesByCategoriesAndLevelsApproved(
			requestGetQuestionByCategoryAndLevelDto.getQuestionCategories(),
			requestGetQuestionByCategoryAndLevelDto.getQuestionLevels());
		for (MajorMultipleChoiceQuestion majorMultipleChoiceQuestion : majorMultipleChoiceQuestions) {
			Collections.shuffle(majorMultipleChoiceQuestion.getQuestionChoices());
		}
		return questionClassifyByCategoryService.classifyQuestionByCategoryOrdered(majorMultipleChoiceQuestions);
	}

	/**
	 분야, 난이도 파라미터로 문제를 조회하는 경우 - 주관식
	 */
	@Override
	public Map<QuestionCategory, List<MajorMultipleChoiceQuestion>> getApprovedClassifiedShortAnsweredMajorQuestions(
		RequestGetQuestionByCategoryAndLevelDto requestGetQuestionByCategoryAndLevelDto) {
		List<MajorMultipleChoiceQuestion> majorMultipleChoiceQuestions = majorMultipleChoiceQuestionDBService.findAllFetchChoicesByCategoriesAndLevelsApprovedAndShortAnswered(
			requestGetQuestionByCategoryAndLevelDto.getQuestionCategories(),
			requestGetQuestionByCategoryAndLevelDto.getQuestionLevels());
		return questionClassifyByCategoryService.classifyQuestionByCategoryOrdered(majorMultipleChoiceQuestions);
	}
}
