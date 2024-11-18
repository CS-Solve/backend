package com.comssa.api.question.license.service;


import com.comssa.api.question.common.service.FileUploadService;
import com.comssa.api.question.common.service.implement.QuestionChoiceService;
import com.comssa.persistence.question.common.dto.response.ResponseMultipleChoiceQuestionDto;
import com.comssa.persistence.question.license.domain.LicenseCategory;
import com.comssa.persistence.question.license.domain.LicenseMultipleChoiceQuestion;
import com.comssa.persistence.question.license.domain.LicenseSession;
import com.comssa.persistence.question.license.dto.request.RequestMakeLicenseMultipleChoiceQuestionDto;
import com.comssa.persistence.question.license.repository.LicenseMultipleChoiceQuestionRepository;
import com.comssa.persistence.question.major.admin.dto.RequestMakeMultipleChoiceQuestionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminLicenseQuestionMakeService {
	private final LicenseSessionService licenseSessionService;
	private final LicenseMultipleChoiceQuestionRepository licenseMultipleChoiceQuestionRepository;
	private final QuestionChoiceService questionChoiceService;
	private final FileUploadService fileUploadService;

	public List<ResponseMultipleChoiceQuestionDto> makeLicenseNormalQuestion(
		RequestMakeLicenseMultipleChoiceQuestionDto requestMakeLicenseMultipleChoiceQuestionDto) {
		LicenseSession licenseSession = licenseSessionService.getLicenseSession(
			requestMakeLicenseMultipleChoiceQuestionDto.getLicenseSession(),
			requestMakeLicenseMultipleChoiceQuestionDto.getLicenseCategory());
		List<RequestMakeMultipleChoiceQuestionDto> questions = requestMakeLicenseMultipleChoiceQuestionDto
			.getQuestions();
		return questions
			.stream()
			.map(q -> saveNormalLicenseQuestion(q, licenseSession,
				requestMakeLicenseMultipleChoiceQuestionDto.getLicenseCategory()))
			.collect(Collectors.toList());
	}

	private ResponseMultipleChoiceQuestionDto saveNormalLicenseQuestion(
		RequestMakeMultipleChoiceQuestionDto requestMakeMultipleChoiceQuestionDto, LicenseSession licenseSession,
		LicenseCategory licenseCategory) {
		LicenseMultipleChoiceQuestion licenseMultipleChoiceQuestion = LicenseMultipleChoiceQuestion.makeWithDto(
			requestMakeMultipleChoiceQuestionDto, licenseSession, licenseCategory);
		licenseMultipleChoiceQuestionRepository.save(licenseMultipleChoiceQuestion);
		questionChoiceService.saveWith(requestMakeMultipleChoiceQuestionDto, licenseMultipleChoiceQuestion);
		return ResponseMultipleChoiceQuestionDto.forAdmin(licenseMultipleChoiceQuestion);
	}

	public String updateLicenseQuestionWithImage(Long licenseQuestionId, MultipartFile file) {
		try {
			LicenseMultipleChoiceQuestion licenseMultipleChoiceQuestion = licenseMultipleChoiceQuestionRepository
				.findById(
					licenseQuestionId).orElse(null);
			String imageUrl = fileUploadService.uploadImage(file, "license");
			assert licenseMultipleChoiceQuestion != null;
			licenseMultipleChoiceQuestion.updateImage(imageUrl);
			return imageUrl;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
