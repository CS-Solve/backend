package com.server.computerscience.question.major.user.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.server.computerscience.question.common.domain.QuestionCategory;
import com.server.computerscience.question.major.common.domain.MajorMultipleChoiceQuestion;
import com.server.computerscience.question.major.user.dto.request.RequestGetQuestionByCategoryAndLevelDto;

@Service
public interface MajorQuestionClassifiedGetService {
	/**
	 * 분류별로 나누어 문제를 반환
	 */
	public Map<QuestionCategory, List<MajorMultipleChoiceQuestion>> getApprovedClassifiedMajorMultipleChoiceQuestions(
		RequestGetQuestionByCategoryAndLevelDto requestGetQuestionByCategoryAndLevelDto);

	public Map<QuestionCategory, List<MajorMultipleChoiceQuestion>> getApprovedClassifiedShortAnsweredMajorQuestions(
		RequestGetQuestionByCategoryAndLevelDto requestGetQuestionByCategoryAndLevelDto);

}
