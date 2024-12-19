package com.comssa.persistence.question.dto.license.response

import com.comssa.persistence.question.domain.license.LicenseCategory
import com.comssa.persistence.question.domain.license.LicenseSession

data class ResponseLicenseSessionDto(
	val id: Long,
	val content: String,
	val licenseCategory: LicenseCategory,
) {
	companion object {
		@JvmStatic
		fun from(licenseSession: LicenseSession): ResponseLicenseSessionDto =
			ResponseLicenseSessionDto(
				id = licenseSession.id,
				content = licenseSession.content,
				licenseCategory = licenseSession.licenseCategory,
			)
	}
}
