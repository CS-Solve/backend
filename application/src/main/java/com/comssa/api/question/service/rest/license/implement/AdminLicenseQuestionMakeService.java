package com.comssa.api.question.service.rest.license.implement;


import com.comssa.api.question.service.rest.common.implement.QuestionChoiceService;
import com.comssa.persistence.question.domain.common.Question;
import com.comssa.persistence.question.domain.license.LicenseCategory;
import com.comssa.persistence.question.domain.license.LicenseMultipleChoiceQuestion;
import com.comssa.persistence.question.domain.license.LicenseSession;
import com.comssa.persistence.question.dto.common.request.RequestMakeMultipleChoiceQuestionDto;
import com.comssa.persistence.question.dto.common.response.ResponseMultipleChoiceQuestionDto;
import com.comssa.persistence.question.dto.common.response.ResponseQuestionDto;
import com.comssa.persistence.question.dto.license.request.RequestMakeLicenseMultipleChoiceQuestionDto;
import com.comssa.persistence.question.repository.QuestionRepository;
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
	private final QuestionRepository<Question> questionRepository;
	private final QuestionChoiceService questionChoiceService;

	public List<ResponseMultipleChoiceQuestionDto> makeLicenseNormalQuestion(
		RequestMakeLicenseMultipleChoiceQuestionDto requestMakeLicenseMultipleChoiceQuestionDto) {
		LicenseSession licenseSession = licenseSessionService.getLicenseSession(
			requestMakeLicenseMultipleChoiceQuestionDto.getLicenseSession(),
			requestMakeLicenseMultipleChoiceQuestionDto.getLicenseCategory());
		List<RequestMakeMultipleChoiceQuestionDto> questions = requestMakeLicenseMultipleChoiceQuestionDto
			.getQuestions();
		return questions
			.stream()
			.map(q -> saveLicenseMultipleChoiceQuestion(q, licenseSession,
				requestMakeLicenseMultipleChoiceQuestionDto.getLicenseCategory()))
			.collect(Collectors.toList());
	}

	private ResponseMultipleChoiceQuestionDto saveLicenseMultipleChoiceQuestion(
		RequestMakeMultipleChoiceQuestionDto requestMakeMultipleChoiceQuestionDto,
		LicenseSession licenseSession,
		LicenseCategory licenseCategory) {
		LicenseMultipleChoiceQuestion licenseMultipleChoiceQuestion = LicenseMultipleChoiceQuestion.makeWithDto(
			requestMakeMultipleChoiceQuestionDto, licenseSession, licenseCategory);
		questionRepository.save(licenseMultipleChoiceQuestion);
		questionChoiceService.saveWith(requestMakeMultipleChoiceQuestionDto, licenseMultipleChoiceQuestion);
		return ResponseQuestionDto.from(licenseMultipleChoiceQuestion);
	}


}
