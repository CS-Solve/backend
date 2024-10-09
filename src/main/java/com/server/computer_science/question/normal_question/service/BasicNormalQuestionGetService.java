package com.server.computer_science.question.normal_question.service;


import com.server.computer_science.question.common.QuestionCategory;
import com.server.computer_science.question.common.QuestionLevel;
import com.server.computer_science.question.normal_question.domain.NormalQuestion;
import com.server.computer_science.question.normal_question.dto.request.RequestGetNormalQuestionsDto;
import com.server.computer_science.question.normal_question.dto.response.ResponseClassifiedNormalQuestionDto;
import com.server.computer_science.question.normal_question.dto.response.ResponseNormalQuestionClassCountDto;
import com.server.computer_science.question.normal_question.dto.response.ResponseNormalQuestionDto;
import com.server.computer_science.question.normal_question.repository.NormalQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BasicNormalQuestionGetService implements NormalQuestionGetService {
    private final NormalQuestionRepository normalQuestionRepository;

    /**
     *
     * 분야 상관없이 문제 반환
     */
    @Override
    public List<ResponseNormalQuestionDto> getNormalQuestions(RequestGetNormalQuestionsDto requestGetNormalQuestionsDto) {
        return normalQuestionRepository
                .findNormalQuestionsFetchChoices(requestGetNormalQuestionsDto.getQuestionCategories(),requestGetNormalQuestionsDto.getQuestionLevels())
                .stream()
                .map(ResponseNormalQuestionDto::forUser)
                .collect(Collectors.toList());
    }

    /**
     * 분류별로 나누어 문제를 반환
     */
    @Override
    public List<ResponseClassifiedNormalQuestionDto> getClassifiedNormalQuestions(RequestGetNormalQuestionsDto requestGetNormalQuestionsDto) {
        Map<QuestionCategory, List<NormalQuestion>> categoryMap = makeCategoryMap(requestGetNormalQuestionsDto);
        return categoryMap.entrySet().stream()
                .map(entry-> ResponseClassifiedNormalQuestionDto.of(entry.getKey(),entry.getValue()))
                .collect(Collectors.toList());
    }

    private Map<QuestionCategory, List<NormalQuestion>> makeCategoryMap(RequestGetNormalQuestionsDto requestGetNormalQuestionsDto) {
        System.out.println(requestGetNormalQuestionsDto.getQuestionCategories());
        return normalQuestionRepository
                .findNormalQuestionsFetchChoices(requestGetNormalQuestionsDto.getQuestionCategories(), requestGetNormalQuestionsDto.getQuestionLevels())
                .stream()
                .collect(Collectors.groupingBy(NormalQuestion::getQuestionCategory));
//        // 요청된 모든 카테고리에 대해 문제가 없어도 빈 리스트 보장
//        requestGetNormalQuestionsDto.getQuestionCategories().forEach(category ->
//                categoryMap.putIfAbsent(category, new ArrayList<>())
//        );
    }

    /**
     *
     * Unique한 분야-난이도 별로 분류하고, 분류별 문제의 수를 판별한다.
     * 분야-난이도별 분류는 Map을 사용한다.
     */
    @Override
    public List<ResponseNormalQuestionClassCountDto> getNormalQuestionCountByClass() {
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
                    return ResponseNormalQuestionClassCountDto.of(questionCategory,questionLevel,count);
                })
                .collect(Collectors.toList());
    }

    private Map<Pair<QuestionCategory,QuestionLevel>,Integer> initateCountMap() {
        Map<Pair<QuestionCategory,QuestionLevel>,Integer> counts = new LinkedHashMap<>();
        for(QuestionCategory questionCategory : QuestionCategory.values()){
            for(QuestionLevel questionLevel : QuestionLevel.values()){
                counts.put(Pair.of(questionCategory,questionLevel),0);
            }
        }
        return counts;
    }

}
