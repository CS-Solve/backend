package com.comssa.api.question.controller.view;

import com.comssa.api.question.service.license.implement.AdminLicenseQuestionGetService;
import com.comssa.persistence.question.common.dto.response.ResponseClassifiedQuestionDto;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.stream.Collectors;

@Controller
@Api(tags = {"자격증 문제 - VIEW"})
@RequiredArgsConstructor
public class LicenseQuestionGetViewController {


	private final AdminLicenseQuestionGetService adminLicenseQuestionGetService;

	private final String baseUrl = "baseUrl";
	@Value("${resource.base-url}")
	private String resourceBaseUrl;


	/*
	업데이트 화면
	 */
	@GetMapping("/admin/question/license/{sessionId}")
	public String updateLicenseQuestions(
		@PathVariable Long sessionId,
		Model model
	) {
		model.addAttribute(baseUrl, resourceBaseUrl);
		model.addAttribute("folderName", "license-index");
		model.addAttribute("isLicenseQuestion", true);
		model.addAttribute("classifiedQuestions",
			adminLicenseQuestionGetService.getClassifiedLicenseMultipleChoiceQuestion(sessionId)
				.entrySet().stream()
				.map(entry -> ResponseClassifiedQuestionDto.getQuestions(entry.getKey(), entry.getValue()))
				.collect(Collectors.toList()));
		return "question-update";
	}

}
