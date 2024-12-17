package com.comssa.persistence.question.dto.license.response

import com.comssa.persistence.question.domain.license.LicenseCategory

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
