package com.comssa.api.question.controller.rest.common

import com.comssa.api.question.service.rest.common.implement.QuestionUpdateService
import com.comssa.persistence.question.dto.common.request.RequestChangeQuestionContentDto
import com.comssa.persistence.question.dto.common.request.RequestChangeQuestionDescriptionDto
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@Api(tags = ["문제 수정"])
@RestController
@RequestMapping("/admin")
class QuestionUpdateController(
	val questionUpdateService: QuestionUpdateService,
) {
	@ApiOperation("문제 개시 허용")
	@PatchMapping(value = ["/question/common/{id}/toggle-approve"])
	fun toggleApproveNormalQuestion(
		@PathVariable("id") questionId: Long?,
	): ResponseEntity<Void> {
		questionUpdateService.toggleApprove(questionId)
		return ResponseEntity.ok().build()
	}

	@ApiOperation("문제 본문 업데이트")
	@PatchMapping(value = ["/question/common/{id}/content"])
	fun changeQuestion(
		@PathVariable("id") questionId: Long?,
		@RequestBody requestChangeQuestionContentDto: RequestChangeQuestionContentDto?,
	): ResponseEntity<Void> {
		questionUpdateService.changeContent(questionId, requestChangeQuestionContentDto)
		return ResponseEntity.ok().build()
	}

	@ApiOperation("해설 업데이트")
	@PatchMapping(value = ["/question/common/{id}/description"])
	fun changeDescription(
		@PathVariable("id") questionId: Long?,
		@RequestBody requestChangeQuestionDescriptionDto: RequestChangeQuestionDescriptionDto?,
	): ResponseEntity<Void> {
		questionUpdateService.changeDescription(questionId, requestChangeQuestionDescriptionDto)
		return ResponseEntity.ok().build()
	}

	@ApiOperation("이미지 업로드")
	@PatchMapping("/question/common/{id}/image")
	fun updateLicenseQuestionWithImage(
		@PathVariable("id") licenseQuestionId: Long?,
		@RequestPart(value = "image") file: MultipartFile?,
	): ResponseEntity<String> = ResponseEntity.ok<String>(questionUpdateService.updateImage(licenseQuestionId, file))

	@ApiOperation(" 문제 삭제")
	@DeleteMapping(value = ["/question/common/{id}"])
	fun changeDescription(
		@PathVariable("id") questionId: Long?,
	): ResponseEntity<Void> {
		questionUpdateService.deleteQuestion(questionId)
		return ResponseEntity.noContent().build()
	}
}
