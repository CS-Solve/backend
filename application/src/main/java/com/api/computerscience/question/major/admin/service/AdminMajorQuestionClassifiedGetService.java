package com.api.computerscience.question.major.admin.service;


import com.persistence.computerscience.question.common.domain.QuestionCategory;
import com.persistence.computerscience.question.major.domain.common.MajorMultipleChoiceQuestion;

import java.util.List;
import java.util.Map;

public interface AdminMajorQuestionClassifiedGetService {
	Map<QuestionCategory, List<MajorMultipleChoiceQuestion>> getClassifiedAllMajorQuestions();

}
