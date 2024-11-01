package com.server.computer_science.question.major_question.user.service.implement;


import com.server.computer_science.question.major_question.common.domain.MajorMultipleChoiceQuestion;
import com.server.computer_science.question.major_question.common.service.implement.MajorMultipleChoiceQuestionDBService;
import com.server.computer_science.question.major_question.user.dto.request.RequestGetQuestionByCategoryAndLevelDto;
import com.server.computer_science.question.common.dto.response.ResponseClassifiedMultipleQuestionDto;
import com.server.computer_science.question.major_question.common.service.QuestionClassifyByCategoryService;
import com.server.computer_science.question.major_question.common.service.MajorQuestionClassifiedGetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service("basicNormalQuestionClassifiedGetService")
@RequiredArgsConstructor
public class BasicMajorQuestionClassifiedGetService implements MajorQuestionClassifiedGetService {
    private final MajorMultipleChoiceQuestionDBService majorMultipleChoiceQuestionDBService;
    private final QuestionClassifyByCategoryService questionClassifyByCategoryService;

    /**
     * 분야, 난이도 파라미터로 문제를 조회하는 경우 - 객관식.
     * 문제 선택지들을 섞어준다.
     */

    @Override
    public List<ResponseClassifiedMultipleQuestionDto> getClassifiedMajorMultipleChoiceQuestions(RequestGetQuestionByCategoryAndLevelDto requestGetQuestionByCategoryAndLevelDto) {
        List<MajorMultipleChoiceQuestion> majorMultipleChoiceQuestions = majorMultipleChoiceQuestionDBService.findAllFetchChoicesByCategoriesAndLevelsApproved(
                requestGetQuestionByCategoryAndLevelDto.getQuestionCategories(),
                requestGetQuestionByCategoryAndLevelDto.getQuestionLevels());
        for(MajorMultipleChoiceQuestion majorMultipleChoiceQuestion : majorMultipleChoiceQuestions){
            Collections.shuffle(majorMultipleChoiceQuestion.getQuestionChoices());
        }
        return questionClassifyByCategoryService.classifyQuestionByCategoryOrdered(majorMultipleChoiceQuestions)
                .entrySet().stream()
                .map(entry-> ResponseClassifiedMultipleQuestionDto.forUser(entry.getKey(),entry.getValue()))
                .collect(Collectors.toList());
    }

    /**
     분야, 난이도 파라미터로 문제를 조회하는 경우 - 주관식
     */
    @Override
    public List<ResponseClassifiedMultipleQuestionDto> getClassifiedShortAnsweredMajorQuestions(RequestGetQuestionByCategoryAndLevelDto requestGetQuestionByCategoryAndLevelDto) {
        List<MajorMultipleChoiceQuestion> majorMultipleChoiceQuestions = majorMultipleChoiceQuestionDBService.findAllFetchChoicesByCategoriesAndLevelsApprovedAndShortAnswered(
                requestGetQuestionByCategoryAndLevelDto.getQuestionCategories(),
                requestGetQuestionByCategoryAndLevelDto.getQuestionLevels());
        return questionClassifyByCategoryService.classifyQuestionByCategoryOrdered(majorMultipleChoiceQuestions)
                .entrySet().stream()
                .map(entry-> ResponseClassifiedMultipleQuestionDto.forUser(entry.getKey(),entry.getValue()))
                .collect(Collectors.toList());
    }

    /**
     * 현재 유저가 파라미터 없이 문제를 조회하는 경우는 존재하지 않는다.
     */
    @Override
    public List<ResponseClassifiedMultipleQuestionDto> getClassifiedAllMajorQuestions() {
        List<MajorMultipleChoiceQuestion> majorMultipleChoiceQuestions = majorMultipleChoiceQuestionDBService.findAllFetchChoices();
        return questionClassifyByCategoryService.classifyQuestionByCategoryOrdered(majorMultipleChoiceQuestions)
                .entrySet().stream()
                .map(entry-> ResponseClassifiedMultipleQuestionDto.forUser(entry.getKey(),entry.getValue()))
                .collect(Collectors.toList());
    }



}
