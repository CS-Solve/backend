package com.comssa.api.question.service.major;


import com.comssa.persistence.question.common.domain.Question;
import com.comssa.persistence.question.common.domain.QuestionCategory;
import com.comssa.persistence.question.major.user.dto.request.RequestGetQuestionByCategoryAndLevelDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface MajorQuestionClassifiedGetService {
	/**
	 * 분류별로 나누어 문제를 반환
	 */
	Map<QuestionCategory, List<Question>> getApprovedClassifiedMajorMultipleChoiceQuestions(
		RequestGetQuestionByCategoryAndLevelDto requestGetQuestionByCategoryAndLevelDto);


	Map<QuestionCategory, List<Question>> getApprovedClassifiedDescriptiveQuestions(
		RequestGetQuestionByCategoryAndLevelDto requestGetQuestionByCategoryAndLevelDto
	);

}

