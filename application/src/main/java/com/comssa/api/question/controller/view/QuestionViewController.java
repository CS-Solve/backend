package com.comssa.api.question.controller.view;

import com.comssa.api.login.aspect.AddLoginStatusAttributeToView;
import com.comssa.api.question.service.rest.license.implement.LicenseSessionService;
import com.comssa.api.question.service.rest.license.implement.UserLicenseQuestionGetService;
import com.comssa.api.question.service.rest.major.implement.UserMajorQuestionClassifiedGetService;
import com.comssa.api.question.service.view.HtmlTagService;
import com.comssa.persistence.question.common.domain.Question;
import com.comssa.persistence.question.common.domain.QuestionCategory;
import com.comssa.persistence.question.common.dto.response.ResponseClassifiedQuestionDto;
import com.comssa.persistence.question.license.domain.LicenseSession;
import com.comssa.persistence.question.major.user.dto.request.RequestGetQuestionByCategoryAndLevelDto;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class QuestionViewController {
	private final UserMajorQuestionClassifiedGetService userMajorQuestionClassifiedGetService;
	private final UserLicenseQuestionGetService userLicenseQuestionGetService;
	private final LicenseSessionService licenseSessionService;
	private final HtmlTagService htmlTagService;
	private final String baseUrl = "baseUrl";
	@Value("${resource.base-url}")
	private String resourceBaseUrl;

	@NotNull
	private static List<ResponseClassifiedQuestionDto> transformQuestion(
		Map<QuestionCategory,
			List<Question>> questions) {
		return questions.entrySet().stream()
			.map(entry -> ResponseClassifiedQuestionDto.from(entry.getKey(), entry.getValue()))
			.collect(Collectors.toList());
	}

	@AddLoginStatusAttributeToView
	@GetMapping("/question/major")
	public String getNormalQuestions(
		@RequestParam(required = false) List<String> levels,
		@RequestParam(required = false) List<String> categories,
		@RequestParam(required = false) Boolean multipleChoice,
		Model model) {

		Map<QuestionCategory, List<Question>> questions = getQuestions(levels, categories, multipleChoice);

		htmlTagService.forMajor(questions.keySet(), multipleChoice, model);

		model.addAttribute(baseUrl, resourceBaseUrl);
		model.addAttribute("questions", transformQuestion(questions));
		model.addAttribute("multipleChoice", multipleChoice);
		model.addAttribute("isMajorQuestion", true);

		if (multipleChoice) {
			return "question";
		}
		return "descriptiveQuestion";
	}

	@AddLoginStatusAttributeToView
	@GetMapping("/question/license/{sessionId}")
	public String getLicenseQuestionsBySession(
		@PathVariable Long sessionId,
		Model model
	) {
		LicenseSession licenseSession = licenseSessionService.getLicenseSessionById(sessionId);
		htmlTagService.forLicenseQuestion(licenseSession, model);
		Map<QuestionCategory, List<Question>> questions =
			userLicenseQuestionGetService.getClassifiedLicenseMultipleChoiceQuestion(sessionId);

		model.addAttribute(baseUrl, resourceBaseUrl);
		model.addAttribute("questions", transformQuestion(questions));
		model.addAttribute("multipleChoice", true);
		model.addAttribute("isMajorQuestion", false);
		return "question";
	}

	private Map<QuestionCategory, List<Question>> getQuestions(
		List<String> levels,
		List<String> categories,
		Boolean multipleChoice) {
		Map<QuestionCategory, List<Question>> questions;
		if (Boolean.TRUE.equals(multipleChoice)) {
			questions =
				userMajorQuestionClassifiedGetService.getApprovedClassifiedMajorMultipleChoiceQuestions(
					RequestGetQuestionByCategoryAndLevelDto.fromKorean(categories, levels)
				);
		} else {
			questions =
				userMajorQuestionClassifiedGetService.getApprovedClassifiedDescriptiveQuestions(
					RequestGetQuestionByCategoryAndLevelDto.fromKorean(categories, levels)
				);
		}
		return questions;
	}
}

