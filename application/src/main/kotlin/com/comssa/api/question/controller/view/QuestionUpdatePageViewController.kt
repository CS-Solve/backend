package com.comssa.api.question.controller.view

import com.comssa.api.question.domain.QuestionType
import com.comssa.api.question.service.rest.license.implement.AdminLicenseQuestionGetService
import com.comssa.api.question.service.rest.major.AdminMajorQuestionClassifiedGetService
import com.comssa.persistence.question.domain.common.Question
import com.comssa.persistence.question.domain.common.QuestionCategory
import com.comssa.persistence.question.dto.common.response.ResponseClassifiedQuestionDto
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.server.ResponseStatusException

@Controller
@RequestMapping("/admin")
class QuestionUpdatePageViewController(
	val adminMajorQuestionClassifiedGetService: AdminMajorQuestionClassifiedGetService,
	val adminLicenseQuestionGetService: AdminLicenseQuestionGetService,
	@Value("\${resource.base-url}") val resourceBaseUrl: String,
) {
	val baseUrl = "baseUrl"

	@GetMapping("/question/license/{sessionId}")
	fun updateLicenseQuestions(
		@PathVariable sessionId: Long,
		model: Model,
	): String {
		model.addAttribute(baseUrl, resourceBaseUrl)
		model.addAttribute("folderName", "license-index")
		model.addAttribute("isLicenseQuestion", true)
		model.addAttribute(
			"classifiedQuestions",
			adminLicenseQuestionGetService
				.getClassifiedLicenseMultipleChoiceQuestion(sessionId)
				.map { ResponseClassifiedQuestionDto.from(it.key, it.value) },
		)
		return "question-update"
	}

	@GetMapping("/question/major/{questionType}")
	fun updateMultipleQuestionPage(
		model: Model,
		@PathVariable("questionType") questionType: String,
	): String {
		val questions: Map<QuestionCategory, List<Question>> =
			when (questionType) {
				QuestionType.MULTIPLE_CHOICE.urlPath -> {
					model.addAttribute("isMultipleChoice", true)
					adminMajorQuestionClassifiedGetService
						.classifiedAllMajorMultipleChoiceQuestions
				}

				QuestionType.DESCRIPTIVE.urlPath -> {
					model.addAttribute("isMultipleChoice", false)
					adminMajorQuestionClassifiedGetService
						.classifiedAllMajorDescriptiveQuestions
				}

				else -> throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid question type")
			}
		model.addAttribute(
			"classifiedQuestions",
			questions.map { entry ->
				ResponseClassifiedQuestionDto.from(
					entry.key,
					entry.value,
				)
			},
		)

		model.addAttribute(baseUrl, resourceBaseUrl)
		model.addAttribute("folderName", "index")
		model.addAttribute("isMajorQuestion", true)

		return "question-update"
	}
}
