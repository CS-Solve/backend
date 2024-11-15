package com.server.computerscience.question.license.dto.response

data class ResponseLicensesDto(
	val korean: String,
	val originalName: String,
) {
	companion object {
		@JvmStatic
		fun from(licenseCategory: com.server.computerscience.question.license.domain.LicenseCategory): ResponseLicensesDto =
			ResponseLicensesDto(
				korean = licenseCategory.korean,
				originalName = licenseCategory.name,
			)
	}
}
