package com.server.computer_science.question.major_question.user.controller;

import com.server.computer_science.question.major_question.user.dto.request.RequestGetQuestionByCategoryAndLevelDto;
import com.server.computer_science.question.major_question.user.service.implement.BasicMajorQuestionClassifiedGetService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MajorQuestionGetViewController {

    private final BasicMajorQuestionClassifiedGetService basicMajorQuestionClassifiedGetService;

    @Value("${resource.base-url}")
    private String resourceBaseUrl;
    private final String baseUrl = "baseUrl";


    @GetMapping("/question/normal")
    public String getNormalQuestions(@RequestParam(required = false) List<String> levels,
                                     @RequestParam(required = false) List<String> categories,
                                     @RequestParam(required = false) Boolean multipleChoice,
                                     Model model) {
        if(multipleChoice){
            model.addAttribute("questions", basicMajorQuestionClassifiedGetService.getClassifiedMajorMultipleChoiceQuestions(RequestGetQuestionByCategoryAndLevelDto.fromString(categories, levels)));
        }
        else{
            model.addAttribute("questions", basicMajorQuestionClassifiedGetService.getClassifiedShortAnsweredMajorQuestions(RequestGetQuestionByCategoryAndLevelDto.fromString(categories, levels)));
        }
        model.addAttribute(baseUrl, resourceBaseUrl);
        model.addAttribute("multipleChoice", multipleChoice);
        return "major-question"; // 문제를 보여줄 페이지의 이름
    }


}
