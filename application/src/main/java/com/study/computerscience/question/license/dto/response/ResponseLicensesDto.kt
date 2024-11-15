package com.study.computerscience.question.license.dto.response

import com.study.computerscience.question.license.domain.LicenseCategory

data class ResponseLicensesDto(
	val korean: String,
	val originalName: String,
) {
	companion object {
		@JvmStatic
		fun from(licenseCategory: LicenseCategory): ResponseLicensesDto =
			ResponseLicensesDto(
				korean = licenseCategory.korean,
				originalName = licenseCategory.name,
			)
	}
}
