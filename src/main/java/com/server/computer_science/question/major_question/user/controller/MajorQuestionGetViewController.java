package com.server.computer_science.question.major_question.user.controller;

import com.server.computer_science.question.common.dto.response.ResponseClassifiedMultipleQuestionDto;
import com.server.computer_science.question.major_question.user.dto.request.RequestGetQuestionByCategoryAndLevelDto;
import com.server.computer_science.question.major_question.user.service.implement.BasicMajorQuestionClassifiedGetService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class MajorQuestionGetViewController {

    private final BasicMajorQuestionClassifiedGetService basicMajorQuestionClassifiedGetService;

    @Value("${resource.base-url}")
    private String resourceBaseUrl;
    private final String baseUrl = "baseUrl";


    @GetMapping("/question/major")
    public String getNormalQuestions(@RequestParam(required = false) List<String> levels,
                                     @RequestParam(required = false) List<String> categories,
                                     @RequestParam(required = false) Boolean multipleChoice,
                                     Model model) {
        if(multipleChoice){
            model.addAttribute("questions", basicMajorQuestionClassifiedGetService
                    .getApprovedClassifiedMajorMultipleChoiceQuestions(RequestGetQuestionByCategoryAndLevelDto.fromKorean(categories, levels))
                    .entrySet().stream()
                    .map(entry-> ResponseClassifiedMultipleQuestionDto.forUser(entry.getKey(),entry.getValue()))
                    .collect(Collectors.toList()));
        }
        else{
            model.addAttribute("questions", basicMajorQuestionClassifiedGetService
                    .getApprovedClassifiedShortAnsweredMajorQuestions(RequestGetQuestionByCategoryAndLevelDto.fromKorean(categories, levels))
                    .entrySet().stream()
                    .map(entry-> ResponseClassifiedMultipleQuestionDto.forUser(entry.getKey(),entry.getValue()))
                    .collect(Collectors.toList()));
        }
        model.addAttribute(baseUrl, resourceBaseUrl);
        model.addAttribute("multipleChoice", multipleChoice);
        return "major-question"; // 문제를 보여줄 페이지의 이름
    }
}
