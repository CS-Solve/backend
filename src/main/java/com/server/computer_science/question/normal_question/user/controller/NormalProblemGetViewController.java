package com.server.computer_science.question.normal_question.user.controller;

import com.server.computer_science.question.normal_question.user.dto.request.RequestGetNormalQuestionsDto;
import com.server.computer_science.question.normal_question.common.service.NormalQuestionClassifiedGetService;
import com.server.computer_science.question.normal_question.user.service.QuestionSelectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class NormalProblemGetViewController {
    private final QuestionSelectorService questionSelectorService;
    @Qualifier("basicNormalQuestionClassifiedGetService")
    private final NormalQuestionClassifiedGetService normalQuestionClassifiedGetService;

    @GetMapping("/")
    public String showMainPage(Model model){
        List<String> categories = questionSelectorService.getCategories();
        List<String> levels = questionSelectorService.getLevels();
        model.addAttribute("categories", categories);
        model.addAttribute("levels", levels);
        return "index";
    }

    @GetMapping("/question/normal")
    public String getNormalQuestions(@RequestParam(required = false) List<String> levels,
                                     @RequestParam(required = false) List<String> categories,
                                     @RequestParam(required = false) Boolean multipleChoice,
                                     Model model) {
        if(multipleChoice){
            model.addAttribute("questions", normalQuestionClassifiedGetService.getClassifiedNormalQuestions(RequestGetNormalQuestionsDto.fromString(categories, levels)));
        }
        else{
            model.addAttribute("questions", normalQuestionClassifiedGetService.getClassifiedShortAnsweredNormalQuestions(RequestGetNormalQuestionsDto.fromString(categories, levels)));
        }
        model.addAttribute("multipleChoice", multipleChoice);
        return "normal-question"; // 문제를 보여줄 페이지의 이름
    }


}
