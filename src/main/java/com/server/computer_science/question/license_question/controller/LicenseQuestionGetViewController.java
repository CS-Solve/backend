package com.server.computer_science.question.license_question.controller;

import com.server.computer_science.question.license_question.service.LicenseQuestionGetService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class LicenseQuestionGetViewController {

    private final LicenseQuestionGetService licenseQuestionGetService;
    @Value("${resource.base-url}")
    private String resourceBaseUrl;
    private final String baseUrl = "baseUrl";

    @GetMapping("/question/license/engineer/{sessionId}")
    public String getLicenseEngineerQuestions(
            @PathVariable Long sessionId,
            Model model
    ){
        model.addAttribute(baseUrl,resourceBaseUrl);
        model.addAttribute("multipleChoice",true);
        model.addAttribute("questions",licenseQuestionGetService.getClassifiedLicenseNormalQuestion(sessionId));
        return "license-question";
    }
}
