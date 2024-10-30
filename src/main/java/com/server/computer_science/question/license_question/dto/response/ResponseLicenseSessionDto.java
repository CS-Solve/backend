package com.server.computer_science.question.license_question.dto.response;

import com.server.computer_science.question.license_question.domain.LicenseSession;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseLicenseSessionDto {
    private Long id;
    private String content;

    public static ResponseLicenseSessionDto from(LicenseSession licenseSession) {
        return ResponseLicenseSessionDto.builder()
                .id(licenseSession.getId())
                .content(licenseSession.getContent())
                .build();
    }
    @Builder
    public ResponseLicenseSessionDto(Long id, String content) {
        this.id = id;
        this.content = content;
    }
}
