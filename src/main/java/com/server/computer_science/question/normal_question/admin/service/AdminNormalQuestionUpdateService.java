package com.server.computer_science.question.normal_question.admin.service;


import com.server.computer_science.question.normal_question.admin.dto.RequestChangeDescriptionDto;
import com.server.computer_science.question.normal_question.admin.dto.RequestChangeQuestionDto;
import com.server.computer_science.question.common.dto.response.ResponseNormalQuestionDto;
import org.springframework.stereotype.Service;

@Service
public interface AdminNormalQuestionUpdateService {
    public ResponseNormalQuestionDto toggleApproveNormalQuestion(Long questionId);
    public ResponseNormalQuestionDto toggleCanBeShortAnswered(Long questionId);
    public ResponseNormalQuestionDto changeDescription(Long questionId, RequestChangeDescriptionDto requestChangeDescriptionDto);
    public ResponseNormalQuestionDto changeContent(Long questionId, RequestChangeQuestionDto requestChangeQuestionDto);
    public void deleteNormalQuestion(Long questionId);

}
