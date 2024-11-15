package com.server.computerscience.question.major.admin.service;

import com.server.computerscience.question.common.domain.QuestionCategory;
import com.server.computerscience.question.major.common.domain.MajorMultipleChoiceQuestion;

import java.util.List;
import java.util.Map;

public interface AdminMajorQuestionClassifiedGetService {
    Map<QuestionCategory, List<MajorMultipleChoiceQuestion>> getClassifiedAllMajorQuestions();

}
