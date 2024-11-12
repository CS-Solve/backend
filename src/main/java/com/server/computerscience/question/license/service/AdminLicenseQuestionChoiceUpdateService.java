package com.server.computerscience.question.license.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.server.computerscience.question.common.service.QuestionChoiceUpdateService;
import com.server.computerscience.question.license.domain.LicenseQuestionChoice;
import com.server.computerscience.question.license.repository.LicenseQuestionChoiceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminLicenseQuestionChoiceUpdateService
	implements QuestionChoiceUpdateService<LicenseQuestionChoice> {
	private final LicenseQuestionChoiceRepository licenseQuestionChoiceRepository;

	@Override
	public void deleteQuestionChoice(Long questionChoiceId) {
		licenseQuestionChoiceRepository.deleteById(questionChoiceId);
	}

	@Override
	public LicenseQuestionChoice findById(Long questionChoiceId) {
		return licenseQuestionChoiceRepository.findById(questionChoiceId).orElse(null);
	}
}
