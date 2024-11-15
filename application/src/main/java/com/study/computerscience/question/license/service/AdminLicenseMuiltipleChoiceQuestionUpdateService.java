package com.study.computerscience.question.license.service;

import com.study.computerscience.question.common.service.QuestionUpdateService;
import com.study.computerscience.question.license.domain.LicenseMultipleChoiceQuestion;
import com.study.computerscience.question.license.repository.LicenseMultipleChoiceQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminLicenseMuiltipleChoiceQuestionUpdateService
	implements QuestionUpdateService<LicenseMultipleChoiceQuestion> {
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
