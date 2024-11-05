package com.server.computer_science.question.license_question.service;

import com.server.computer_science.question.common.dto.response.ResponseQuestionDto;
import com.server.computer_science.question.common.service.FileUploadService;
import com.server.computer_science.question.license_question.domain.LicenseCategory;
import com.server.computer_science.question.license_question.domain.LicenseMultipleChoiceQuestion;
import com.server.computer_science.question.license_question.domain.LicenseSession;
import com.server.computer_science.question.license_question.dto.request.RequestMakeLicenseMultipleChoiceQuestionDto;
import com.server.computer_science.question.license_question.repository.LicenseMultipleChoiceQuestionRepository;
import com.server.computer_science.question.major_question.admin.dto.RequestMakeMultipleChoiceQuestionDto;
import com.server.computer_science.question.common.service.Implements.QuestionChoiceService;
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
    public List<ResponseQuestionDto> makeLicenseNormalQuestion(RequestMakeLicenseMultipleChoiceQuestionDto requestMakeLicenseMultipleChoiceQuestionDto){
        LicenseSession licenseSession = licenseSessionService.getLicenseSession(
                requestMakeLicenseMultipleChoiceQuestionDto.getLicenseSession(),
                requestMakeLicenseMultipleChoiceQuestionDto.getLicenseCategory());
        List<RequestMakeMultipleChoiceQuestionDto> questions = requestMakeLicenseMultipleChoiceQuestionDto.getQuestions();
        return questions
                .stream()
                .map(q-> saveNormalLicenseQuestion(q,licenseSession, requestMakeLicenseMultipleChoiceQuestionDto.getLicenseCategory()))
                .collect(Collectors.toList());
    }

    private ResponseQuestionDto saveNormalLicenseQuestion(RequestMakeMultipleChoiceQuestionDto requestMakeMultipleChoiceQuestionDto, LicenseSession licenseSession, LicenseCategory licenseCategory){
        LicenseMultipleChoiceQuestion licenseMultipleChoiceQuestion = LicenseMultipleChoiceQuestion.makeWithDto(requestMakeMultipleChoiceQuestionDto,licenseSession,licenseCategory);
        licenseMultipleChoiceQuestionRepository.save(licenseMultipleChoiceQuestion);
        questionChoiceService.saveWith(requestMakeMultipleChoiceQuestionDto, licenseMultipleChoiceQuestion);
        return ResponseQuestionDto.forAdmin(licenseMultipleChoiceQuestion);
    }

    public String updateLicenseQuestionWithImage(Long licenseQuestionId, MultipartFile file){
        try{
            LicenseMultipleChoiceQuestion licenseMultipleChoiceQuestion = licenseMultipleChoiceQuestionRepository.findById(licenseQuestionId).orElse(null);
            String imageUrl = fileUploadService.uploadImage(file,"license");
            assert licenseMultipleChoiceQuestion != null;
            licenseMultipleChoiceQuestion.updateImage(imageUrl);
            return imageUrl;
        }
        catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
}
