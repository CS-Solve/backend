package com.comssa.api.question.service.rest.license.implement;


import com.comssa.api.question.service.rest.common.implement.QuestionChoiceService;
import com.comssa.persistence.question.domain.license.LicenseCategory;
import com.comssa.persistence.question.domain.license.LicenseMultipleChoiceQuestion;
import com.comssa.persistence.question.domain.license.LicenseSession;
import com.comssa.persistence.question.dto.common.response.ResponseMultipleChoiceQuestionDto;
import com.comssa.persistence.question.dto.license.request.RequestMakeLicenseMultipleChoiceQuestionDto;
import com.comssa.persistence.question.dto.major.request.RequestMakeMajorMultipleChoiceQuestionDto;
import com.comssa.persistence.question.repository.LicenseMultipleChoiceQuestionRepository;
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
