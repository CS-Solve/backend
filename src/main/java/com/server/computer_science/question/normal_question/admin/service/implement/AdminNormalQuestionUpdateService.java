package com.server.computer_science.question.normal_question.admin.service.implement;

import com.server.computer_science.question.common.service.QuestionUpdateService;
import com.server.computer_science.question.normal_question.admin.dto.RequestChangeDescriptionDto;
import com.server.computer_science.question.normal_question.admin.dto.RequestChangeContentDto;
import com.server.computer_science.question.normal_question.common.domain.NormalQuestion;
import com.server.computer_science.question.normal_question.common.repository.NormalQuestionRepository;
import com.server.computer_science.question.normal_question.common.service.implement.NormalQuestionDBService;
import com.server.computer_science.question.common.dto.response.ResponseNormalQuestionDto;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminNormalQuestionUpdateService implements QuestionUpdateService<NormalQuestion> {
    private final NormalQuestionDBService normalQuestionDBService;

    public NormalQuestion toggleApproveNormalQuestion(Long questionId) {
        NormalQuestion normalQuestion = normalQuestionDBService.findByIdFetchChoices(questionId);
        normalQuestion.toggleApproved();
        return normalQuestion;
    }

    public NormalQuestion toggleCanBeShortAnswered(Long questionId) {
        NormalQuestion normalQuestion= normalQuestionDBService.findByIdFetchChoices(questionId);
        normalQuestion.toggleCanBeShortAnswered();
        return normalQuestion;
    }

    @Override
    public void deleteQuestion(Long questionId) {
        normalQuestionDBService.deleteById(questionId);
    }

    @Override
    public NormalQuestion findById(Long questionId) {
        return normalQuestionDBService.findByIdFetchChoices(questionId);
    }

}
