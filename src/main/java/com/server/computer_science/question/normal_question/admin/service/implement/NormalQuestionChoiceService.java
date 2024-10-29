package com.server.computer_science.question.normal_question.admin.service.implement;

import com.server.computer_science.question.license_question.domain.LicenseNormalQuestion;
import com.server.computer_science.question.license_question.domain.LicenseNormalQuestionChoice;
import com.server.computer_science.question.license_question.repository.LicenseNormalQuestionChoiceRepository;
import com.server.computer_science.question.normal_question.admin.dto.RequestMakeNormalQuestionDto;
import com.server.computer_science.question.normal_question.common.domain.NormalQuestion;
import com.server.computer_science.question.normal_question.common.domain.NormalQuestionChoice;
import com.server.computer_science.question.normal_question.common.repository.NormalQuestionChoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class NormalQuestionChoiceService {
    private final NormalQuestionChoiceRepository normalQuestionChoiceRepository;
    private final LicenseNormalQuestionChoiceRepository licenseNormalQuestionChoiceRepository;

    public void saveWith(RequestMakeNormalQuestionDto dto, LicenseNormalQuestion licenseNormalQuestion){
        licenseNormalQuestionChoiceRepository.saveAll(dto.getQuestionChoices()
                .stream()
                .map(qc -> LicenseNormalQuestionChoice.fromLicenseNormalQuestion(qc,licenseNormalQuestion))
                .collect(Collectors.toList()));
    }
    public void saveWith(RequestMakeNormalQuestionDto dto, NormalQuestion normalQuestion){
        normalQuestionChoiceRepository.saveAll(dto.getQuestionChoices()
                .stream()
                .map(qc -> NormalQuestionChoice.fromNormalQuestion(qc,normalQuestion))
                .collect(Collectors.toList()));
    }

}
