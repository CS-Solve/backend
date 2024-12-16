package com.comssa.api.question.license.service;


import com.comssa.api.question.common.service.implement.QuestionChoiceService;
import com.comssa.persistence.question.common.dto.response.ResponseMultipleChoiceQuestionDto;
import com.comssa.persistence.question.license.domain.LicenseCategory;
import com.comssa.persistence.question.license.domain.LicenseMultipleChoiceQuestion;
import com.comssa.persistence.question.license.domain.LicenseSession;
import com.comssa.persistence.question.license.dto.request.RequestMakeLicenseMultipleChoiceQuestionDto;
import com.comssa.persistence.question.license.repository.LicenseMultipleChoiceQuestionRepository;
import com.comssa.persistence.question.major.admin.dto.RequestMakeMajorMultipleChoiceQuestionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminLicenseQuestionMakeService {
	private final LicenseSessionService licenseSessionService;
	private final LicenseMultipleChoiceQuestionRepository licenseMultipleChoiceQuestionRepository;
	private final QuestionChoiceService questionChoiceService;

	public List<ResponseMultipleChoiceQuestionDto> makeLicenseNormalQuestion(
		RequestMakeLicenseMultipleChoiceQuestionDto requestMakeLicenseMultipleChoiceQuestionDto) {
		LicenseSession licenseSession = licenseSessionService.getLicenseSession(
			requestMakeLicenseMultipleChoiceQuestionDto.getLicenseSession(),
			requestMakeLicenseMultipleChoiceQuestionDto.getLicenseCategory());
		List<RequestMakeMajorMultipleChoiceQuestionDto> questions = requestMakeLicenseMultipleChoiceQuestionDto
			.getQuestions();
		return questions
			.stream()
			.map(q -> saveNormalLicenseQuestion(q, licenseSession,
				requestMakeLicenseMultipleChoiceQuestionDto.getLicenseCategory()))
			.collect(Collectors.toList());
	}

	private ResponseMultipleChoiceQuestionDto saveNormalLicenseQuestion(
		RequestMakeMajorMultipleChoiceQuestionDto requestMakeMajorMultipleChoiceQuestionDto,
		LicenseSession licenseSession,
		LicenseCategory licenseCategory) {
		LicenseMultipleChoiceQuestion licenseMultipleChoiceQuestion = LicenseMultipleChoiceQuestion.makeWithDto(
			requestMakeMajorMultipleChoiceQuestionDto, licenseSession, licenseCategory);
		licenseMultipleChoiceQuestionRepository.save(licenseMultipleChoiceQuestion);
		questionChoiceService.saveWith(requestMakeMajorMultipleChoiceQuestionDto, licenseMultipleChoiceQuestion);
		return ResponseMultipleChoiceQuestionDto.forLicense(licenseMultipleChoiceQuestion);
	}


}
