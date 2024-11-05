package com.server.computerscience.question.license.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.server.computerscience.question.common.service.QuestionUpdateService;
import com.server.computerscience.question.license.domain.LicenseMultipleChoiceQuestion;
import com.server.computerscience.question.license.repository.LicenseMultipleChoiceQuestionRepository;

import lombok.RequiredArgsConstructor;

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
