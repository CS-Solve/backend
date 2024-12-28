package com.comssa.api.question.controller.rest.common

import com.comssa.api.question.service.rest.common.implement.QuestionChoiceUpdateService
import com.comssa.persistence.question.domain.common.QuestionChoice
import com.comssa.persistence.question.dto.common.request.RequestChangeQuestionContentDto
import com.comssa.persistence.question.dto.common.response.ResponseQuestionChoiceDto
import com.comssa.persistence.question.dto.common.response.ResponseQuestionChoiceDto.Companion.from
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Api(tags = ["문제 선택지 수정"])
@RestController
@RequestMapping("/admin")
class QuestionChoiceUpdateController(
	val questionChoiceUpdateService: QuestionChoiceUpdateService,
) {
	@ApiOperation("단답형 선택지 업데이트 - 선택지 지문 업데이트")
	@PatchMapping(value = ["/question/common/choice/{id}"])
	fun changeChoiceContent(
		@PathVariable("id") choiceId: Long?,
		@RequestBody requestChangeQuestionContentDto: RequestChangeQuestionContentDto?,
	): ResponseEntity<ResponseQuestionChoiceDto> =
		ResponseEntity.ok(
			from<QuestionChoice>(
				questionChoiceUpdateService.changeContent(
					choiceId,
					requestChangeQuestionContentDto,
				),
			),
		)

	@ApiOperation("단답형 선택지 업데이트 - 정답 여부 변경")
	@PatchMapping(value = ["/question/common/choice/{id}/toggle"])
	fun changeChoiceContent(
		@PathVariable("id") licenseChoiceId: Long?,
	): ResponseEntity<ResponseQuestionChoiceDto> =
		ResponseEntity.ok(
			from<QuestionChoice>(
				questionChoiceUpdateService
					.toggleAnswerStatus(
						licenseChoiceId,
					),
			),
		)

	@ApiOperation("단답형 선택지 업데이트 - 선택지 삭제")
	@DeleteMapping(value = ["/question/common/choice/{id}"])
	fun deleteChoiceContent(
		@PathVariable("id") licenseChoiceId: Long?,
	): ResponseEntity<Void> {
		questionChoiceUpdateService.deleteQuestionChoice(
			licenseChoiceId,
		)
		return ResponseEntity.noContent().build()
	}
}
