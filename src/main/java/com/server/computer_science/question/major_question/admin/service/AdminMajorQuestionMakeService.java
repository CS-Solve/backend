package com.server.computer_science.question.major_question.admin.service;

import com.server.computer_science.question.major_question.admin.dto.RequestMakeMultipleChoiceQuestionDto;
import com.server.computer_science.question.common.dto.response.ResponseQuestionDto;
import com.server.computer_science.question.major_question.common.domain.MajorMultipleChoiceQuestion;
import com.server.computer_science.question.major_question.common.exception.DuplicateQuestionException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminMajorQuestionMakeService {

    public List<MajorMultipleChoiceQuestion> makeMultipleChoiceQuestions(List<RequestMakeMultipleChoiceQuestionDto> requestNormalQuestionDto);
    public MajorMultipleChoiceQuestion makeMultipleChoiceQuestion(RequestMakeMultipleChoiceQuestionDto requestNormalQuestionDto) throws DuplicateQuestionException;
}
