package com.server.computer_science.question.major_question.user.service;

import com.server.computer_science.question.major_question.user.dto.response.ResponseMajorQuestionClassCountDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MajorQuestionCountService {
    public List<ResponseMajorQuestionClassCountDto> getNormalQuestionCountByClass();
}
