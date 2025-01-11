package com.comssa.api.question.service.rest.major.implement;


import com.comssa.api.question.service.rest.common.QuestionClassifyByCategoryService;
import com.comssa.api.question.service.rest.major.AdminMajorQuestionClassifiedGetService;
import com.comssa.persistence.question.domain.common.Question;
import com.comssa.persistence.question.domain.common.QuestionCategory;
import com.comssa.persistence.question.domain.major.MajorDescriptiveQuestion;
import com.comssa.persistence.question.domain.major.MajorMultipleChoiceQuestion;
import com.comssa.persistence.question.repository.querydsl.impl.MajorDescriptiveDslRepository;
import com.comssa.persistence.question.repository.querydsl.impl.MajorMultipleChoiceQuestionDslRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class BasicAdminMajorQuestionClassifiedGetService implements AdminMajorQuestionClassifiedGetService {
    private final QuestionClassifyByCategoryService questionClassifyByCategoryService;
    private final MajorDescriptiveDslRepository majorDescriptiveQuestionChooseRepository;
    private final MajorMultipleChoiceQuestionDslRepository majorMultipleChoiceQuestionDslRepository;

    /**
     * 관리자가 조회시 Approve됐는지 기준으로 정렬되며(false인 것부터),
     * 이후엔 객관식 -> 주관식으로 정렬된다.
     *
     * @return
     */
    @Override
    public Map<QuestionCategory, List<Question>> getClassifiedAllMajorMultipleChoiceQuestions() {
        List<MajorMultipleChoiceQuestion> majorMultipleChoiceQuestions = majorMultipleChoiceQuestionDslRepository
                .findAllOrderByIfApprovedAsc();
        return questionClassifyByCategoryService.classifyQuestionByCategoryOrdered(majorMultipleChoiceQuestions);
    }

    @Override
    public Map<QuestionCategory, List<Question>> getClassifiedAllMajorDescriptiveQuestions() {
        List<MajorDescriptiveQuestion> majorDescriptiveQuestions =
                majorDescriptiveQuestionChooseRepository.findAllOrderByIfApprovedAsc();
        return questionClassifyByCategoryService.classifyQuestionByCategoryOrdered(majorDescriptiveQuestions);
    }
}
