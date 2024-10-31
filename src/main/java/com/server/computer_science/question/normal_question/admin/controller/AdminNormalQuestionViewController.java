package com.server.computer_science.question.normal_question.admin.controller;


import com.server.computer_science.question.normal_question.admin.service.implement.BasicAdminNormalQuestionClassifiedGetService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminNormalQuestionViewController {

    @Value("${resource.base-url}")
    private String resourceBaseUrl;
    private final String baseUrl = "baseUrl";
    private final BasicAdminNormalQuestionClassifiedGetService basicAdminNormalQuestionClassifiedGetService;

    @GetMapping("/question/update")
    public String updateQuestionPage(Model model){
        model.addAttribute("classifiedQuestions", basicAdminNormalQuestionClassifiedGetService.getClassifiedAllNormalQuestions());
        model.addAttribute(baseUrl, resourceBaseUrl);
        return "normal-question-update";
    }
}
