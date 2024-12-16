package com.comssa.api.question.controller.view;


import com.comssa.api.question.controller.rest.QuestionType;
import com.comssa.api.question.service.major.AdminMajorQuestionClassifiedGetService;
import com.comssa.persistence.question.common.domain.Question;
import com.comssa.persistence.question.common.domain.QuestionCategory;
import com.comssa.persistence.question.common.dto.response.ResponseClassifiedQuestionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminMajorQuestionViewController {

	private final String baseUrl = "baseUrl";
	private final AdminMajorQuestionClassifiedGetService adminMajorQuestionClassifiedGetService;

	@Value("${resource.base-url}")
	private String resourceBaseUrl;

	@GetMapping("/question/major/{questionType}")
	public String updateMultipleQuestionPage(
		Model model,
		@PathVariable("questionType") String questionType
	) {
		Map<QuestionCategory, List<Question>> questions = null;
		if (questionType.equals(QuestionType.MULTIPLE_CHOICE.getName())) {
			model.addAttribute("isMultipleChoice", true);
			model.addAttribute("classifiedQuestions", adminMajorQuestionClassifiedGetService.getClassifiedAllMajorMultipleChoiceQuestions()
				.entrySet().stream()
				.map(entry -> ResponseClassifiedQuestionDto
					.getQuestions(entry.getKey(), entry.getValue()))
				.collect(Collectors.toList()));
		} else if (questionType.equals(QuestionType.DESCRIPTIVE.getName())) {
			model.addAttribute("isMultipleChoice", false);
			model.addAttribute("classifiedQuestions", adminMajorQuestionClassifiedGetService.getClassifiedAllMajorDescriptiveQuestions()
				.entrySet().stream()
				.map(entry -> ResponseClassifiedQuestionDto
					.getQuestions(entry.getKey(), entry.getValue()))
				.collect(Collectors.toList()));
		}
		model.addAttribute(baseUrl, resourceBaseUrl);
		model.addAttribute("folderName", "index");
		model.addAttribute("isMajorQuestion", true);

		return "question-update";
	}
}
