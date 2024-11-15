package com.server.computerscience.question.major.admin.service.implement;

import com.server.computerscience.question.common.service.QuestionUpdateService;
import com.server.computerscience.question.major.common.domain.MajorMultipleChoiceQuestion;
import com.server.computerscience.question.major.common.service.implement.MajorMultipleChoiceQuestionDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminMajorMultipleChoiceQuestionUpdateService
        implements QuestionUpdateService<MajorMultipleChoiceQuestion> {
    private final MajorMultipleChoiceQuestionDbService majorMultipleChoiceQuestionDBService;

    public MajorMultipleChoiceQuestion toggleCanBeShortAnswered(Long questionId) {
        MajorMultipleChoiceQuestion majorMultipleChoiceQuestion = majorMultipleChoiceQuestionDBService
                .findById(
                        questionId);
        majorMultipleChoiceQuestion.toggleCanBeShortAnswered();
        return majorMultipleChoiceQuestion;
    }

    @Override
    public void deleteQuestion(Long questionId) {
        majorMultipleChoiceQuestionDBService.deleteById(questionId);
    }

    @Override
    public MajorMultipleChoiceQuestion findById(Long questionId) {
        return majorMultipleChoiceQuestionDBService.findByIdFetchChoices(questionId);
    }

}