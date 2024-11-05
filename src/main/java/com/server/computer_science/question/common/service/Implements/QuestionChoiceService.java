package com.server.computer_science.question.common.service.Implements;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.server.computer_science.question.license_question.domain.LicenseMultipleChoiceQuestion;
import com.server.computer_science.question.license_question.domain.LicenseQuestionChoice;
import com.server.computer_science.question.license_question.repository.LicenseQuestionChoiceRepository;
import com.server.computer_science.question.major_question.admin.dto.RequestMakeMultipleChoiceQuestionDto;
import com.server.computer_science.question.major_question.common.domain.MajorMultipleChoiceQuestion;
import com.server.computer_science.question.major_question.common.domain.MajorQuestionChoice;
import com.server.computer_science.question.major_question.common.repository.MajorQuestionChoiceRepository;

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
