package com.server.computerscience.question.license.controller;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.server.computerscience.login.aspect.AddLoginStatusAttribute;
import com.server.computerscience.question.common.dto.response.ResponseClassifiedMultipleQuestionDto;
import com.server.computerscience.question.license.domain.LicenseSession;
import com.server.computerscience.question.license.service.AdminLicenseQuestionGetService;
import com.server.computerscience.question.license.service.LicenseSessionService;
import com.server.computerscience.question.license.service.UserLicenseQuestionGetService;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@Controller
@Api(tags = {"자격증 문제 - VIEW"})
@RequiredArgsConstructor
public class LicenseQuestionGetViewController {

	private final UserLicenseQuestionGetService userLicenseQuestionGetService;
	private final AdminLicenseQuestionGetService adminLicenseQuestionGetService;
	private final LicenseSessionService licenseSessionService;
	private final String baseUrl = "baseUrl";
	@Value("${resource.base-url}")
	private String resourceBaseUrl;

	@AddLoginStatusAttribute
	@GetMapping("/question/license/{sessionId}")
	public String getLicenseQuestionsBySession(
		@PathVariable Long sessionId,
		Model model
	) {
		LicenseSession licenseSession = licenseSessionService.getLicenseSessionById(sessionId);

		String title =
			licenseSession.getLicenseCategory().getKorean() + " | " + "기출 문제 - " + licenseSession.getContent();
		model.addAttribute(baseUrl, resourceBaseUrl);
		model.addAttribute("title", title);
		model.addAttribute("description", "CS 전공과 관련된 자격증 기출 문제를 풀어볼 수 있습니다.");
		model.addAttribute("questionSession",
			licenseSession.getLicenseCategory().getKorean()
				+ " - "
				+ licenseSession.getContent()
				+ " / 복원 문제는 오류가 있을 수 있습니다.");
		model.addAttribute("questions",
			userLicenseQuestionGetService.getClassifiedLicenseMultipleChoiceQuestion(sessionId)
				.entrySet().stream()
				.map(entry -> ResponseClassifiedMultipleQuestionDto.forUser(entry.getKey(), entry.getValue()))
				.collect(Collectors.toList()));
		model.addAttribute("multipleChoice", true);
		return "question";
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
		model.addAttribute("folderName", "license-index");
		model.addAttribute("isLicenseQuestion", true);
		model.addAttribute("classifiedQuestions",
			adminLicenseQuestionGetService.getClassifiedLicenseMultipleChoiceQuestion(sessionId)
				.entrySet().stream()
				.map(entry -> ResponseClassifiedMultipleQuestionDto.forUser(entry.getKey(), entry.getValue()))
				.collect(Collectors.toList()));
		return "question-update";
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
