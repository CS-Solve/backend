package com.server.computer_science.question.normal_question.admin.service;

import com.server.computer_science.question.normal_question.admin.dto.RequestMakeNormalQuestionDto;
import com.server.computer_science.question.common.dto.ResponseNormalQuestionDto;
import com.server.computer_science.question.normal_question.common.exception.DuplicateQuestionException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminNormalQuestionMakeService {

    public List<ResponseNormalQuestionDto> makeNormalQuestions(List<RequestMakeNormalQuestionDto> requestNormalQuestionDto);
    public ResponseNormalQuestionDto makeNormalQuestion(RequestMakeNormalQuestionDto requestNormalQuestionDto) throws DuplicateQuestionException;
}
