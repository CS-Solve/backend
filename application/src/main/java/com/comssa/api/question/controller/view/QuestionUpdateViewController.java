package com.comssa.api.question.controller.view;


import com.comssa.api.question.domain.QuestionType;
import com.comssa.api.question.service.rest.license.implement.AdminLicenseQuestionGetService;
import com.comssa.api.question.service.rest.major.AdminMajorQuestionClassifiedGetService;
import com.comssa.persistence.question.domain.common.Question;
import com.comssa.persistence.question.domain.common.QuestionCategory;
import com.comssa.persistence.question.dto.common.response.ResponseClassifiedQuestionDto;
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
public class QuestionUpdateViewController {

    private final String baseUrl = "baseUrl";
    private final AdminMajorQuestionClassifiedGetService adminMajorQuestionClassifiedGetService;
    private final AdminLicenseQuestionGetService adminLicenseQuestionGetService;

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
            questions = adminMajorQuestionClassifiedGetService
                    .getClassifiedAllMajorMultipleChoiceQuestions();
        } else if (questionType.equals(QuestionType.DESCRIPTIVE.getName())) {
            model.addAttribute("isMultipleChoice", false);
            questions = adminMajorQuestionClassifiedGetService
                    .getClassifiedAllMajorDescriptiveQuestions();
        }
        model.addAttribute("classifiedQuestions", questions
                .entrySet().stream()
                .map(entry -> ResponseClassifiedQuestionDto
                        .from(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList()));

        model.addAttribute(baseUrl, resourceBaseUrl);
        model.addAttribute("folderName", "index");
        model.addAttribute("isMajorQuestion", true);

        return "question-update";
    }

    @GetMapping("/question/license/{sessionId}")
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
                        .map(entry -> ResponseClassifiedQuestionDto.from(entry.getKey(), entry.getValue()))
                        .collect(Collectors.toList()));
        return "question-update";
    }
}
