package com.server.computer_science.question.license_question.controller;

import com.server.computer_science.question.license_question.dto.response.ResponseLicenseSessionDto;
import com.server.computer_science.question.license_question.service.LicenseQuestionGetService;
import com.server.computer_science.question.license_question.service.LicenseSessionService;
import com.server.computer_science.question.common.dto.response.ResponseClassifiedMultipleQuestionDto;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.stream.Collectors;

@Controller
@Api(tags ={"자격증 문제 - VIEW"})
@RequiredArgsConstructor
public class LicenseQuestionGetViewController {

    private final LicenseQuestionGetService licenseQuestionGetService;
    private final LicenseSessionService licenseSessionService;
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
        model.addAttribute("licenseSession", ResponseLicenseSessionDto.from(licenseSessionService.getLicenseSessionById(sessionId)));
        model.addAttribute("questions",licenseQuestionGetService.getClassifiedLicenseMultipleChoiceQuestion(sessionId)
                .entrySet().stream()
                .map(entry-> ResponseClassifiedMultipleQuestionDto.forUser(entry.getKey(),entry.getValue()))
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
    ){
        model.addAttribute(baseUrl,resourceBaseUrl);
        model.addAttribute("classifiedQuestions",licenseQuestionGetService.getClassifiedLicenseMultipleChoiceQuestion(sessionId)
                .entrySet().stream()
                .map(entry-> ResponseClassifiedMultipleQuestionDto.forUser(entry.getKey(),entry.getValue()))
                .collect(Collectors.toList()));
        return "license-question-update";
    }


    /*
    임시 JSON 용
     */
//    @GetMapping("/question/license/{sessionId}/rest")
//    @ResponseBody
//    public ResponseEntity<List<ResponseClassifiedMultipleQuestionDto>> getLicenseQuestionsJson(@PathVariable Long sessionId){
//        return ResponseEntity.ok(licenseQuestionGetService.getClassifiedLicenseNormalQuestion(sessionId));
//    }

}
