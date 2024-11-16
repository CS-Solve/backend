package com.api.computerscience.question.license.service;

import com.api.computerscience.question.common.service.QuestionChoiceUpdateService;
import com.persistence.computerscience.question.license.domain.LicenseQuestionChoice;
import com.persistence.computerscience.question.license.repository.LicenseQuestionChoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
