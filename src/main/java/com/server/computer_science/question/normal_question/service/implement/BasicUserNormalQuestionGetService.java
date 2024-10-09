package com.server.computer_science.question.normal_question.service.implement;


import com.server.computer_science.question.common.QuestionCategory;
import com.server.computer_science.question.common.QuestionLevel;
import com.server.computer_science.question.normal_question.domain.NormalQuestion;
import com.server.computer_science.question.normal_question.dto.request.RequestGetNormalQuestionsDto;
import com.server.computer_science.question.normal_question.dto.response.ResponseClassifiedNormalQuestionDto;
import com.server.computer_science.question.normal_question.dto.response.ResponseNormalQuestionClassCountDto;
import com.server.computer_science.question.normal_question.service.NormalQuestionClassifyService;
import com.server.computer_science.question.normal_question.service.UserNormalQuestionGetService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BasicUserNormalQuestionGetService implements UserNormalQuestionGetService {
    private final NormalQuestionDBService normalQuestionDBService;
    private final NormalQuestionClassifyService normalQuestionClassifyService;

    /**
     * 분류별로 나누어 문제를 반환
     */
    @Override
    public List<ResponseClassifiedNormalQuestionDto> getClassifiedNormalQuestions(RequestGetNormalQuestionsDto requestGetNormalQuestionsDto) {
        Map<QuestionCategory, List<NormalQuestion>> categoryMap = makeCategoryMap(requestGetNormalQuestionsDto);
        return categoryMap.entrySet().stream()
                .map(entry-> ResponseClassifiedNormalQuestionDto.forUser(entry.getKey(),entry.getValue()))
                .collect(Collectors.toList());
    }

    private Map<QuestionCategory, List<NormalQuestion>> makeCategoryMap(RequestGetNormalQuestionsDto requestGetNormalQuestionsDto) {
        List<NormalQuestion> normalQuestions = normalQuestionDBService.getFetchChoicesByCategoriesAndLevels(requestGetNormalQuestionsDto.getQuestionCategories(),requestGetNormalQuestionsDto.getQuestionLevels());
        return normalQuestionClassifyService.classifyNormalQuestionByClass(normalQuestions);
    }

}
