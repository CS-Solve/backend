package com.server.computer_science.question.license_question.service;

import com.server.computer_science.question.license_question.domain.LicenseMultipleChoiceQuestion;
import com.server.computer_science.question.license_question.repository.LicenseMultipleChoiceQuestionRepository;
import com.server.computer_science.question.major_question.common.service.QuestionClassifyByCategoryService;
import com.server.computer_science.question.major_question.user.dto.response.ResponseClassifiedMultipleQuestionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LicenseQuestionGetService {
    private final LicenseMultipleChoiceQuestionRepository licenseMultipleChoiceQuestionRepository;
    private final QuestionClassifyByCategoryService questionClassifyByCategoryService;

    public List<ResponseClassifiedMultipleQuestionDto> getClassifiedLicenseNormalQuestion(Long sessionId) {
        List<LicenseMultipleChoiceQuestion> licenseMultipleChoiceQuestions = licenseMultipleChoiceQuestionRepository.findAllByLicenseSessionIdFetchChoices(sessionId);
        for(LicenseMultipleChoiceQuestion licenseMultipleChoiceQuestion : licenseMultipleChoiceQuestions){
            Collections.shuffle(licenseMultipleChoiceQuestion.getNormalQuestionChoices());
        }
        return questionClassifyByCategoryService.classifyQuestionByCategoryOrdered(licenseMultipleChoiceQuestions)
                .entrySet().stream()
                .map(entry-> ResponseClassifiedMultipleQuestionDto.LicenseQuestionForUser(entry.getKey(),entry.getValue()))
                .collect(Collectors.toList());
    }
}
