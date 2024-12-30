package com.comssa.api.question.service.rest.license.implement;


import com.comssa.api.question.service.rest.common.QuestionClassifyByCategoryService;
import com.comssa.api.question.service.rest.license.LicenseQuestionGetService;
import com.comssa.persistence.question.domain.common.Question;
import com.comssa.persistence.question.domain.common.QuestionCategory;
import com.comssa.persistence.question.domain.license.LicenseMultipleChoiceQuestion;
import com.comssa.persistence.question.repository.querydsl.impl.LicenseMultipleChoiceQuestionDslRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminLicenseQuestionGetService implements LicenseQuestionGetService {

    private final LicenseMultipleChoiceQuestionDslRepository licenseMultipleChoiceQuestionDslRepository;
    private final QuestionClassifyByCategoryService questionClassifyByCategoryService;

    /**
     * 관리자 조회시 허용 여부와 관련 없이 모든 문제를 가져온다.
     * 문제지도 섞지 않는다.
     * 허용 여부 순대로 섞는다
     */
    @Override
    public Map<QuestionCategory, List<Question>> getClassifiedLicenseMultipleChoiceQuestion(
            Long sessionId) {
        List<LicenseMultipleChoiceQuestion> licenseMultipleChoiceQuestions = licenseMultipleChoiceQuestionDslRepository
                .findAllWhereLicenseSessionId(sessionId);
        return questionClassifyByCategoryService.classifyQuestionByCategoryOrdered(licenseMultipleChoiceQuestions);
    }
}
