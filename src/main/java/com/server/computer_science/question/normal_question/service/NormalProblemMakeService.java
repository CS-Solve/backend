package com.server.computer_science.question.normal_question.service;

import com.server.computer_science.question.normal_question.dto.request.RequestNormalQuestionDto;
import com.server.computer_science.question.normal_question.dto.response.ResponseNormalQuestionDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NormalProblemMakeService {

    public List<ResponseNormalQuestionDto> makeNormalQuestion(List<RequestNormalQuestionDto> requestNormalQuestionDto);
}
