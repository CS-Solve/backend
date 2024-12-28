package com.comssa.api.question.controller.view

import com.comssa.api.login.aspect.AddLoginStatusAttributeToView
import com.comssa.api.question.service.rest.license.implement.LicenseSessionService
import com.comssa.api.question.service.rest.license.implement.UserLicenseQuestionGetService
import com.comssa.api.question.service.rest.major.implement.UserMajorQuestionClassifiedGetService
import com.comssa.api.question.service.view.HtmlTagService
import com.comssa.persistence.question.domain.common.Question
import com.comssa.persistence.question.domain.common.QuestionCategory
import com.comssa.persistence.question.dto.common.response.ResponseClassifiedQuestionDto
import com.comssa.persistence.question.dto.major.request.RequestGetQuestionByCategoryAndLevelDto.Companion.fromKorean
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam

@Controller
class QuestionPageViewController(
	val userMajorQuestionClassifiedGetService: UserMajorQuestionClassifiedGetService,
	val userLicenseQuestionGetService: UserLicenseQuestionGetService,
	val licenseSessionService: LicenseSessionService,
	val htmlTagService: HtmlTagService,
	@Value("\${resource.base-url}") val resourceBaseUrl: String,
) {
	val baseUrl = "baseUrl"

	@AddLoginStatusAttributeToView
	@GetMapping("/question/major")
	fun getNormalQuestions(
		@RequestParam(required = false) levels: List<String>?,
		@RequestParam(required = false) categories: List<String>?,
		@RequestParam(required = false) multipleChoice: Boolean,
		model: Model,
	): String {
		val questions = getQuestions(levels, categories, multipleChoice)

		htmlTagService.forMajor(questions.keys, multipleChoice, model)

		model.addAttribute(baseUrl, resourceBaseUrl)
		model.addAttribute("questions", transformQuestion(questions))
		model.addAttribute("multipleChoice", multipleChoice)
		model.addAttribute("isMajorQuestion", true)

		if (multipleChoice) {
			return "question"
		}
		return "descriptiveQuestion"
	}

	@AddLoginStatusAttributeToView
	@GetMapping("/question/license/{sessionId}")
	fun getLicenseQuestionsBySession(
		@PathVariable sessionId: Long,
		model: Model,
	): String {
		val licenseSession = licenseSessionService.getLicenseSessionById(sessionId)
		htmlTagService.forLicenseQuestion(licenseSession, model)
		val questions =
			userLicenseQuestionGetService.getClassifiedLicenseMultipleChoiceQuestion(sessionId)

		model.addAttribute(baseUrl, resourceBaseUrl)
		model.addAttribute("questions", transformQuestion(questions))
		model.addAttribute("multipleChoice", true)
		model.addAttribute("isMajorQuestion", false)
		return "question"
	}

	private fun transformQuestion(questions: Map<QuestionCategory, List<Question>>): List<ResponseClassifiedQuestionDto> =
		questions
			.map { entry -> ResponseClassifiedQuestionDto.from(entry.key, entry.value) }

	private fun getQuestions(
		levels: List<String>?,
		categories: List<String>?,
		multipleChoice: Boolean,
	): Map<QuestionCategory, List<Question>> {
		if (multipleChoice) {
			return userMajorQuestionClassifiedGetService.getApprovedClassifiedMajorMultipleChoiceQuestions(
				fromKorean(categories, levels),
			)
		}
		return userMajorQuestionClassifiedGetService.getApprovedClassifiedDescriptiveQuestions(
			fromKorean(categories, levels),
		)
	}
}
