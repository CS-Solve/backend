package com.server.computer_science.question.normal_question.user.service.implement;


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

    /**
     * 분야, 난이도 파라미터로 문제를 조회하는 경우 - 객관식.
     * 문제 선택지들을 섞어준다.
     */

    @Override
    public List<ResponseClassifiedNormalQuestionDto> getClassifiedNormalQuestions(RequestGetNormalQuestionsDto requestGetNormalQuestionsDto) {
        List<NormalQuestion> normalQuestions = normalQuestionDBService.findAllFetchChoicesByCategoriesAndLevelsApproved(
                requestGetNormalQuestionsDto.getQuestionCategories(),
                requestGetNormalQuestionsDto.getQuestionLevels());
        for(NormalQuestion normalQuestion : normalQuestions){
            Collections.shuffle(normalQuestion.getQuestionChoices());
        }
        return normalQuestionClassifyService.classifyNormalQuestionByClass(normalQuestions)
                .entrySet().stream()
                .map(entry-> ResponseClassifiedNormalQuestionDto.normalQuestionForUser(entry.getKey(),entry.getValue()))
                .collect(Collectors.toList());
    }

    /**
     분야, 난이도 파라미터로 문제를 조회하는 경우 - 주관식
     */
    @Override
    public List<ResponseClassifiedNormalQuestionDto> getClassifiedShortAnsweredNormalQuestions(RequestGetNormalQuestionsDto requestGetNormalQuestionsDto) {
        List<NormalQuestion> normalQuestions = normalQuestionDBService.findAllFetchChoicesByCategoriesAndLevelsApprovedAndShortAnswered(
                requestGetNormalQuestionsDto.getQuestionCategories(),
                requestGetNormalQuestionsDto.getQuestionLevels());
        return normalQuestionClassifyService.classifyNormalQuestionByClass(normalQuestions)
                .entrySet().stream()
                .map(entry-> ResponseClassifiedNormalQuestionDto.normalQuestionForUser(entry.getKey(),entry.getValue()))
                .collect(Collectors.toList());
    }

    /**
     * 현재 유저가 파라미터 없이 문제를 조회하는 경우는 존재하지 않는다.
     */
    @Override
    public List<ResponseClassifiedNormalQuestionDto> getClassifiedAllNormalQuestions() {
        List<NormalQuestion> normalQuestions = normalQuestionDBService.findAllFetchChoices();
        return normalQuestionClassifyService.classifyNormalQuestionByClass(normalQuestions)
                .entrySet().stream()
                .map(entry-> ResponseClassifiedNormalQuestionDto.normalQuestionForUser(entry.getKey(),entry.getValue()))
                .collect(Collectors.toList());
    }



}
