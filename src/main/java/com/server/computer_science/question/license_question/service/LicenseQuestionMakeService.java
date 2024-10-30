package com.server.computer_science.question.license_question.service;

import com.server.computer_science.question.license_question.domain.LicenseCategory;
import com.server.computer_science.question.license_question.domain.LicenseNormalQuestion;
import com.server.computer_science.question.license_question.domain.LicenseSession;
import com.server.computer_science.question.license_question.dto.request.RequestMakeNormalLicenseQuestionDto;
import com.server.computer_science.question.license_question.repository.LicenseNormalQuestionRepository;
import com.server.computer_science.question.normal_question.admin.dto.RequestMakeNormalQuestionDto;
import com.server.computer_science.question.common.service.Implements.QuestionChoiceService;
import com.server.computer_science.question.common.dto.response.ResponseNormalQuestionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class LicenseQuestionMakeService {
    private final LicenseSessionService licenseSessionService;
    private final LicenseNormalQuestionRepository licenseNormalQuestionRepository;
    private final QuestionChoiceService questionChoiceService;
    public List<ResponseNormalQuestionDto> makeLicenseNormalQuestion(RequestMakeNormalLicenseQuestionDto requestMakeNormalLicenseQuestionDto){
        LicenseSession licenseSession = licenseSessionService.getLicenseSession(
                requestMakeNormalLicenseQuestionDto.getLicenseSession(),
                requestMakeNormalLicenseQuestionDto.getLicenseCategory());
        List<RequestMakeNormalQuestionDto> questions = requestMakeNormalLicenseQuestionDto.getQuestions();
        return questions
                .stream()
                .map(q-> saveNormalLicenseQuestion(q,licenseSession,requestMakeNormalLicenseQuestionDto.getLicenseCategory()))
                .collect(Collectors.toList());
    }

    private ResponseNormalQuestionDto saveNormalLicenseQuestion(RequestMakeNormalQuestionDto requestMakeNormalQuestionDto, LicenseSession licenseSession, LicenseCategory licenseCategory){
        LicenseNormalQuestion licenseNormalQuestion = LicenseNormalQuestion.makeWithDto(requestMakeNormalQuestionDto,licenseSession,licenseCategory);
        licenseNormalQuestionRepository.save(licenseNormalQuestion);
        questionChoiceService.saveWith(requestMakeNormalQuestionDto,licenseNormalQuestion);
        return ResponseNormalQuestionDto.forAdmin(licenseNormalQuestion);
    }
}
