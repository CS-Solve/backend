package com.server.computer_science.question.major_question.admin.service;

import java.util.List;
import java.util.Map;

import com.server.computer_science.question.common.domain.QuestionCategory;
import com.server.computer_science.question.major_question.common.domain.MajorMultipleChoiceQuestion;

public interface AdminMajorQuestionClassifiedGetService {
	public Map<QuestionCategory, List<MajorMultipleChoiceQuestion>> getClassifiedAllMajorQuestions();

}
