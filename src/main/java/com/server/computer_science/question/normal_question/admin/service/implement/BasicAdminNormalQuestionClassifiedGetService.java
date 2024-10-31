package com.server.computer_science.question.normal_question.admin.service.implement;


import com.server.computer_science.question.normal_question.common.domain.NormalQuestion;
import com.server.computer_science.question.normal_question.common.service.QuestionClassifyByCategoryService;
import com.server.computer_science.question.normal_question.user.dto.request.RequestGetNormalQuestionsDto;
import com.server.computer_science.question.normal_question.user.dto.response.ResponseClassifiedNormalQuestionDto;
import com.server.computer_science.question.normal_question.common.service.NormalQuestionClassifiedGetService;
import com.server.computer_science.question.normal_question.common.service.implement.NormalQuestionDBService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("basicAdminNormalQuestionClassifiedGetService")
@RequiredArgsConstructor
public class BasicAdminNormalQuestionClassifiedGetService implements NormalQuestionClassifiedGetService {
    private final NormalQuestionDBService normalQuestionDBService;
    private final QuestionClassifyByCategoryService questionClassifyByCategoryService;

    @Override
    public List<ResponseClassifiedNormalQuestionDto> getClassifiedNormalQuestions(RequestGetNormalQuestionsDto requestGetNormalQuestionsDto) {
        List<NormalQuestion> normalQuestions = normalQuestionDBService.getFetchChoicesByCategoriesAndLevels(requestGetNormalQuestionsDto.getQuestionCategories(),requestGetNormalQuestionsDto.getQuestionLevels());
        return questionClassifyByCategoryService.classifyQuestionByCategoryOrdered(normalQuestions)
                .entrySet().stream()
                .map(entry-> ResponseClassifiedNormalQuestionDto.forAdmin(entry.getKey(),entry.getValue()))
                .collect(Collectors.toList());
    }

    /**
     * 관리자가 조회시 Approve됐는지 기준으로 정렬되며(false인 것부터),
     * 이후엔 객관식 -> 주관식으로 정렬된다.
     * @return
     */
    @Override
    public List<ResponseClassifiedNormalQuestionDto> getClassifiedAllNormalQuestions() {
        List<NormalQuestion> normalQuestions = normalQuestionDBService.findAllFetchChoicesSortedByApproveAndShortAnswered();
        return questionClassifyByCategoryService.classifyQuestionByCategoryOrdered(normalQuestions)
                .entrySet().stream()
                .map(entry-> ResponseClassifiedNormalQuestionDto.forAdmin(entry.getKey(),entry.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ResponseClassifiedNormalQuestionDto> getClassifiedShortAnsweredNormalQuestions(RequestGetNormalQuestionsDto requestGetNormalQuestionsDto) {
        List<NormalQuestion> normalQuestions = normalQuestionDBService.findAllFetchChoicesShortAnswered();
        return questionClassifyByCategoryService.classifyQuestionByCategoryOrdered(normalQuestions)
                .entrySet().stream()
                .map(entry-> ResponseClassifiedNormalQuestionDto.forAdmin(entry.getKey(),entry.getValue()))
                .collect(Collectors.toList());
    }
}
