package com.server.computer_science.question.normal_question.controller;

import com.server.computer_science.question.normal_question.service.QuestionSelectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class NormalProblemGetViewController {
    private final QuestionSelectorService questionSelectorService;

    @GetMapping("/")
    public String showMainPage(Model model){
        List<String> categories = questionSelectorService.getCategories();
        List<String> levels = questionSelectorService.getLevels();
        model.addAttribute("categories", categories);
        model.addAttribute("levels", levels);
        return "index";
    }
}
