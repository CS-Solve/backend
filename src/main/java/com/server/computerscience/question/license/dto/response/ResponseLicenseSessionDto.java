package com.server.computerscience.question.license.dto.response;

import com.server.computerscience.question.license.domain.LicenseCategory;
import com.server.computerscience.question.license.domain.LicenseSession;

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
