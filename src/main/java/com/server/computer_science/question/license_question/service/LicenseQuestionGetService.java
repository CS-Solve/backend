package com.server.computer_science.question.license_question.service;

import com.server.computer_science.question.common.domain.QuestionCategory;
import com.server.computer_science.question.license_question.domain.LicenseMultipleChoiceQuestion;
import com.server.computer_science.question.license_question.repository.LicenseMultipleChoiceQuestionRepository;
import com.server.computer_science.question.major_question.common.service.QuestionClassifyByCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LicenseQuestionGetService {
    private final LicenseMultipleChoiceQuestionRepository licenseMultipleChoiceQuestionRepository;
    private final QuestionClassifyByCategoryService questionClassifyByCategoryService;

    public Map<QuestionCategory,List<LicenseMultipleChoiceQuestion>> getClassifiedLicenseMultipleChoiceQuestion(Long sessionId) {
        List<LicenseMultipleChoiceQuestion> licenseMultipleChoiceQuestions = licenseMultipleChoiceQuestionRepository.findAllByLicenseSessionIdFetchChoices(sessionId);
        for(LicenseMultipleChoiceQuestion licenseMultipleChoiceQuestion : licenseMultipleChoiceQuestions){
            Collections.shuffle(licenseMultipleChoiceQuestion.getQuestionChoices());
        }
        return questionClassifyByCategoryService.classifyQuestionByCategoryOrdered(licenseMultipleChoiceQuestions);
    }
}
