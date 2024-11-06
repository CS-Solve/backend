package com.server.computerscience.question.major.user.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.server.computerscience.question.common.dto.response.ResponseClassifiedMultipleQuestionDto;
import com.server.computerscience.question.major.user.dto.request.RequestGetQuestionByCategoryAndLevelDto;
import com.server.computerscience.question.major.user.service.implement.BasicMajorQuestionClassifiedGetService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MajorQuestionGetViewController {

	private final BasicMajorQuestionClassifiedGetService basicMajorQuestionClassifiedGetService;

	@Value("${resource.base-url}")
	private String resourceBaseUrl;
	private final String baseUrl = "baseUrl";

	@GetMapping("/question/major")
	public String getNormalQuestions(@RequestParam(required = false) List<String> levels,
		@RequestParam(required = false) List<String> categories,
		@RequestParam(required = false) Boolean multipleChoice,
		Model model,
		Authentication auth) {
		if (multipleChoice) {
			model.addAttribute("questions", basicMajorQuestionClassifiedGetService
				.getApprovedClassifiedMajorMultipleChoiceQuestions(
					RequestGetQuestionByCategoryAndLevelDto.fromKorean(categories, levels))
				.entrySet().stream()
				.map(entry -> ResponseClassifiedMultipleQuestionDto.forUser(entry.getKey(), entry.getValue()))
				.collect(Collectors.toList()));
		} else {
			model.addAttribute("questions", basicMajorQuestionClassifiedGetService
				.getApprovedClassifiedShortAnsweredMajorQuestions(
					RequestGetQuestionByCategoryAndLevelDto.fromKorean(categories, levels))
				.entrySet().stream()
				.map(entry -> ResponseClassifiedMultipleQuestionDto.forUser(entry.getKey(), entry.getValue()))
				.collect(Collectors.toList()));
		}
		model.addAttribute(baseUrl, resourceBaseUrl);
		model.addAttribute("multipleChoice", multipleChoice);
		if (auth != null && auth.isAuthenticated()) {
			model.addAttribute("isLogin", true);
		} else {
			model.addAttribute("isLogin", false);
		}
		return "major-question"; // 문제를 보여줄 페이지의 이름
	}
}
