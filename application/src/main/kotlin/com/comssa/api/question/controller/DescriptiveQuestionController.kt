package com.comssa.api.question.controller

import com.comssa.core.question.common.dto.RequestUserDescriptiveAnswerDto
import com.comssa.core.question.common.service.DescriptiveQuestionService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
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

// 	@PostMapping("/questions/major/descriptive")
// 	fun makeMajorDescriptiveQuestion(
//
// 	):
}
