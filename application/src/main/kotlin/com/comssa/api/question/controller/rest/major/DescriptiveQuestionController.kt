package com.comssa.api.question.controller.rest.major

import com.comssa.core.question.service.common.DescriptiveQuestionService
import com.comssa.persistence.question.dto.common.request.RequestChangeQuestionGradeStandardDto
import com.comssa.persistence.question.dto.common.request.RequestDoGradeDescriptiveAnswerDto
import com.comssa.persistence.question.dto.common.response.ResponseDescriptiveQuestionDto
import com.comssa.persistence.question.dto.common.response.ResponseQuestionDto
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
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
	@ApiOperation("서술형 문제 채점")
	@PostMapping("/questions/major/descriptive/{questionId}/grade")
	fun gradeDescriptiveQuestion(
		@PathVariable("questionId") questionId: Long,
		@RequestBody requestDoGradeDescriptiveAnswerDto: RequestDoGradeDescriptiveAnswerDto,
	): ResponseEntity<String> =
		ResponseEntity.ok(
			descriptiveQuestionService.gradeDescriptiveQuestion(questionId, requestDoGradeDescriptiveAnswerDto),
		)

	@ApiOperation("서술형 문제 채점 기준 수정")
	@PatchMapping("/questions/major/descriptive/{questionId}/standard")
	fun changeQuestionStandard(
		@PathVariable("questionId") questionId: Long,
		@RequestBody requestChangeQuestionGradeStandardDto: RequestChangeQuestionGradeStandardDto,
	): ResponseEntity<ResponseQuestionDto> =
		ResponseEntity.ok(
			ResponseDescriptiveQuestionDto.forMajor(
				descriptiveQuestionService.changeGradeStandard(questionId, requestChangeQuestionGradeStandardDto),
			),
		)
}
