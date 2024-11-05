package com.server.computerscience.question.license.dto.response;

import com.server.computerscience.question.license.domain.LicenseCategory;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseLicensesDto {
	private String korean;
	private String originalName;

	public static ResponseLicensesDto from(LicenseCategory licenseCategory) {
		return ResponseLicensesDto.builder()
			.korean(licenseCategory.getKorean())
			.originalName(licenseCategory.name())
			.build();
	}

	@Builder
	public ResponseLicensesDto(String korean, String originalName) {
		this.korean = korean;
		this.originalName = originalName;
	}
}
