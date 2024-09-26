package com.server.computer_science.question.normal_question.service;


import com.server.computer_science.question.common.QuestionCategory;
import com.server.computer_science.question.common.QuestionLevel;
import com.server.computer_science.question.normal_question.domain.NormalQuestion;
import com.server.computer_science.question.normal_question.dto.request.RequestGetNormalQuestionsDto;
import com.server.computer_science.question.normal_question.dto.response.ResponseNormalQuestionClassDto;
import com.server.computer_science.question.normal_question.dto.response.ResponseNormalQuestionDto;
import com.server.computer_science.question.normal_question.repository.NormalQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BasicNormalQuestionGetService implements NormalQuestionGetService {
    private final NormalQuestionRepository normalQuestionRepository;

    @Override
    public List<ResponseNormalQuestionDto> getNormalQuestions(RequestGetNormalQuestionsDto requestGetNormalQuestionsDto) {
        return normalQuestionRepository.findAll().stream()
                .filter(normalQuestion -> isQuestionFit(requestGetNormalQuestionsDto,normalQuestion))
                .map(ResponseNormalQuestionDto::of)
                .collect(Collectors.toList());
    }

    /**
     *
     * Unique한 분야-난이도 별로 분류하고, 분류별 문제의 수를 판별한다.
     * 분야-난이도별 분류는 Map을 사용한다.
     */
    @Override
    public List<ResponseNormalQuestionClassDto> getNormalQuestionByClass() {
        List<NormalQuestion> normalQuestions = normalQuestionRepository.findAll();
        Map<Pair<QuestionCategory,QuestionLevel>,Integer> counts = initateCountMap();
        for (NormalQuestion normalQuestion : normalQuestions) {
            for(Pair<QuestionCategory,QuestionLevel> pair : counts.keySet()){
                if(normalQuestion.isFit(pair.getFirst(),pair.getSecond())){
                    counts.put(pair,counts.get(pair)+1);
                }
            }
        }
        return counts.entrySet().stream()
                .map(pairIntegerEntry -> {
                    QuestionCategory questionCategory = pairIntegerEntry.getKey().getFirst();
                    QuestionLevel questionLevel = pairIntegerEntry.getKey().getSecond();
                    int count = pairIntegerEntry.getValue();
                    return ResponseNormalQuestionClassDto.of(questionCategory,questionLevel,count);
                })
                .collect(Collectors.toList());
    }

    private Map<Pair<QuestionCategory,QuestionLevel>,Integer> initateCountMap() {
        Map<Pair<QuestionCategory,QuestionLevel>,Integer> counts = new HashMap<>();
        for(QuestionCategory questionCategory : QuestionCategory.values()){
            for(QuestionLevel questionLevel : QuestionLevel.values()){
                counts.put(Pair.of(questionCategory,questionLevel),0);
            }
        }
        return counts;
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
