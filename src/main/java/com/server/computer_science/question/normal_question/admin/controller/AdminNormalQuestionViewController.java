package com.server.computer_science.question.normal_question.admin.controller;


import com.server.computer_science.question.normal_question.admin.service.implement.BasicAdminNormalQuestionClassifiedGetService;
import com.server.computer_science.question.normal_question.admin.service.implement.BasicAdminNormalQuestionUpdateService;
import com.server.computer_science.question.normal_question.common.domain.NormalQuestion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminNormalQuestionViewController {
    private final BasicAdminNormalQuestionClassifiedGetService basicAdminNormalQuestionClassifiedGetService;

    @GetMapping("/question/update")
    public String updateQuestionPage(Model model){
        model.addAttribute("classifiedQuestions", basicAdminNormalQuestionClassifiedGetService.getClassifiedAllNormalQuestions());
        return "normal-question-update";
    }
}
