package com.server.computer_science.question.normal_question.service;


import com.server.computer_science.question.normal_question.dto.request.RequestGetNormalQuestionsDto;
import com.server.computer_science.question.normal_question.dto.response.ResponseClassifiedNormalQuestionDto;
import com.server.computer_science.question.normal_question.dto.response.ResponseNormalQuestionClassCountDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserNormalQuestionGetService {
    public List<ResponseNormalQuestionClassCountDto> getNormalQuestionCountByClass();
    public List<ResponseClassifiedNormalQuestionDto> getClassifiedNormalQuestions(RequestGetNormalQuestionsDto requestGetNormalQuestionsDto);
}
