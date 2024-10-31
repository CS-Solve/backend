package com.server.computer_science.question.license_question.service;

import com.server.computer_science.question.common.domain.Question;
import com.server.computer_science.question.common.service.QuestionUpdateService;
import com.server.computer_science.question.license_question.domain.LicenseNormalQuestion;
import com.server.computer_science.question.license_question.repository.LicenseNormalQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LicenseQuestionUpdateService implements QuestionUpdateService {
    private final LicenseNormalQuestionRepository licenseNormalQuestionRepository;

    @Override
    public void deleteQuestion(Long questionId) {
        licenseNormalQuestionRepository.deleteById(questionId);
    }

    @Override
    public LicenseNormalQuestion findById(Long questionId) {
        return licenseNormalQuestionRepository.findById(questionId).orElse(null);
    }

}
