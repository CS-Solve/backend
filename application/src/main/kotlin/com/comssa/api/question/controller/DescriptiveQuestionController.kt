package com.comssa.api.question.controller

import com.comssa.core.question.common.dto.RequestGradeStandardDto
import com.comssa.core.question.common.dto.RequestUserDescriptiveAnswerDto
import com.comssa.core.question.common.service.DescriptiveQuestionService
import com.comssa.persistence.question.common.dto.response.ResponseDescriptiveQuestionDto
import com.comssa.persistence.question.common.dto.response.ResponseQuestionDto
import io.swagger.annotations.Api
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
@Api(tags = ["서술형 문제"])
class DescriptiveQuestionController(
	private val descriptiveQuestionService: DescriptiveQuestionService,
) {
	@PostMapping("/questions/descriptive/{questionId}/grade")
	fun gradeDescriptiveQuestion(
		@PathVariable("questionId") questionId: Long,
		@RequestBody requestUserDescriptiveAnswerDto: RequestUserDescriptiveAnswerDto,
	): ResponseEntity<String> =
		ResponseEntity.ok(
			descriptiveQuestionService.gradeDescriptiveQuestion(questionId, requestUserDescriptiveAnswerDto),
		)

	@PatchMapping("/questions/descriptive/{questionId}/standard")
	fun changeQuestionStandard(
		@PathVariable("questionId") questionId: Long,
		@RequestBody requestGradeStandardDto: RequestGradeStandardDto,
	): ResponseEntity<ResponseQuestionDto> =
		ResponseEntity.ok(
			ResponseDescriptiveQuestionDto.forAdminMajor(
				descriptiveQuestionService.changeGradeStandard(questionId, requestGradeStandardDto),
			),
		)

// 	@PostMapping("/questions/major/descriptive")
// 	fun makeMajorDescriptiveQuestion(
//
// 	):
}
