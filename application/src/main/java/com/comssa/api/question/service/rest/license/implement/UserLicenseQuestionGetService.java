package com.comssa.api.question.service.rest.license.implement;


import com.comssa.api.question.service.rest.common.QuestionClassifyByCategoryService;
import com.comssa.api.question.service.rest.license.LicenseQuestionGetService;
import com.comssa.persistence.question.domain.common.Question;
import com.comssa.persistence.question.domain.common.QuestionCategory;
import com.comssa.persistence.question.domain.license.LicenseMultipleChoiceQuestion;
import com.comssa.persistence.question.repository.LicenseMultipleChoiceQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserLicenseQuestionGetService implements LicenseQuestionGetService {
    private final LicenseMultipleChoiceQuestionRepository licenseMultipleChoiceQuestionRepository;
    private final QuestionClassifyByCategoryService questionClassifyByCategoryService;

    public Map<QuestionCategory, List<Question>> getClassifiedLicenseMultipleChoiceQuestion(
            Long sessionId) {
        List<LicenseMultipleChoiceQuestion> licenseMultipleChoiceQuestions = licenseMultipleChoiceQuestionRepository
                .findAllByLicenseSessionIdFetchChoicesAndIfApproved(
                        sessionId);
        for (LicenseMultipleChoiceQuestion licenseMultipleChoiceQuestion : licenseMultipleChoiceQuestions) {
            Collections.shuffle(licenseMultipleChoiceQuestion.getQuestionChoices());
        }
        return questionClassifyByCategoryService.classifyQuestionByCategoryOrdered(licenseMultipleChoiceQuestions);
    }
}
