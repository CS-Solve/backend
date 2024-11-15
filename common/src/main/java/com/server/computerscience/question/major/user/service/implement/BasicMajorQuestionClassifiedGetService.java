package com.server.computerscience.question.major.user.service.implement;

import com.server.computerscience.question.common.domain.QuestionCategory;
import com.server.computerscience.question.major.common.domain.MajorMultipleChoiceQuestion;
import com.server.computerscience.question.major.common.service.QuestionClassifyByCategoryService;
import com.server.computerscience.question.major.common.service.implement.MajorMultipleChoiceQuestionDbService;
import com.server.computerscience.question.major.user.dto.request.RequestGetQuestionByCategoryAndLevelDto;
import com.server.computerscience.question.major.user.service.MajorQuestionClassifiedGetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BasicMajorQuestionClassifiedGetService implements MajorQuestionClassifiedGetService {
    private final MajorMultipleChoiceQuestionDbService majorMultipleChoiceQuestionDbService;
    private final QuestionClassifyByCategoryService questionClassifyByCategoryService;

    /**
     * 분야, 난이도 파라미터로 문제를 조회하는 경우 - 객관식.
     * 문제 선택지들을 섞어준다.
     */

    @Override
    public Map<QuestionCategory, List<MajorMultipleChoiceQuestion>> getApprovedClassifiedMajorMultipleChoiceQuestions(
            RequestGetQuestionByCategoryAndLevelDto requestGetQuestionByCategoryAndLevelDto) {
        List<MajorMultipleChoiceQuestion> majorMultipleChoiceQuestions = majorMultipleChoiceQuestionDbService
                .findAllFetchChoicesByCategoriesAndLevelsApproved(
                        requestGetQuestionByCategoryAndLevelDto.getQuestionCategories(),
                        requestGetQuestionByCategoryAndLevelDto.getQuestionLevels());
        for (MajorMultipleChoiceQuestion majorMultipleChoiceQuestion : majorMultipleChoiceQuestions) {
            Collections.shuffle(majorMultipleChoiceQuestion.getQuestionChoices());
        }
        return questionClassifyByCategoryService.classifyQuestionByCategoryOrdered(majorMultipleChoiceQuestions);
    }

    /**
     * 분야, 난이도 파라미터로 문제를 조회하는 경우 - 주관식
     */
    @Override
    public Map<QuestionCategory, List<MajorMultipleChoiceQuestion>> getApprovedClassifiedShortAnsweredMajorQuestions(
            RequestGetQuestionByCategoryAndLevelDto requestGetQuestionByCategoryAndLevelDto) {
        List<MajorMultipleChoiceQuestion> majorMultipleChoiceQuestions = majorMultipleChoiceQuestionDbService
                .findAllFetchChoicesByCategoriesAndLevelsApprovedAndShortAnswered(
                        requestGetQuestionByCategoryAndLevelDto.getQuestionCategories(),
                        requestGetQuestionByCategoryAndLevelDto.getQuestionLevels());
        return questionClassifyByCategoryService.classifyQuestionByCategoryOrdered(majorMultipleChoiceQuestions);
    }
}
