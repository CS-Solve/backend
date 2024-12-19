package com.comssa.api.question.controller.view;

import com.comssa.api.login.aspect.AddLoginStatusAttributeToView;
import com.comssa.api.question.service.rest.common.QuestionSelectorService;
import com.comssa.api.question.service.view.HtmlTagService;
import com.comssa.persistence.question.domain.license.LicenseCategory;
import com.comssa.persistence.question.dto.license.response.ResponseLicenseSessionDto;
import com.comssa.persistence.question.dto.license.response.ResponseLicensesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class MainViewController {
	private final QuestionSelectorService questionSelectorService;
	private final HtmlTagService tagService;
	private final String baseUrl = "baseUrl";
	private final HtmlTagService htmlTagService;
	@Value("${resource.base-url}")
	private String resourceBaseUrl;

	@AddLoginStatusAttributeToView
	@GetMapping("/")
	public String showMainPage(Model model) {
		List<String> categories = questionSelectorService.getCategories();
		List<String> levels = questionSelectorService.getLevels();
		model.addAttribute("categories", categories);
		model.addAttribute("levels", levels);
		model.addAttribute(baseUrl, resourceBaseUrl);
		return "index";
	}

	@AddLoginStatusAttributeToView
	@GetMapping("/license")
	public String showLicensePage(Model model) {
		List<ResponseLicensesDto> licenseCategories = questionSelectorService.getLicenseCategories()
			.stream()
			.map(ResponseLicensesDto::from)
			.collect(Collectors.toList());
		model.addAttribute(baseUrl, resourceBaseUrl);
		model.addAttribute("licenseCategories", licenseCategories);
		htmlTagService.forLicenseMain(model);

		return "license-index";
	}

	@GetMapping("/license/{category}")
	@ResponseBody
	public ResponseEntity<List<ResponseLicenseSessionDto>> showLicenseSessions(@PathVariable LicenseCategory category) {
		List<ResponseLicenseSessionDto> licenseSessions = questionSelectorService.getLicenseSessions(category)
			.stream()
			.map(ResponseLicenseSessionDto::from)
			.collect(Collectors.toList());

		return ResponseEntity.ok(licenseSessions);
	}
}
