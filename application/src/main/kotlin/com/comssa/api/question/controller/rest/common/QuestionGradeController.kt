package com.comssa.api.question.controller.rest.common

import com.comssa.api.question.service.rest.common.QuestionChoiceGradeService
import com.comssa.core.question.service.common.DescriptiveQuestionService
import com.comssa.persistence.question.dto.common.request.RequestDoGradeDescriptiveAnswerDto
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter

@Api(tags = ["문제 채점"])
@RestController
class QuestionGradeController(
	val questionChoiceGradeService: QuestionChoiceGradeService,
	val descriptiveQuestionService: DescriptiveQuestionService,
) {
	/**
	 * @param questionField (major 또는 License)
	 * 각 구현체에 따라 채점 방식이 다름름
	 */
	@ApiOperation("객관식 문제 채점")
	@PatchMapping("/question/{questionField}/multiple/choice/{choiceId}/grade")
	fun gradeMultipleChoiceQuestion(
		@PathVariable("questionField") questionField: String,
		@PathVariable("choiceId") choiceId: Long,
	): ResponseEntity<Boolean> = ResponseEntity.ok(questionChoiceGradeService.isChoiceAnswer(choiceId))

	@ApiOperation("서술형 문제 채점 - 제출 답안 등록")
	@PostMapping(
		value = ["/questions/{questionField}/descriptive/{questionId}/grade"],
		produces = [MediaType.TEXT_EVENT_STREAM_VALUE],
	)
	fun gradeDescriptiveQuestion(
		@PathVariable("questionField") questionField: String,
		@PathVariable("questionId") questionId: Long,
		@RequestBody requestDoGradeDescriptiveAnswerDto: RequestDoGradeDescriptiveAnswerDto,
	): ResponseEntity<SseEmitter> =
		ResponseEntity.ok(
			descriptiveQuestionService.gradeDescriptiveQuestion(questionId, requestDoGradeDescriptiveAnswerDto),
		)
}
