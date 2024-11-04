package com.server.computer_science.question.major_question.admin.service.implement;


import com.server.computer_science.question.major_question.admin.service.AdminMajorQuestionClassifiedGetService;
import com.server.computer_science.question.major_question.common.domain.MajorMultipleChoiceQuestion;
import com.server.computer_science.question.major_question.common.service.QuestionClassifyByCategoryService;
import com.server.computer_science.question.major_question.user.dto.request.RequestGetQuestionByCategoryAndLevelDto;
import com.server.computer_science.question.common.dto.response.ResponseClassifiedMultipleQuestionDto;
import com.server.computer_science.question.major_question.user.service.MajorQuestionClassifiedGetService;
import com.server.computer_science.question.major_question.common.service.implement.MajorMultipleChoiceQuestionDBService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BasicAdminMajorQuestionClassifiedGetService implements AdminMajorQuestionClassifiedGetService {
    private final MajorMultipleChoiceQuestionDBService majorMultipleChoiceQuestionDBService;
    private final QuestionClassifyByCategoryService questionClassifyByCategoryService;

    /**
     * 관리자가 조회시 Approve됐는지 기준으로 정렬되며(false인 것부터),
     * 이후엔 객관식 -> 주관식으로 정렬된다.
     * @return
     */
    @Override
    public List<ResponseClassifiedMultipleQuestionDto> getClassifiedAllMajorQuestions() {
        List<MajorMultipleChoiceQuestion> majorMultipleChoiceQuestions = majorMultipleChoiceQuestionDBService.findAllFetchChoicesSortedByApproveAndShortAnswered();
        return questionClassifyByCategoryService.classifyQuestionByCategoryOrdered(majorMultipleChoiceQuestions)
                .entrySet().stream()
                .map(entry-> ResponseClassifiedMultipleQuestionDto.forAdmin(entry.getKey(),entry.getValue()))
                .collect(Collectors.toList());
    }


}
