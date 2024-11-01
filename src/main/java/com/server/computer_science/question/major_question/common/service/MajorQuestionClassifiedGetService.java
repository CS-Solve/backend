package com.server.computer_science.question.major_question.common.service;


import com.server.computer_science.question.major_question.user.dto.request.RequestGetQuestionByCategoryAndLevelDto;
import com.server.computer_science.question.major_question.user.dto.response.ResponseClassifiedMultipleQuestionDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MajorQuestionClassifiedGetService {
    /**
     * 분류별로 나누어 문제를 반환
     */
    public List<ResponseClassifiedMultipleQuestionDto> getClassifiedNormalQuestions(RequestGetQuestionByCategoryAndLevelDto requestGetQuestionByCategoryAndLevelDto);
    public List<ResponseClassifiedMultipleQuestionDto> getClassifiedShortAnsweredMajorQuestions(RequestGetQuestionByCategoryAndLevelDto requestGetQuestionByCategoryAndLevelDto);
    public List<ResponseClassifiedMultipleQuestionDto> getClassifiedAllMajorQuestions();
}
