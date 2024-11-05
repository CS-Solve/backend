package com.server.computer_science.question.license_question.dto.request;

import com.server.computer_science.question.common.domain.Question;
import com.server.computer_science.question.common.domain.QuestionChoice;
import com.server.computer_science.question.license_question.domain.LicenseCategory;
import com.server.computer_science.question.license_question.domain.LicenseMultipleChoiceQuestion;
import com.server.computer_science.question.license_question.domain.LicenseQuestionChoice;
import com.server.computer_science.question.major_question.admin.dto.RequestMakeMultipleChoiceQuestionDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
public class RequestMakeLicenseMultipleChoiceQuestionDto {
    private List<RequestMakeMultipleChoiceQuestionDto> questions;
    private String licenseSession;
    private LicenseCategory licenseCategory;

    public static RequestMakeLicenseMultipleChoiceQuestionDto from(
            String licenseSession
            , LicenseCategory licenseCategory
            , List<LicenseMultipleChoiceQuestion> questions
    , Function<LicenseMultipleChoiceQuestion,List<LicenseQuestionChoice>> questionChoiceExtractor) {
        return RequestMakeLicenseMultipleChoiceQuestionDto.builder()
                .questions(
                        questions.stream()
                                        .map(
                                                q->RequestMakeMultipleChoiceQuestionDto.from(q,questionChoiceExtractor.apply(q))
                                        )
                                .collect(Collectors.toList())

                )
                .licenseSession(licenseSession)
                .licenseCategory(licenseCategory)
                .build();
    }
}
