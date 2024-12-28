package com.comssa.api.question.controller.rest.major

import com.comssa.api.question.service.rest.major.implement.AdminMajorMultipleChoiceQuestionUpdateService
import com.comssa.core.question.service.common.DescriptiveQuestionService
import com.comssa.persistence.question.dto.common.request.RequestChangeQuestionGradeStandardDto
import com.comssa.persistence.question.dto.common.response.ResponseDescriptiveQuestionDto
import com.comssa.persistence.question.dto.common.response.ResponseMultipleChoiceQuestionDto
import com.comssa.persistence.question.dto.common.response.ResponseQuestionDto.Companion.from
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Api(tags = ["전공 문제 업데이트"])
@RequestMapping("/admin")
class MajorQuestionUpdateController(
	val descriptiveQuestionService: DescriptiveQuestionService,
	var adminMajorMultipleChoiceQuestionUpdateService: AdminMajorMultipleChoiceQuestionUpdateService,
) {
	@ApiOperation("서술형 문제 채점 기준 수정")
	@PatchMapping("/questions/major/descriptive/{questionId}/standard")
	fun changeQuestionStandard(
		@PathVariable("questionId") questionId: Long,
		@RequestBody requestChangeQuestionGradeStandardDto: RequestChangeQuestionGradeStandardDto,
	): ResponseEntity<ResponseDescriptiveQuestionDto> =
		ResponseEntity.ok(
			from(
				descriptiveQuestionService.changeGradeStandard(questionId, requestChangeQuestionGradeStandardDto),
			),
		)

	@ApiOperation("단답형 문제 상태 업데이트 - 단답형-주관식 토글")
	@PatchMapping(value = ["/question/major/multiple/{id}/toggle-multiple"])
	fun toggleCanBeShortAnswered(
		@PathVariable("id") questionId: Long?,
	): ResponseEntity<ResponseMultipleChoiceQuestionDto> =
		ResponseEntity.ok(
			from(
				adminMajorMultipleChoiceQuestionUpdateService.toggleCanBeShortAnswered(questionId),
			),
		)
}
