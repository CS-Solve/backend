package com.server.computer_science.question.normal_question.controller;

import com.server.computer_science.question.common.QuestionCategory;
import com.server.computer_science.question.common.QuestionLevel;
import com.server.computer_science.question.normal_question.dto.request.RequestGetNormalQuestionsDto;
import com.server.computer_science.question.normal_question.dto.response.ResponseNormalQuestionDto;
import com.server.computer_science.question.normal_question.service.NormalQuestionGetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NormalProblemGetController {
    private final NormalQuestionGetService normalQuestionGetService;

    @GetMapping(value = "/question/normal")
    public List<ResponseNormalQuestionDto> getNormalQuestions(
            @RequestParam(required = false) List<QuestionCategory> categories,
            @RequestParam(required = false) List<QuestionLevel> levels
    ){
        return normalQuestionGetService.getNormalQuestions(RequestGetNormalQuestionsDto.of(categories, levels));

    }
}
