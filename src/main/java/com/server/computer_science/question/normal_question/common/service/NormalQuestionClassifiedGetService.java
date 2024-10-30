package com.server.computer_science.question.normal_question.common.service;


import com.server.computer_science.question.normal_question.user.dto.request.RequestGetNormalQuestionsDto;
import com.server.computer_science.question.normal_question.user.dto.response.ResponseClassifiedNormalQuestionDto;
import com.server.computer_science.question.normal_question.user.dto.response.ResponseNormalQuestionClassCountDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NormalQuestionClassifiedGetService {
    /**
     * 분류별로 나누어 문제를 반환
     */
    public List<ResponseClassifiedNormalQuestionDto> getClassifiedNormalQuestions(RequestGetNormalQuestionsDto requestGetNormalQuestionsDto);
    public List<ResponseClassifiedNormalQuestionDto> getClassifiedShortAnsweredNormalQuestions(RequestGetNormalQuestionsDto requestGetNormalQuestionsDto);
    public List<ResponseClassifiedNormalQuestionDto> getClassifiedAllNormalQuestions();
}
