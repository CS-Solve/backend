package com.server.computer_science.question.major_question.admin.service;

import com.server.computer_science.question.common.dto.response.ResponseClassifiedMultipleQuestionDto;

import java.util.List;

public interface AdminMajorQuestionClassifiedGetService {
    public List<ResponseClassifiedMultipleQuestionDto> getClassifiedAllMajorQuestions();
}
