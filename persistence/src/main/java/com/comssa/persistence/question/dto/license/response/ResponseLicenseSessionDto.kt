package com.comssa.persistence.question.dto.license.response;

import com.comssa.persistence.question.domain.license.LicenseCategory;
import com.comssa.persistence.question.domain.license.LicenseSession;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class ResponseLicenseSessionDto {
	private Long id;
	private String content;
	private LicenseCategory licenseCategory;

	@Builder
	public ResponseLicenseSessionDto(Long id, String content, LicenseCategory licenseCategory) {
		this.id = id;
		this.content = content;
		this.licenseCategory = licenseCategory;
	}

	public static ResponseLicenseSessionDto from(LicenseSession licenseSession) {
		return ResponseLicenseSessionDto.builder()
			.id(licenseSession.getId())
			.content(licenseSession.getContent())
			.licenseCategory(licenseSession.getLicenseCategory())
			.build();
	}

}
