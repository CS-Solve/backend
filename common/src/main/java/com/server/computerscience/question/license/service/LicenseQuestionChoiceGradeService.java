package com.server.computerscience.question.license.service;

import com.server.computerscience.question.common.service.QuestionChoiceGradeService;
import com.server.computerscience.question.license.domain.LicenseQuestionChoice;
import com.server.computerscience.question.license.repository.LicenseQuestionChoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LicenseQuestionChoiceGradeService implements QuestionChoiceGradeService<LicenseQuestionChoice> {
	private final LicenseQuestionChoiceRepository licenseQuestionChoiceRepository;

	@Override
	public LicenseQuestionChoice findById(Long id) {
		return licenseQuestionChoiceRepository.findById(id).orElse(null);
	}
}