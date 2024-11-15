package com.study.computerscience.question.license.controller;

import com.study.computerscience.login.aspect.AddLoginStatusAttribute;
import com.study.computerscience.question.common.dto.response.ResponseClassifiedMultipleQuestionDto;
import com.study.computerscience.question.license.domain.LicenseCategory;
import com.study.computerscience.question.license.domain.LicenseSession;
import com.study.computerscience.question.license.service.AdminLicenseQuestionGetService;
import com.study.computerscience.question.license.service.LicenseSessionService;
import com.study.computerscience.question.license.service.UserLicenseQuestionGetService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Arrays;
import java.util.stream.Collectors;

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
		model.addAttribute("description", Arrays.stream(LicenseCategory.values())
			.map(LicenseCategory::getKorean)
			.collect(Collectors.joining(", "))
			+ " 등 컴퓨터 사이언스(CS) 자격증 기출 문제를 풀어보세요. 기출 문제 풀이를 통해 자격증 대비가 가능합니다.");
		model.addAttribute("questionSession",
			licenseSession.getLicenseCategory().getKorean()
				+ " - "
				+ licenseSession.getContent()
				+ " / 실제 시험은 더 어려울 수 있으니, 문제의 중심 개념 (정답 선택지) 위주로 학습 추천");
		model.addAttribute("questions",
			userLicenseQuestionGetService.getClassifiedLicenseMultipleChoiceQuestion(sessionId)
				.entrySet().stream()
				.map(entry -> ResponseClassifiedMultipleQuestionDto.forUser(entry.getKey(), entry.getValue()))
				.collect(Collectors.toList()));
		model.addAttribute("multipleChoice", true);
		model.addAttribute("isMajorQuestion", false);
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
