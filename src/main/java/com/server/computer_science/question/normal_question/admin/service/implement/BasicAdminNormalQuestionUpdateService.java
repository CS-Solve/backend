package com.server.computer_science.question.normal_question.admin.service.implement;

import com.server.computer_science.question.normal_question.admin.service.AdminNormalQuestionUpdateService;
import com.server.computer_science.question.normal_question.common.domain.NormalQuestion;
import com.server.computer_science.question.normal_question.common.service.implement.NormalQuestionDBService;
import com.server.computer_science.question.normal_question.user.dto.response.ResponseNormalQuestionDto;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BasicAdminNormalQuestionUpdateService implements AdminNormalQuestionUpdateService {
    private final NormalQuestionDBService normalQuestionDBService;
    @Override
    public ResponseNormalQuestionDto toggleApproveNormalQuestion(Long questionId) {
        NormalQuestion normalQuestion = normalQuestionDBService.findById(questionId);
        normalQuestion.toggleApproved();
        return ResponseNormalQuestionDto.forAdmin(normalQuestion);
    }

    @Override
    public ResponseNormalQuestionDto toggleBeMultipleNormalQuestion(Long questionId) {
        NormalQuestion normalQuestion= normalQuestionDBService.findById(questionId);
        normalQuestion.toggleCanBeShortAnswered();
        return ResponseNormalQuestionDto.forAdmin(normalQuestion);
    }
}
