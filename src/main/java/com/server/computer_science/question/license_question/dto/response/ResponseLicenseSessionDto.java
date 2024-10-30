package com.server.computer_science.question.license_question.dto.response;

import com.server.computer_science.question.license_question.domain.LicenseCategory;
import com.server.computer_science.question.license_question.domain.LicenseSession;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseLicenseSessionDto {
    private Long id;
    private String content;
    private LicenseCategory licenseCategory;

    public static ResponseLicenseSessionDto from(LicenseSession licenseSession) {
        return ResponseLicenseSessionDto.builder()
                .id(licenseSession.getId())
                .content(licenseSession.getContent())
                .licenseCategory(licenseSession.getLicenseCategory())
                .build();
    }

   @Builder
    public ResponseLicenseSessionDto(Long id, String content, LicenseCategory licenseCategory) {
        this.id = id;
        this.content = content;
        this.licenseCategory = licenseCategory;
    }

    @Override
    public String toString() {
        return "ResponseLicenseSessionDto{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", licenseCategory=" + licenseCategory +
                '}';
    }
}
