package com.server.computer_science.question.normal_question.service;

import com.server.computer_science.question.normal_question.dto.request.RequestNormalQuestionDto;
import com.server.computer_science.question.normal_question.dto.response.ResponseNormalQuestionDto;
import org.springframework.stereotype.Service;

@Service
public interface NormalProblemMakeService {

    public ResponseNormalQuestionDto makeNormalQuestion(RequestNormalQuestionDto requestNormalQuestionDto);
}
