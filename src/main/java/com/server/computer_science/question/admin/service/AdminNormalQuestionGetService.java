package com.server.computer_science.question.admin.service;


import com.server.computer_science.question.normal_question.dto.response.ResponseNormalQuestionDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminNormalQuestionGetService {
    public List<ResponseNormalQuestionDto> getAllNormalQuestionsClassifiedForAdmin();
}
