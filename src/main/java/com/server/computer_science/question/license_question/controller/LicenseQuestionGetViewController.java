package com.server.computer_science.question.license_question.controller;

import com.server.computer_science.question.license_question.service.LicenseQuestionGetService;
import com.server.computer_science.question.normal_question.user.dto.response.ResponseClassifiedNormalQuestionDto;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Api(tags ={"자격증 문제 - VIEW"})
@RequiredArgsConstructor
public class LicenseQuestionGetViewController {

    private final LicenseQuestionGetService licenseQuestionGetService;
    @Value("${resource.base-url}")
    private String resourceBaseUrl;
    private final String baseUrl = "baseUrl";

    @GetMapping("/question/license/{sessionId}")
    public String getLicenseQuestionsBySession(
            @PathVariable Long sessionId,
            Model model
    ){
        model.addAttribute(baseUrl,resourceBaseUrl);
        model.addAttribute("multipleChoice",true);
        model.addAttribute("questions",licenseQuestionGetService.getClassifiedLicenseNormalQuestion(sessionId));
        return "license-question";
    }

    @GetMapping("/question/license/{sessionId}/update")
    public String updateLicenseQuestions(
            @PathVariable Long sessionId,
            Model model
    ){
        model.addAttribute(baseUrl,resourceBaseUrl);
        model.addAttribute("classifiedQuestions",licenseQuestionGetService.getClassifiedLicenseNormalQuestion(sessionId));
        return "license-question-update";
    }

    /*
    임시 JSON 용
     */
    @GetMapping("/question/license/{sessionId}/rest")
    @ResponseBody
    public ResponseEntity<List<ResponseClassifiedNormalQuestionDto>> getLicenseQuestionsJson(@PathVariable Long sessionId){
        return ResponseEntity.ok(licenseQuestionGetService.getClassifiedLicenseNormalQuestion(sessionId));
    }

}
