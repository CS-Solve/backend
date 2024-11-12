package com.server.computerscience.question.major.user.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.server.computerscience.login.aspect.AddLoginStatusAttribute;
import com.server.computerscience.question.common.domain.QuestionCategory;
import com.server.computerscience.question.common.dto.response.ResponseClassifiedMultipleQuestionDto;
import com.server.computerscience.question.major.common.domain.MajorMultipleChoiceQuestion;
import com.server.computerscience.question.major.user.dto.request.RequestGetQuestionByCategoryAndLevelDto;
import com.server.computerscience.question.major.user.service.implement.BasicMajorQuestionClassifiedGetService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MajorQuestionGetViewController {

	private final BasicMajorQuestionClassifiedGetService basicMajorQuestionClassifiedGetService;
	private final String baseUrl = "baseUrl";
	@Value("${resource.base-url}")
	private String resourceBaseUrl;

	@AddLoginStatusAttribute
	@GetMapping("/question/major")
	public String getNormalQuestions(@RequestParam(required = false) List<String> levels,
		@RequestParam(required = false) List<String> categories,
		@RequestParam(required = false) Boolean multipleChoice,
		Model model) {
		Map<QuestionCategory, List<MajorMultipleChoiceQuestion>> questions = null;
		if (multipleChoice) {
			questions = basicMajorQuestionClassifiedGetService
				.getApprovedClassifiedMajorMultipleChoiceQuestions(
					RequestGetQuestionByCategoryAndLevelDto.fromKorean(categories, levels));
		} else {
			questions = basicMajorQuestionClassifiedGetService
				.getApprovedClassifiedShortAnsweredMajorQuestions(
					RequestGetQuestionByCategoryAndLevelDto.fromKorean(categories, levels));
		}
		String title = "CS 전공 문제 - " + questions.keySet().stream()
			.map(QuestionCategory::getKorean) // 각 엔트리의 `getKorean()` 호출
			.collect(Collectors.joining(", "));
		model.addAttribute(baseUrl, resourceBaseUrl);
		model.addAttribute("title", title);
		model.addAttribute("description", "다양한 분야와 난이도의 CS (컴퓨터 사이언스) 문제를 풀어볼 수 있습니다.");
		model.addAttribute("questionSession", title);
		model.addAttribute("questions", questions.entrySet().stream()
			.map(entry -> ResponseClassifiedMultipleQuestionDto.forUser(entry.getKey(), entry.getValue()))
			.collect(Collectors.toList()));
		model.addAttribute("multipleChoice", multipleChoice);
		model.addAttribute("isMajorQuestion", true);

		return "question"; // 문제를 보여줄 페이지의 이름
	}
}
