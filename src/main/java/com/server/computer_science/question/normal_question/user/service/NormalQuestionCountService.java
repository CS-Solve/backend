package com.server.computer_science.question.normal_question.user.service;

import com.server.computer_science.question.normal_question.user.dto.response.ResponseNormalQuestionClassCountDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NormalQuestionCountService {
    public List<ResponseNormalQuestionClassCountDto> getNormalQuestionCountByClass();
}
