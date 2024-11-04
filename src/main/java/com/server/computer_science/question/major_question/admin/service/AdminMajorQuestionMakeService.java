package com.server.computer_science.question.major_question.admin.service;

import com.server.computer_science.question.major_question.admin.dto.RequestMakeMajorMultipleChoiceQuestionDto;
import com.server.computer_science.question.common.dto.response.ResponseQuestionDto;
import com.server.computer_science.question.major_question.common.exception.DuplicateQuestionException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminMajorQuestionMakeService {

    public List<ResponseQuestionDto> makeMultipleChoiceQuestions(List<RequestMakeMajorMultipleChoiceQuestionDto> requestNormalQuestionDto);
    public ResponseQuestionDto makeMultipleChoiceQuestion(RequestMakeMajorMultipleChoiceQuestionDto requestNormalQuestionDto) throws DuplicateQuestionException;
}
