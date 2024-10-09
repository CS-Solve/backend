package com.server.computer_science.question.admin.service.implement;


import com.server.computer_science.question.admin.service.AdminNormalQuestionGetService;
import com.server.computer_science.question.normal_question.domain.NormalQuestion;
import com.server.computer_science.question.normal_question.dto.response.ResponseClassifiedNormalQuestionDto;
import com.server.computer_science.question.normal_question.service.NormalQuestionClassifyService;
import com.server.computer_science.question.normal_question.service.implement.NormalQuestionDBService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BasicAdminNormalQuestionGetService implements AdminNormalQuestionGetService {
    private final NormalQuestionDBService normalQuestionDBService;
    private final NormalQuestionClassifyService normalQuestionClassifyService;

    @Override
    public List<ResponseClassifiedNormalQuestionDto> getAllNormalQuestionsClassifiedForAdmin() {
        List<NormalQuestion> normalQuestions = normalQuestionDBService.findAllFetchChoices();
        return normalQuestionClassifyService.classifyNormalQuestionByClass(normalQuestions)
                .entrySet().stream()
                .map(entry-> ResponseClassifiedNormalQuestionDto.forAdmin(entry.getKey(),entry.getValue()))
                .collect(Collectors.toList());
    }
}
