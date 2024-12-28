package com.comssa.api.question.controller.rest.license

import com.comssa.api.question.service.rest.license.implement.AdminLicenseQuestionMakeService
import com.comssa.persistence.question.dto.common.response.ResponseMultipleChoiceQuestionDto
import com.comssa.persistence.question.dto.license.request.RequestMakeLicenseMultipleChoiceQuestionDto
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin")
@Api(tags = ["자격증 문제 - ADMIN"])
class LicenseQuestionMakeController(
	val adminLicenseQuestionMakeService: AdminLicenseQuestionMakeService,
) {
	@ApiOperation("단답형 문제 세션으로 생성")
	@PostMapping("/question/license/multiple")
	fun makeLicenseQuestionOfSession(
		@RequestBody requestMakeLicenseMultipleChoiceQuestionDto: RequestMakeLicenseMultipleChoiceQuestionDto,
	): ResponseEntity<List<ResponseMultipleChoiceQuestionDto>> =
		ResponseEntity.ok(
			adminLicenseQuestionMakeService
				.makeLicenseMultipleChoiceQuestion(requestMakeLicenseMultipleChoiceQuestionDto),
		)
}
