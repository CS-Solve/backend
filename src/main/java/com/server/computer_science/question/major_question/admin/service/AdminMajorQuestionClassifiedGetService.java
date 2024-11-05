package com.server.computer_science.question.major_question.admin.service;

import com.server.computer_science.question.common.domain.QuestionCategory;
import com.server.computer_science.question.common.dto.response.ResponseClassifiedMultipleQuestionDto;
import com.server.computer_science.question.major_question.common.domain.MajorMultipleChoiceQuestion;

import java.util.List;
import java.util.Map;

public interface AdminMajorQuestionClassifiedGetService {
    public Map<QuestionCategory,List<MajorMultipleChoiceQuestion>> getClassifiedAllMajorQuestions();

}
