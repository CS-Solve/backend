package com.server.computer_science.question.license_question.service;

import com.server.computer_science.question.license_question.domain.LicenseCategory;
import com.server.computer_science.question.license_question.domain.LicenseNormalQuestion;
import com.server.computer_science.question.license_question.repository.LicenseNormalQuestionRepository;
import com.server.computer_science.question.normal_question.common.service.NormalQuestionClassifyService;
import com.server.computer_science.question.normal_question.user.dto.response.ResponseClassifiedNormalQuestionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LicenseQuestionGetService {
    private final LicenseNormalQuestionRepository licenseNormalQuestionRepository;
    private final NormalQuestionClassifyService normalQuestionClassifyService;

    public List<ResponseClassifiedNormalQuestionDto> getClassifiedLicenseNormalQuestion(Long sessionId) {
        List<LicenseNormalQuestion> licenseNormalQuestions = licenseNormalQuestionRepository.findAllByLicenseSessionId(sessionId);
        return normalQuestionClassifyService.classifyLicenseNormalQuestionByClass(licenseNormalQuestions)
                .entrySet().stream()
                .map(entry->ResponseClassifiedNormalQuestionDto.LicenseQuestionForUser(entry.getKey(),entry.getValue()))
                .collect(Collectors.toList());
    }

    public List<String> getLicenseCategories(){
        return Arrays.stream(LicenseCategory.values())
                .map(LicenseCategory::getKorean)
                .collect(Collectors.toList());
    }
}
