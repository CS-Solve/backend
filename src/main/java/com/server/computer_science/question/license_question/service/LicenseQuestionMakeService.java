package com.server.computer_science.question.license_question.service;

import com.server.computer_science.question.common.service.FileUploadService;
import com.server.computer_science.question.license_question.domain.LicenseCategory;
import com.server.computer_science.question.license_question.domain.LicenseNormalQuestion;
import com.server.computer_science.question.license_question.domain.LicenseSession;
import com.server.computer_science.question.license_question.dto.request.RequestMakeNormalLicenseQuestionDto;
import com.server.computer_science.question.license_question.repository.LicenseNormalQuestionRepository;
import com.server.computer_science.question.normal_question.admin.dto.RequestChangeContentDto;
import com.server.computer_science.question.normal_question.admin.dto.RequestChangeDescriptionDto;
import com.server.computer_science.question.normal_question.admin.dto.RequestMakeNormalQuestionDto;
import com.server.computer_science.question.common.service.Implements.QuestionChoiceService;
import com.server.computer_science.question.common.dto.response.ResponseNormalQuestionDto;
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
public class LicenseQuestionMakeService {
    private final LicenseSessionService licenseSessionService;
    private final LicenseNormalQuestionRepository licenseNormalQuestionRepository;
    private final QuestionChoiceService questionChoiceService;
    private final FileUploadService fileUploadService;
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

    public String updateLicenseQuestionWithImage(Long licenseQuestionId, MultipartFile file){
        try{
            LicenseNormalQuestion licenseNormalQuestion = licenseNormalQuestionRepository.findById(licenseQuestionId).orElse(null);
            String imageUrl = fileUploadService.uploadImage(file,"license");
            assert licenseNormalQuestion != null;
            licenseNormalQuestion.updateImage(imageUrl);
            return imageUrl;
        }
        catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
    public ResponseNormalQuestionDto changeDescription(Long questionId, RequestChangeDescriptionDto requestChangeDescriptionDto){
        LicenseNormalQuestion licenseNormalQuestion = licenseNormalQuestionRepository.findById(questionId).orElse(null);
        licenseNormalQuestion.changeDescription(requestChangeDescriptionDto.getDescription());

        return ResponseNormalQuestionDto.forAdmin(licenseNormalQuestion);
    }

    public ResponseNormalQuestionDto changeContent(Long questionId, RequestChangeContentDto requestChangeContentDto){
        LicenseNormalQuestion licenseNormalQuestion = licenseNormalQuestionRepository.findById(questionId).orElse(null);
        licenseNormalQuestion.changeContent(requestChangeContentDto.getContent());

        return ResponseNormalQuestionDto.forAdmin(licenseNormalQuestion);
    }

    public void deleteLicenseQuestion(Long questionId){
        LicenseNormalQuestion licenseNormalQuestion = licenseNormalQuestionRepository.findById(questionId).orElse(null);
        licenseNormalQuestionRepository.delete(licenseNormalQuestion);
    }


}
