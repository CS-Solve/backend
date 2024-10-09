package com.server.computer_science.question.normal_question.user.service.implement;


import com.server.computer_science.question.common.QuestionCategory;
import com.server.computer_science.question.normal_question.common.domain.NormalQuestion;
import com.server.computer_science.question.normal_question.common.service.implement.NormalQuestionDBService;
import com.server.computer_science.question.normal_question.user.dto.request.RequestGetNormalQuestionsDto;
import com.server.computer_science.question.normal_question.user.dto.response.ResponseClassifiedNormalQuestionDto;
import com.server.computer_science.question.normal_question.common.service.NormalQuestionClassifyService;
import com.server.computer_science.question.normal_question.common.service.NormalQuestionClassifiedGetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service("basicNormalQuestionClassifiedGetService")
@RequiredArgsConstructor
public class BasicNormalQuestionClassifiedGetService implements NormalQuestionClassifiedGetService {
    private final NormalQuestionDBService normalQuestionDBService;
    private final NormalQuestionClassifyService normalQuestionClassifyService;


    @Override
    public List<ResponseClassifiedNormalQuestionDto> getClassifiedNormalQuestions(RequestGetNormalQuestionsDto requestGetNormalQuestionsDto) {
        Map<QuestionCategory, List<NormalQuestion>> categoryMap = makeCategoryMap(requestGetNormalQuestionsDto);
        return categoryMap.entrySet().stream()
                .map(entry-> ResponseClassifiedNormalQuestionDto.forUser(entry.getKey(),entry.getValue()))
                .collect(Collectors.toList());
    }

    private Map<QuestionCategory, List<NormalQuestion>> makeCategoryMap(RequestGetNormalQuestionsDto requestGetNormalQuestionsDto) {
        List<NormalQuestion> normalQuestions = normalQuestionDBService.getAllFetchChoicesByCategoriesAndLevelsApproved(requestGetNormalQuestionsDto.getQuestionCategories(),requestGetNormalQuestionsDto.getQuestionLevels());
        return normalQuestionClassifyService.classifyNormalQuestionByClass(normalQuestions);
    }
    @Override
    public List<ResponseClassifiedNormalQuestionDto> getClassifiedAllNormalQuestions() {
        List<NormalQuestion> normalQuestions = normalQuestionDBService.findAllFetchChoices();
        return normalQuestionClassifyService.classifyNormalQuestionByClass(normalQuestions)
                .entrySet().stream()
                .map(entry-> ResponseClassifiedNormalQuestionDto.forUser(entry.getKey(),entry.getValue()))
                .collect(Collectors.toList());
    }
}
