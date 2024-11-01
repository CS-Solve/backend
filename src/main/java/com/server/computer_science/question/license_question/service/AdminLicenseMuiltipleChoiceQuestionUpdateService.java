package com.server.computer_science.question.license_question.service;

import com.server.computer_science.question.common.service.QuestionUpdateService;
import com.server.computer_science.question.license_question.domain.LicenseMultipleChoiceQuestion;
import com.server.computer_science.question.license_question.repository.LicenseMultipleChoiceQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminLicenseMuiltipleChoiceQuestionUpdateService implements QuestionUpdateService<LicenseMultipleChoiceQuestion> {
    private final LicenseMultipleChoiceQuestionRepository licenseMultipleChoiceQuestionRepository;

    @Override
    public void deleteQuestion(Long questionId) {
        licenseMultipleChoiceQuestionRepository.deleteById(questionId);
    }

    @Override
    public LicenseMultipleChoiceQuestion findById(Long questionId) {
        return licenseMultipleChoiceQuestionRepository.findByIdFetchChoices(questionId).orElse(null);
    }
}
