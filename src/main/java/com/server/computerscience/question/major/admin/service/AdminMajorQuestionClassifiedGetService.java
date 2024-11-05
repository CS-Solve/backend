package com.server.computerscience.question.major.admin.service;

import java.util.List;
import java.util.Map;

import com.server.computerscience.question.common.domain.QuestionCategory;
import com.server.computerscience.question.major.common.domain.MajorMultipleChoiceQuestion;

public interface AdminMajorQuestionClassifiedGetService {
	public Map<QuestionCategory, List<MajorMultipleChoiceQuestion>> getClassifiedAllMajorQuestions();

}
