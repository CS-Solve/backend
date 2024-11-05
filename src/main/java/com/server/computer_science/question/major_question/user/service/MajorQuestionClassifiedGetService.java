package com.server.computer_science.question.major_question.user.service;


import com.server.computer_science.question.common.domain.QuestionCategory;
import com.server.computer_science.question.major_question.common.domain.MajorMultipleChoiceQuestion;
import com.server.computer_science.question.major_question.user.dto.request.RequestGetQuestionByCategoryAndLevelDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface MajorQuestionClassifiedGetService {
    /**
     * 분류별로 나누어 문제를 반환
     */
    public Map<QuestionCategory,List<MajorMultipleChoiceQuestion>> getApprovedClassifiedMajorMultipleChoiceQuestions(RequestGetQuestionByCategoryAndLevelDto requestGetQuestionByCategoryAndLevelDto);
    public Map<QuestionCategory,List<MajorMultipleChoiceQuestion>> getApprovedClassifiedShortAnsweredMajorQuestions(RequestGetQuestionByCategoryAndLevelDto requestGetQuestionByCategoryAndLevelDto);

}
