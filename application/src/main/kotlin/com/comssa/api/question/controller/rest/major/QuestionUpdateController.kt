// package com.comssa.api.question.controller.rest.major
//
// import com.comssa.api.question.service.rest.common.implement.QuestionUpdateService
// import io.swagger.annotations.ApiOperation
// import org.springframework.http.ResponseEntity
// import org.springframework.web.bind.annotation.PatchMapping
// import org.springframework.web.bind.annotation.PathVariable
// import org.springframework.web.bind.annotation.RequestMapping
// import org.springframework.web.bind.annotation.RestController
//
// @RestController
// @RequestMapping("/admin")
// class QuestionUpdateController(
// 	private val questionUpdateService: QuestionUpdateService,
// ) {
// 	@ApiOperation("문제 개시 허용")
// 	@PatchMapping("/question/common/{id}/toggle-approve")
// 	fun gradeDescriptiveQuestion(
// 		@PathVariable("id") id: Long,
// 	): ResponseEntity<Void> {
// 		questionUpdateService.toggleApprove(id)
// 		return ResponseEntity.ok().build()
// 	}
// }
