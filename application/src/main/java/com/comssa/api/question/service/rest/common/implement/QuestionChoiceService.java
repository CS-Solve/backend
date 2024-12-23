package com.comssa.api.question.service.rest.common.implement;


import com.comssa.persistence.question.domain.license.LicenseMultipleChoiceQuestion;
import com.comssa.persistence.question.domain.license.LicenseQuestionChoice;
import com.comssa.persistence.question.domain.major.MajorMultipleChoiceQuestion;
import com.comssa.persistence.question.domain.major.MajorQuestionChoice;
import com.comssa.persistence.question.dto.common.request.RequestMakeMultipleChoiceQuestionDto;
import com.comssa.persistence.question.repository.jpa.QuestionChoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionChoiceService {
	private final QuestionChoiceRepository questionChoiceRepository;

	public void saveWith(
		RequestMakeMultipleChoiceQuestionDto dto,
		LicenseMultipleChoiceQuestion licenseMultipleChoiceQuestion) {
		questionChoiceRepository.saveAll(dto.getQuestionChoices()
			.stream()
			.map(qc -> LicenseQuestionChoice.from(qc, licenseMultipleChoiceQuestion))
			.collect(Collectors.toList()));
	}

	public void saveWith(
		RequestMakeMultipleChoiceQuestionDto dto,
		MajorMultipleChoiceQuestion majorMultipleChoiceQuestion) {
		questionChoiceRepository.saveAll(dto.getQuestionChoices()
			.stream()
			.map(qc -> MajorQuestionChoice.fromMajorQuestion(qc, majorMultipleChoiceQuestion))
			.collect(Collectors.toList()));
	}

}
