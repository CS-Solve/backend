package com.server.computer_science.question.common.service.Implements;

import com.server.computer_science.question.license_question.domain.LicenseMultipleChoiceQuestion;
import com.server.computer_science.question.license_question.domain.LicenseQuestionChoice;
import com.server.computer_science.question.license_question.repository.LicenseQuestionChoiceRepository;
import com.server.computer_science.question.major_question.admin.dto.RequestMakeMajorMultipleChoiceQuestionDto;
import com.server.computer_science.question.major_question.common.domain.MajorMultipleChoiceQuestion;
import com.server.computer_science.question.major_question.common.domain.MajorQuestionChoice;
import com.server.computer_science.question.major_question.common.repository.MajorQuestionChoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionChoiceService {
    private final MajorQuestionChoiceRepository majorQuestionChoiceRepository;
    private final LicenseQuestionChoiceRepository licenseQuestionChoiceRepository;

    public void saveWith(RequestMakeMajorMultipleChoiceQuestionDto dto, LicenseMultipleChoiceQuestion licenseMultipleChoiceQuestion){
        licenseQuestionChoiceRepository.saveAll(dto.getQuestionChoices()
                .stream()
                .map(qc -> LicenseQuestionChoice.from(qc, licenseMultipleChoiceQuestion))
                .collect(Collectors.toList()));
    }
    public void saveWith(RequestMakeMajorMultipleChoiceQuestionDto dto, MajorMultipleChoiceQuestion majorMultipleChoiceQuestion){
        majorQuestionChoiceRepository.saveAll(dto.getQuestionChoices()
                .stream()
                .map(qc -> MajorQuestionChoice.fromNormalQuestion(qc, majorMultipleChoiceQuestion))
                .collect(Collectors.toList()));
    }

}
