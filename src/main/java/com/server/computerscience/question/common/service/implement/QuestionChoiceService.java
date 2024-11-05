package com.server.computerscience.question.common.service.implement;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.server.computerscience.question.license.domain.LicenseMultipleChoiceQuestion;
import com.server.computerscience.question.license.domain.LicenseQuestionChoice;
import com.server.computerscience.question.license.repository.LicenseQuestionChoiceRepository;
import com.server.computerscience.question.major.admin.dto.RequestMakeMultipleChoiceQuestionDto;
import com.server.computerscience.question.major.common.domain.MajorMultipleChoiceQuestion;
import com.server.computerscience.question.major.common.domain.MajorQuestionChoice;
import com.server.computerscience.question.major.common.repository.MajorQuestionChoiceRepository;

import lombok.RequiredArgsConstructor;

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
