package com.server.computerscience.question.license.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.server.computerscience.question.common.dto.response.ResponseQuestionDto;
import com.server.computerscience.question.common.service.FileUploadService;
import com.server.computerscience.question.common.service.implement.QuestionChoiceService;
import com.server.computerscience.question.license.domain.LicenseCategory;
import com.server.computerscience.question.license.domain.LicenseMultipleChoiceQuestion;
import com.server.computerscience.question.license.domain.LicenseSession;
import com.server.computerscience.question.license.dto.request.RequestMakeLicenseMultipleChoiceQuestionDto;
import com.server.computerscience.question.license.repository.LicenseMultipleChoiceQuestionRepository;
import com.server.computerscience.question.major.admin.dto.RequestMakeMultipleChoiceQuestionDto;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminLicenseQuestionMakeService {
	private final LicenseSessionService licenseSessionService;
	private final LicenseMultipleChoiceQuestionRepository licenseMultipleChoiceQuestionRepository;
	private final QuestionChoiceService questionChoiceService;
	private final FileUploadService fileUploadService;

	public List<ResponseQuestionDto> makeLicenseNormalQuestion(
		RequestMakeLicenseMultipleChoiceQuestionDto requestMakeLicenseMultipleChoiceQuestionDto) {
		LicenseSession licenseSession = licenseSessionService.getLicenseSession(
			requestMakeLicenseMultipleChoiceQuestionDto.getLicenseSession(),
			requestMakeLicenseMultipleChoiceQuestionDto.getLicenseCategory());
		List<RequestMakeMultipleChoiceQuestionDto> questions = requestMakeLicenseMultipleChoiceQuestionDto.getQuestions();
		return questions
			.stream()
			.map(q -> saveNormalLicenseQuestion(q, licenseSession,
				requestMakeLicenseMultipleChoiceQuestionDto.getLicenseCategory()))
			.collect(Collectors.toList());
	}

	private ResponseQuestionDto saveNormalLicenseQuestion(
		RequestMakeMultipleChoiceQuestionDto requestMakeMultipleChoiceQuestionDto, LicenseSession licenseSession,
		LicenseCategory licenseCategory) {
		LicenseMultipleChoiceQuestion licenseMultipleChoiceQuestion = LicenseMultipleChoiceQuestion.makeWithDto(
			requestMakeMultipleChoiceQuestionDto, licenseSession, licenseCategory);
		licenseMultipleChoiceQuestionRepository.save(licenseMultipleChoiceQuestion);
		questionChoiceService.saveWith(requestMakeMultipleChoiceQuestionDto, licenseMultipleChoiceQuestion);
		return ResponseQuestionDto.forAdmin(licenseMultipleChoiceQuestion);
	}

	public String updateLicenseQuestionWithImage(Long licenseQuestionId, MultipartFile file) {
		try {
			LicenseMultipleChoiceQuestion licenseMultipleChoiceQuestion = licenseMultipleChoiceQuestionRepository.findById(
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
