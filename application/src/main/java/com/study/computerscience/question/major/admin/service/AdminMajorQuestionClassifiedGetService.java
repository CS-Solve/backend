package com.study.computerscience.question.major.admin.service;

import com.study.computerscience.question.common.domain.QuestionCategory;
import com.study.computerscience.question.major.common.domain.MajorMultipleChoiceQuestion;

import java.util.List;
import java.util.Map;

public interface AdminMajorQuestionClassifiedGetService {
	Map<QuestionCategory, List<MajorMultipleChoiceQuestion>> getClassifiedAllMajorQuestions();

}
