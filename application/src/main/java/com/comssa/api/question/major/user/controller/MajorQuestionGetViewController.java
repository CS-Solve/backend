package com.comssa.api.question.major.user.controller;

import com.comssa.api.login.aspect.AddLoginStatusAttributeToView;
import com.comssa.api.question.major.user.service.implement.UserMajorQuestionClassifiedGetService;
import com.comssa.persistence.question.common.domain.QuestionCategory;
import com.comssa.persistence.question.common.dto.response.ResponseClassifiedQuestionDto;
import com.comssa.persistence.question.major.domain.common.MajorDescriptiveQuestion;
import com.comssa.persistence.question.major.domain.common.MajorMultipleChoiceQuestion;
import com.comssa.persistence.question.major.user.dto.request.RequestGetQuestionByCategoryAndLevelDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class MajorQuestionGetViewController {

	private final UserMajorQuestionClassifiedGetService userMajorQuestionClassifiedGetService;
	private final String baseUrl = "baseUrl";
	@Value("${resource.base-url}")
	private String resourceBaseUrl;

	@AddLoginStatusAttributeToView
	@GetMapping("/question/major")
	public String getNormalQuestions(
		@RequestParam(required = false) List<String> levels,
		@RequestParam(required = false) List<String> categories,
		@RequestParam(required = false) Boolean multipleChoice,
		Model model) {

		String title;
		if (Boolean.TRUE.equals(multipleChoice)) {
			Map<QuestionCategory, List<MajorMultipleChoiceQuestion>> multipleChoiceQuestions =
				userMajorQuestionClassifiedGetService.getApprovedClassifiedMajorMultipleChoiceQuestions(
					RequestGetQuestionByCategoryAndLevelDto.fromKorean(categories, levels)
				);
			model.addAttribute("questions", multipleChoiceQuestions.entrySet().stream()
				.map(entry -> ResponseClassifiedQuestionDto.multipleQuestionForUser(entry.getKey(), entry.getValue()))
				.collect(Collectors.toList()));
			title = "CS 전공 문제 - " + multipleChoiceQuestions.keySet().stream()
				.map(QuestionCategory::getKorean)
				.collect(Collectors.joining(", "));
		} else {
			Map<QuestionCategory, List<MajorDescriptiveQuestion>> descriptiveQuestions =
				userMajorQuestionClassifiedGetService.getApprovedClassifiedDescriptiveQuestions(
					RequestGetQuestionByCategoryAndLevelDto.fromKorean(categories, levels)
				);
			model.addAttribute("questions", descriptiveQuestions.entrySet().stream()
				.map(entry -> ResponseClassifiedQuestionDto.majorDescriptiveQuestionForUser(entry.getKey(), entry.getValue()))
				.collect(Collectors.toList()));
			title = "CS 전공 문제 - " + descriptiveQuestions.keySet().stream()
				.map(QuestionCategory::getKorean)
				.collect(Collectors.joining(", "));
		}

		model.addAttribute(baseUrl, resourceBaseUrl);
		model.addAttribute("title", title);
		model.addAttribute("description", "다양한 분야와 난이도의 CS (컴퓨터 사이언스) 문제를 풀어볼 수 있습니다.");
		model.addAttribute("questionSession", title);
		model.addAttribute("multipleChoice", multipleChoice);
		model.addAttribute("isMajorQuestion", true);

		return "question";
	}
}

