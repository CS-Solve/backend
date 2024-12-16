package com.comssa.api.question.service.rest.common.implement;


import com.comssa.persistence.question.license.domain.LicenseMultipleChoiceQuestion;
import com.comssa.persistence.question.license.domain.LicenseQuestionChoice;
import com.comssa.persistence.question.license.repository.LicenseQuestionChoiceRepository;
import com.comssa.persistence.question.major.admin.dto.RequestMakeMajorMultipleChoiceQuestionDto;
import com.comssa.persistence.question.major.domain.common.MajorMultipleChoiceQuestion;
import com.comssa.persistence.question.major.domain.common.MajorQuestionChoice;
import com.comssa.persistence.question.major.repository.MajorQuestionChoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionChoiceService {
	private final MajorQuestionChoiceRepository majorQuestionChoiceRepository;
	private final LicenseQuestionChoiceRepository licenseQuestionChoiceRepository;

	public void saveWith(
		RequestMakeMajorMultipleChoiceQuestionDto dto,
		LicenseMultipleChoiceQuestion licenseMultipleChoiceQuestion) {
		licenseQuestionChoiceRepository.saveAll(dto.getQuestionChoices()
			.stream()
			.map(qc -> LicenseQuestionChoice.from(qc, licenseMultipleChoiceQuestion))
			.collect(Collectors.toList()));
	}

	public void saveWith(
		RequestMakeMajorMultipleChoiceQuestionDto dto,
		MajorMultipleChoiceQuestion majorMultipleChoiceQuestion) {
		majorQuestionChoiceRepository.saveAll(dto.getQuestionChoices()
			.stream()
			.map(qc -> MajorQuestionChoice.fromMajorQuestion(qc, majorMultipleChoiceQuestion))
			.collect(Collectors.toList()));
	}

}
