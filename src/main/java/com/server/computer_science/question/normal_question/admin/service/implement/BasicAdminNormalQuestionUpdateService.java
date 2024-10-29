package com.server.computer_science.question.normal_question.admin.service.implement;

import com.server.computer_science.question.normal_question.admin.dto.RequestChangeDescriptionDto;
import com.server.computer_science.question.normal_question.admin.dto.RequestChangeQuestionDto;
import com.server.computer_science.question.normal_question.admin.service.AdminNormalQuestionUpdateService;
import com.server.computer_science.question.normal_question.common.domain.NormalQuestion;
import com.server.computer_science.question.normal_question.common.service.implement.NormalQuestionDBService;
import com.server.computer_science.question.normal_question.user.dto.response.ResponseNormalQuestionDto;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BasicAdminNormalQuestionUpdateService implements AdminNormalQuestionUpdateService {
    private final NormalQuestionDBService normalQuestionDBService;
    @Override
    public ResponseNormalQuestionDto toggleApproveNormalQuestion(Long questionId) {
        NormalQuestion normalQuestion = normalQuestionDBService.findByIdFetchChoices(questionId);
        normalQuestion.toggleApproved();
        return ResponseNormalQuestionDto.forAdmin(normalQuestion);
    }

    @Override
    public ResponseNormalQuestionDto toggleCanBeShortAnswered(Long questionId) {
        NormalQuestion normalQuestion= normalQuestionDBService.findByIdFetchChoices(questionId);
        normalQuestion.toggleCanBeShortAnswered();
        return ResponseNormalQuestionDto.forAdmin(normalQuestion);
    }

    @Override
    public ResponseNormalQuestionDto changeDescription(Long questionId, RequestChangeDescriptionDto requestChangeDescriptionDto){
        NormalQuestion normalQuestion = normalQuestionDBService.findByIdFetchChoices(questionId);
        normalQuestion.changeDescription(requestChangeDescriptionDto.getDescription());
        return ResponseNormalQuestionDto.forAdmin(normalQuestion);
    }

    @Override
    public ResponseNormalQuestionDto changeContent(Long questionId, RequestChangeQuestionDto requestChangeQuestionDto) {
        NormalQuestion normalQuestion = normalQuestionDBService.findByIdFetchChoices(questionId);
        normalQuestion.changeContent(requestChangeQuestionDto.getContent());
        return ResponseNormalQuestionDto.forAdmin(normalQuestion);
    }

    @Override
    public void deleteNormalQuestion(Long questionId) {
        NormalQuestion normalQuestion = normalQuestionDBService.findByIdFetchChoices(questionId);
        normalQuestionDBService.deleteNormalQuestion(normalQuestion);
    }
}
