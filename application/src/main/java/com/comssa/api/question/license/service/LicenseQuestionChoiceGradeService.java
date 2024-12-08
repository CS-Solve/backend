package com.comssa.api.question.license.service;

import com.comssa.api.question.common.service.QuestionChoiceGradeService;
import com.comssa.persistence.question.license.domain.LicenseQuestionChoice;
import com.comssa.persistence.question.license.repository.LicenseQuestionChoiceRepository;
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
