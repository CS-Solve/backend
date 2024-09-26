package com.server.computer_science.question.normal_question.service;


import com.server.computer_science.question.common.QuestionCategory;
import com.server.computer_science.question.common.QuestionLevel;
import com.server.computer_science.question.normal_question.domain.NormalQuestion;
import com.server.computer_science.question.normal_question.dto.request.RequestGetNormalQuestionsDto;
import com.server.computer_science.question.normal_question.dto.response.ResponseNormalQuestionDto;
import com.server.computer_science.question.normal_question.repository.NormalQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BasicNormalQuestionGetService implements NormalQuestionGetService {
    private final NormalQuestionRepository normalQuestionRepository;

    @Override
    public List<ResponseNormalQuestionDto> getNormalQuestions(RequestGetNormalQuestionsDto requestGetNormalQuestionsDto) {
        return normalQuestionRepository.findAll().stream()
                .filter(normalQuestion -> isQuestionFit(requestGetNormalQuestionsDto,normalQuestion))
                .map(ResponseNormalQuestionDto::of)
                .collect(Collectors.toList());
    }
    private boolean isQuestionFit(RequestGetNormalQuestionsDto requestGetNormalQuestionsDto,NormalQuestion normalQuestion) {
        for (QuestionCategory questionCategory : requestGetNormalQuestionsDto.getQuestionCategories()) {
            for (QuestionLevel questionLevel : requestGetNormalQuestionsDto.getQuestionLevels()) {
                if (normalQuestion.isFit(questionCategory, questionLevel)) {
                    return true;
                }
            }
        }
        return false;
    }
}
