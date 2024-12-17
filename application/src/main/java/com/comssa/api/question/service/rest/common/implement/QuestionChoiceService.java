package com.comssa.api.question.service.rest.common.implement;


import com.comssa.persistence.question.domain.license.LicenseMultipleChoiceQuestion;
import com.comssa.persistence.question.domain.license.LicenseQuestionChoice;
import com.comssa.persistence.question.domain.major.MajorMultipleChoiceQuestion;
import com.comssa.persistence.question.domain.major.MajorQuestionChoice;
import com.comssa.persistence.question.dto.major.request.RequestMakeMajorMultipleChoiceQuestionDto;
import com.comssa.persistence.question.repository.LicenseQuestionChoiceRepository;
import com.comssa.persistence.question.repository.MajorQuestionChoiceRepository;
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
