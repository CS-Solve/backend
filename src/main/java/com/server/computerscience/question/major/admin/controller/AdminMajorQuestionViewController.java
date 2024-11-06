package com.server.computerscience.question.major.admin.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.server.computerscience.question.major.admin.service.AdminMajorQuestionClassifiedGetService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminMajorQuestionViewController {

	private final String baseUrl = "baseUrl";
	private final AdminMajorQuestionClassifiedGetService adminMajorQuestionClassifiedGetService;
	@Value("${resource.base-url}")
	private String resourceBaseUrl;

	@GetMapping("/question/update")
	public String updateQuestionPage(Model model) {
		model.addAttribute("classifiedQuestions",
			adminMajorQuestionClassifiedGetService.getClassifiedAllMajorQuestions());
		model.addAttribute(baseUrl, resourceBaseUrl);
		return "major-question-update";
	}
}