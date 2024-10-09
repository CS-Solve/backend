package com.server.computer_science.question.normal_question.admin.service.implement;


import com.server.computer_science.question.normal_question.common.domain.NormalQuestion;
import com.server.computer_science.question.normal_question.user.dto.request.RequestGetNormalQuestionsDto;
import com.server.computer_science.question.normal_question.user.dto.response.ResponseClassifiedNormalQuestionDto;
import com.server.computer_science.question.normal_question.common.service.NormalQuestionClassifiedGetService;
import com.server.computer_science.question.normal_question.common.service.NormalQuestionClassifyService;
import com.server.computer_science.question.normal_question.common.service.implement.NormalQuestionDBService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("BasicAdminNormalQuestionClassifiedGetService")
@RequiredArgsConstructor
public class BasicAdminNormalQuestionClassifiedGetService implements NormalQuestionClassifiedGetService {
    private final NormalQuestionDBService normalQuestionDBService;
    private final NormalQuestionClassifyService normalQuestionClassifyService;

    @Override
    public List<ResponseClassifiedNormalQuestionDto> getClassifiedNormalQuestions(RequestGetNormalQuestionsDto requestGetNormalQuestionsDto) {
        List<NormalQuestion> normalQuestions = normalQuestionDBService.getFetchChoicesByCategoriesAndLevels(requestGetNormalQuestionsDto.getQuestionCategories(),requestGetNormalQuestionsDto.getQuestionLevels());
        return normalQuestionClassifyService.classifyNormalQuestionByClass(normalQuestions)
                .entrySet().stream()
                .map(entry-> ResponseClassifiedNormalQuestionDto.forAdmin(entry.getKey(),entry.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ResponseClassifiedNormalQuestionDto> getClassifiedAllNormalQuestions() {
        List<NormalQuestion> normalQuestions = normalQuestionDBService.findAllFetchChoices();
        return normalQuestionClassifyService.classifyNormalQuestionByClass(normalQuestions)
                .entrySet().stream()
                .map(entry-> ResponseClassifiedNormalQuestionDto.forAdmin(entry.getKey(),entry.getValue()))
                .collect(Collectors.toList());
    }
}
