package com.comssa.api.question.service.rest.major;


import com.comssa.persistence.question.domain.common.Question;
import com.comssa.persistence.question.domain.common.QuestionCategory;
import com.comssa.persistence.question.dto.major.request.RequestGetQuestionByCategoryAndLevelDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface MajorQuestionClassifiedGetService<T extends Question> {
	/**
	 * 분류별로 나누어 문제를 반환
	 */
	Map<QuestionCategory, List<T>> getApprovedClassifiedMajorMultipleChoiceQuestions(
		RequestGetQuestionByCategoryAndLevelDto requestGetQuestionByCategoryAndLevelDto);


	Map<QuestionCategory, List<T>> getApprovedClassifiedDescriptiveQuestions(
		RequestGetQuestionByCategoryAndLevelDto requestGetQuestionByCategoryAndLevelDto
	);

}

