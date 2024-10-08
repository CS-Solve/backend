package com.server.computer_science.question.normal_question.controller;

import com.server.computer_science.question.common.QuestionCategory;
import com.server.computer_science.question.common.QuestionLevel;
import com.server.computer_science.question.normal_question.dto.request.RequestGetNormalQuestionsDto;
import com.server.computer_science.question.normal_question.dto.response.ResponseNormalQuestionDto;
import com.server.computer_science.question.normal_question.service.NormalQuestionGetService;
import com.server.computer_science.question.normal_question.service.QuestionSelectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class NormalProblemGetViewController {
    private final QuestionSelectorService questionSelectorService;
    private final NormalQuestionGetService normalQuestionGetService;

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
                                     @RequestParam(required = false) Boolean MultipleChoice,
                                     Model model) {
        model.addAttribute("questions", normalQuestionGetService.getClassifiedNormalQuestions(RequestGetNormalQuestionsDto.fromString(categories, levels)));
        model.addAttribute("multipleChoice",MultipleChoice);
        return "normal-question"; // 문제를 보여줄 페이지의 이름
    }


}
