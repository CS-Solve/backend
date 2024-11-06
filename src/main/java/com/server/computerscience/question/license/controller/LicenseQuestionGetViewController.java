package com.server.computerscience.question.license.controller;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.server.computerscience.login.aspect.AddLoginStatusAttribute;
import com.server.computerscience.question.common.dto.response.ResponseClassifiedMultipleQuestionDto;
import com.server.computerscience.question.license.dto.response.ResponseLicenseSessionDto;
import com.server.computerscience.question.license.service.LicenseQuestionGetService;
import com.server.computerscience.question.license.service.LicenseSessionService;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@Controller
@Api(tags = {"자격증 문제 - VIEW"})
@RequiredArgsConstructor
public class LicenseQuestionGetViewController {

	private final LicenseQuestionGetService licenseQuestionGetService;
	private final LicenseSessionService licenseSessionService;
	@Value("${resource.base-url}")
	private String resourceBaseUrl;
	private final String baseUrl = "baseUrl";

	@AddLoginStatusAttribute
	@GetMapping("/question/license/{sessionId}")
	public String getLicenseQuestionsBySession(
		@PathVariable Long sessionId,
		Model model
	) {
		model.addAttribute(baseUrl, resourceBaseUrl);
		model.addAttribute("multipleChoice", true);
		model.addAttribute("licenseSession",
			ResponseLicenseSessionDto.from(licenseSessionService.getLicenseSessionById(sessionId)));
		model.addAttribute("questions", licenseQuestionGetService.getClassifiedLicenseMultipleChoiceQuestion(sessionId)
			.entrySet().stream()
			.map(entry -> ResponseClassifiedMultipleQuestionDto.forUser(entry.getKey(), entry.getValue()))
			.collect(Collectors.toList()));

		return "license-question";
	}

	/*
	업데이트 화면
	 */
	@GetMapping("/admin/question/license/update/{sessionId}")
	public String updateLicenseQuestions(
		@PathVariable Long sessionId,
		Model model
	) {
		model.addAttribute(baseUrl, resourceBaseUrl);
		model.addAttribute("classifiedQuestions",
			licenseQuestionGetService.getClassifiedLicenseMultipleChoiceQuestion(sessionId)
				.entrySet().stream()
				.map(entry -> ResponseClassifiedMultipleQuestionDto.forUser(entry.getKey(), entry.getValue()))
				.collect(Collectors.toList()));
		return "license-question-update";
	}

	/**
	 * 임시 JSON 용
	 */
	//    @GetMapping("/question/license/{sessionId}/rest")
	//    @ResponseBody
	//    public ResponseEntity<List<ResponseClassifiedMultipleQuestionDto>> getLicenseQuestionsJson(
	//    @PathVariable Long sessionId){
	//        return ResponseEntity.ok(licenseQuestionGetService.getClassifiedLicenseNormalQuestion(sessionId));
	//    }

}
