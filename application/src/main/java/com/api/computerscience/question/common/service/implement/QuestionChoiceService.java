package com.api.computerscience.question.common.service.implement;


import com.persistence.computerscience.question.license.domain.LicenseMultipleChoiceQuestion;
import com.persistence.computerscience.question.license.domain.LicenseQuestionChoice;
import com.persistence.computerscience.question.license.repository.LicenseQuestionChoiceRepository;
import com.persistence.computerscience.question.major.domain.admin.dto.RequestMakeMultipleChoiceQuestionDto;
import com.persistence.computerscience.question.major.domain.common.MajorMultipleChoiceQuestion;
import com.persistence.computerscience.question.major.domain.common.MajorQuestionChoice;
import com.persistence.computerscience.question.major.repository.MajorQuestionChoiceRepository;
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

	public void saveWith(RequestMakeMultipleChoiceQuestionDto dto,
						 LicenseMultipleChoiceQuestion licenseMultipleChoiceQuestion) {
		licenseQuestionChoiceRepository.saveAll(dto.getQuestionChoices()
			.stream()
			.map(qc -> LicenseQuestionChoice.from(qc, licenseMultipleChoiceQuestion))
			.collect(Collectors.toList()));
	}

	public void saveWith(RequestMakeMultipleChoiceQuestionDto dto,
						 MajorMultipleChoiceQuestion majorMultipleChoiceQuestion) {
		majorQuestionChoiceRepository.saveAll(dto.getQuestionChoices()
			.stream()
			.map(qc -> MajorQuestionChoice.fromMajorQuestion(qc, majorMultipleChoiceQuestion))
			.collect(Collectors.toList()));
	}

}
