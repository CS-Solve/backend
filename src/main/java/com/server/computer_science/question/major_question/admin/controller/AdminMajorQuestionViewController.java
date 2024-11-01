package com.server.computer_science.question.major_question.admin.controller;


import com.server.computer_science.question.major_question.admin.service.implement.BasicAdminMajorQuestionClassifiedGetService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminMajorQuestionViewController {

    @Value("${resource.base-url}")
    private String resourceBaseUrl;
    private final String baseUrl = "baseUrl";
    private final BasicAdminMajorQuestionClassifiedGetService basicAdminNormalQuestionClassifiedGetService;

    @GetMapping("/question/update")
    public String updateQuestionPage(Model model){
        model.addAttribute("classifiedQuestions", basicAdminNormalQuestionClassifiedGetService.getClassifiedAllMajorQuestions());
        model.addAttribute(baseUrl, resourceBaseUrl);
        return "normal-question-update";
    }
}
