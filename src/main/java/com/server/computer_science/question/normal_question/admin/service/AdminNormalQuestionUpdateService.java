package com.server.computer_science.question.normal_question.admin.service;


import com.server.computer_science.question.normal_question.user.dto.response.ResponseNormalQuestionDto;
import org.springframework.stereotype.Service;

@Service
public interface AdminNormalQuestionUpdateService {
    public ResponseNormalQuestionDto toggleApproveNormalQuestion(Long questionId);
    public ResponseNormalQuestionDto toggleCanBeShortAnswered(Long questionId);
}
