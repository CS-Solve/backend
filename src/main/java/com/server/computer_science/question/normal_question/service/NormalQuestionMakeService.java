package com.server.computer_science.question.normal_question.service;

import com.server.computer_science.question.normal_question.dto.request.RequestMakeNormalQuestionDto;
import com.server.computer_science.question.normal_question.dto.response.ResponseNormalQuestionDto;
import com.server.computer_science.question.normal_question.exception.DuplicateQuestionException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NormalQuestionMakeService {

    public List<ResponseNormalQuestionDto> makeNormalQuestions(List<RequestMakeNormalQuestionDto> requestNormalQuestionDto);
    public ResponseNormalQuestionDto makeNormalQuestion(RequestMakeNormalQuestionDto requestNormalQuestionDto) throws DuplicateQuestionException;
}
