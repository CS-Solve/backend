package com.server.computer_science.question.license_question.dto.request;

import com.server.computer_science.question.license_question.domain.LicenseCategory;
import com.server.computer_science.question.major_question.admin.dto.RequestMakeMajorMultipleChoiceQuestionDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
public class RequestMakeLicenseMultipleChoiceQuestionDto {
    private List<RequestMakeMajorMultipleChoiceQuestionDto> questions;
    private String licenseSession;
    private LicenseCategory licenseCategory;
}
