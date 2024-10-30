package com.server.computer_science.question.license_question.dto.request;

import com.server.computer_science.question.common.dto.RequestMakeQuestionDto;
import com.server.computer_science.question.license_question.domain.LicenseCategory;
import com.server.computer_science.question.normal_question.admin.dto.RequestMakeNormalQuestionChoiceDto;
import com.server.computer_science.question.normal_question.admin.dto.RequestMakeNormalQuestionDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
public class RequestMakeNormalLicenseQuestionDto {
    private List<RequestMakeNormalQuestionDto> questions;
    private String licenseSession;
    private LicenseCategory licenseCategory;
}
