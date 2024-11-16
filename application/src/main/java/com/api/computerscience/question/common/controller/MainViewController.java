package com.api.computerscience.question.common.controller;

import com.api.computerscience.login.aspect.AddLoginStatusAttribute;
import com.api.computerscience.question.common.service.QuestionSelectorService;
import com.api.computerscience.question.license.dto.response.ResponseLicensesDto;
import com.persistence.computerscience.question.license.domain.LicenseCategory;
import com.persistence.computerscience.question.license.domain.dto.response.ResponseLicenseSessionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
		model.addAttribute("description", Arrays.stream(LicenseCategory.values())
			.map(LicenseCategory::getKorean)
			.collect(Collectors.joining(", "))
			+ " 등 컴퓨터 사이언스(CS) 자격증 기출 문제를 풀어보세요. 기출 문제 풀이를 통해 자격증 대비가 가능합니다.");

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
