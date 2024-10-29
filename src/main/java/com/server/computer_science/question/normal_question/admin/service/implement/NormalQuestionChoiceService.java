package com.server.computer_science.question.normal_question.admin.service.implement;

import com.server.computer_science.question.license_question.domain.LicenseNormalQuestion;
import com.server.computer_science.question.normal_question.admin.dto.RequestMakeNormalQuestionDto;
import com.server.computer_science.question.normal_question.common.domain.NormalQuestion;
import com.server.computer_science.question.normal_question.common.domain.QuestionChoice;
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

    public void saveWith(RequestMakeNormalQuestionDto dto, LicenseNormalQuestion licenseNormalQuestion){
        normalQuestionChoiceRepository.saveAll(dto.getQuestionChoices()
                .stream()
                .map(qc -> QuestionChoice.fromLicenseNormalQuestion(qc,licenseNormalQuestion))
                .collect(Collectors.toList()));
    }
    public void saveWith(RequestMakeNormalQuestionDto dto, NormalQuestion normalQuestion){
        normalQuestionChoiceRepository.saveAll(dto.getQuestionChoices()
                .stream()
                .map(qc -> QuestionChoice.fromNormalQuestion(qc,normalQuestion))
                .collect(Collectors.toList()));
    }

}
