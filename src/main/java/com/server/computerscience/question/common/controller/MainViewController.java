package com.server.computerscience.question.common.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.server.computerscience.login.aspect.AddLoginStatusAttribute;
import com.server.computerscience.question.common.service.QuestionSelectorService;
import com.server.computerscience.question.license.domain.LicenseCategory;
import com.server.computerscience.question.license.dto.response.ResponseLicenseSessionDto;
import com.server.computerscience.question.license.dto.response.ResponseLicensesDto;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainViewController {
	private final QuestionSelectorService questionSelectorService;
	private final String baseUrl = "baseUrl";
	@Value("${resource.base-url}")
	private String resourceBaseUrl;

	@AddLoginStatusAttribute
	@GetMapping("/")
	public String showMainPage(Model model) {
		List<String> categories = questionSelectorService.getCategories();
		List<String> levels = questionSelectorService.getLevels();
		model.addAttribute("categories", categories);
		model.addAttribute("levels", levels);
		model.addAttribute(baseUrl, resourceBaseUrl);
		return "index";
	}

	@AddLoginStatusAttribute
	@GetMapping("/license")
	public String showLicensePage(Model model) {
		List<ResponseLicensesDto> licenseCategories = questionSelectorService.getLicenseCategories()
			.stream()
			.map(ResponseLicensesDto::from)
			.collect(Collectors.toList());
		model.addAttribute("licenseCategories", licenseCategories);
		model.addAttribute(baseUrl, resourceBaseUrl);

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