package com.api.computerscience.question.major.user.service;


import com.persistence.computerscience.question.common.domain.QuestionCategory;
import com.persistence.computerscience.question.major.domain.common.MajorMultipleChoiceQuestion;
import com.persistence.computerscience.question.major.domain.user.dto.request.RequestGetQuestionByCategoryAndLevelDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface MajorQuestionClassifiedGetService {
	/**
	 * 분류별로 나누어 문제를 반환
	 */
	Map<QuestionCategory, List<MajorMultipleChoiceQuestion>> getApprovedClassifiedMajorMultipleChoiceQuestions(
		RequestGetQuestionByCategoryAndLevelDto requestGetQuestionByCategoryAndLevelDto);

	Map<QuestionCategory, List<MajorMultipleChoiceQuestion>> getApprovedClassifiedShortAnsweredMajorQuestions(
		RequestGetQuestionByCategoryAndLevelDto requestGetQuestionByCategoryAndLevelDto);

}
