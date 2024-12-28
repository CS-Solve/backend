package com.comssa.api.question.controller.rest.major

import com.comssa.api.question.service.rest.major.AdminMajorQuestionMakeService
import com.comssa.persistence.question.dto.common.request.RequestMakeMultipleChoiceQuestionDto
import com.comssa.persistence.question.dto.common.response.ResponseDescriptiveQuestionDto
import com.comssa.persistence.question.dto.common.response.ResponseMultipleChoiceQuestionDto
import com.comssa.persistence.question.dto.common.response.ResponseQuestionDto.Companion.from
import com.comssa.persistence.question.dto.major.request.RequestMakeMajorDescriptiveQuestionDto
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Api(tags = ["전공 문제 생성"])
@RequestMapping("/admin")
class MajorQuestionMakeController(
	val adminMajorQuestionMakeService: AdminMajorQuestionMakeService,
) {
	@ApiOperation("단답형 문제 리스트로 생성")
	@PostMapping("/question/major/multiple")
	fun makeMajorMultipleChoiceQuestion(
		@RequestBody requestMakeMultipleChoiceQuestions: List<RequestMakeMultipleChoiceQuestionDto>,
	): ResponseEntity<List<ResponseMultipleChoiceQuestionDto>> =
		ResponseEntity.ok(
			adminMajorQuestionMakeService
				.makeMultipleChoiceQuestions(requestMakeMultipleChoiceQuestions)
				.map { question -> from(question) },
		)

	@ApiOperation("서술형 문제 리스트로 생성")
	@PostMapping(value = ["/question/major/descriptive"])
	fun makeDescriptiveQuestion(
		@RequestBody requestMakeMajorDescriptiveQuestions: List<RequestMakeMajorDescriptiveQuestionDto?>?,
	): ResponseEntity<List<ResponseDescriptiveQuestionDto>> =
		ResponseEntity.ok(
			adminMajorQuestionMakeService
				.makeDescriptiveQuestions(requestMakeMajorDescriptiveQuestions)
				.map { question -> from(question) },
		)
}
