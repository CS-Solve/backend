package com.comssa.api.question.major.user.controller;

import com.comssa.api.login.aspect.AddLoginStatusAttributeToView;
import com.comssa.api.question.major.user.service.implement.UserMajorQuestionClassifiedGetService;
import com.comssa.persistence.question.common.domain.QuestionCategory;
import com.comssa.persistence.question.common.dto.response.ResponseClassifiedQuestionDto;
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
	@GetMapping("/question/major/multiple")
	public String getNormalQuestions(
		@RequestParam(required = false) List<String> levels,
		@RequestParam(required = false) List<String> categories,
		@RequestParam(required = false) Boolean multipleChoice,
		Model model) {
		Map<QuestionCategory, List<MajorMultipleChoiceQuestion>> questions = null;
		if (multipleChoice) {
			questions = userMajorQuestionClassifiedGetService
				.getApprovedClassifiedMajorMultipleChoiceQuestions(
					RequestGetQuestionByCategoryAndLevelDto.fromKorean(categories, levels));
		} else {
			questions = userMajorQuestionClassifiedGetService
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
			.map(entry -> ResponseClassifiedQuestionDto.multipleQuestionForUser(entry.getKey(), entry.getValue()))
			.collect(Collectors.toList()));
		model.addAttribute("multipleChoice", multipleChoice);
		model.addAttribute("isMajorQuestion", true);

		return "question"; // 문제를 보여줄 페이지의 이름
	}

//	@AddLoginStatusAttributeToView
//	@GetMapping("/question/major/descriptive")
//	public String getMajorDescriptiveQuestions(
//		@RequestParam(required = false) List<String> levels,
//		@RequestParam(required = false) List<String> categories,
//		Model model
//	) {
//		Map<QuestionCategory, List<MajorDescriptiveQuestion>> questions = null;
//		questions = userMajorQuestionClassifiedGetService
//			.getApprovedClassifiedDescriptiveQuestions(
//				RequestGetQuestionByCategoryAndLevelDto.fromKorean(categories, levels));
//
//		model.addAttribute(baseUrl, resourceBaseUrl);
//		model.addAttribute("questions", questions.entrySet().stream()
//			.map(entry -> ResponseClassifiedMultipleQuestionDto.multipleQuestionForUser(entry.getKey(), entry.getValue()))
//			.collect(Collectors.toList()));
//	}
}
