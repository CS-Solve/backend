package com.comssa.api.question.controller.view

import com.comssa.api.member.aspect.AddLoginStatusAttributeToView
import com.comssa.api.question.service.rest.common.QuestionSelectorService
import com.comssa.api.question.service.view.HtmlTagService
import com.comssa.persistence.question.domain.license.LicenseCategory
import com.comssa.persistence.question.dto.license.response.ResponseLicenseSessionDto
import com.comssa.persistence.question.dto.license.response.ResponseLicensesDto
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class MainPageViewController(
	val questionSelectorService: QuestionSelectorService,
	val htmlTagService: HtmlTagService,
	@Value("\${resource.base-url}") val resourceBaseUrl: String,
) {
	val baseUrl = "baseUrl"

	@AddLoginStatusAttributeToView
	@GetMapping("/")
	fun showMainPage(model: Model): String {
		val categories = questionSelectorService.categories
		val levels = questionSelectorService.levels
		model.addAttribute("categories", categories)
		model.addAttribute("levels", levels)
		model.addAttribute(baseUrl, resourceBaseUrl)
		return "index"
	}

	@AddLoginStatusAttributeToView
	@GetMapping("/license")
	fun showLicensePage(model: Model): String {
		val licenseCategories =
			questionSelectorService
				.licenseCategories
				.map { ResponseLicensesDto.from(it) }
		model.addAttribute(baseUrl, resourceBaseUrl)
		model.addAttribute("licenseCategories", licenseCategories)
		htmlTagService.forLicenseMain(model)

		return "license-index"
	}

	@GetMapping("/license/{category}")
	@ResponseBody
	fun showLicenseSessions(
		@PathVariable category: LicenseCategory,
	): ResponseEntity<List<ResponseLicenseSessionDto>> {
		val licenseSessions =
			questionSelectorService
				.getLicenseSessions(category)
				.map { ResponseLicenseSessionDto.from(it) }
		return ResponseEntity.ok(licenseSessions)
	}
}
