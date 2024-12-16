package com.comssa.api.question.controller.view;


import com.comssa.api.question.service.major.AdminMajorQuestionClassifiedGetService;
import com.comssa.persistence.question.common.dto.response.ResponseClassifiedQuestionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminMajorQuestionViewController {

	private final String baseUrl = "baseUrl";
	private final AdminMajorQuestionClassifiedGetService adminMajorQuestionClassifiedGetService;

	@Value("${resource.base-url}")
	private String resourceBaseUrl;

	@GetMapping("/question/major/multiple/update")
	public String updateMultipleQuestionPage(Model model) {
		model.addAttribute("classifiedQuestions",
			adminMajorQuestionClassifiedGetService.getClassifiedAllMajorMultipleChoiceQuestions()
				.entrySet().stream()
				.map(entry -> ResponseClassifiedQuestionDto
					.majorMultipleQuestionForAdmin(entry.getKey(), entry.getValue()))
				.collect(Collectors.toList()));
		model.addAttribute(baseUrl, resourceBaseUrl);
		model.addAttribute("folderName", "index");
		model.addAttribute("isMajorQuestion", true);
		model.addAttribute("isMultipleChoice", true);
		return "question-update";
	}

	@GetMapping("/question/major/descriptive/update")
	public String updateDescriptiveQuestionPage(Model model) {
		model.addAttribute("classifiedQuestions",
			adminMajorQuestionClassifiedGetService.getClassifiedAllMajorDescriptiveQuestions()
				.entrySet().stream()
				.map(entry -> ResponseClassifiedQuestionDto.majorDescriptiveQuestion(entry.getKey(), entry.getValue()))
				.collect(Collectors.toList()));
		model.addAttribute(baseUrl, resourceBaseUrl);
		model.addAttribute("folderName", "index");
		model.addAttribute("isMajorQuestion", true);
		model.addAttribute("isMultipleChoice", false);
		return "question-update";
	}
}
