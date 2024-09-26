package com.server.computer_science.question.normal_question.controller;

import com.server.computer_science.question.common.QuestionCategory;
import com.server.computer_science.question.common.QuestionLevel;
import com.server.computer_science.question.normal_question.dto.request.RequestGetNormalQuestionsDto;
import com.server.computer_science.question.normal_question.dto.response.ResponseNormalQuestionClassDto;
import com.server.computer_science.question.normal_question.dto.response.ResponseNormalQuestionDto;
import com.server.computer_science.question.normal_question.service.NormalQuestionGetService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NormalProblemGetController {
    private final NormalQuestionGetService normalQuestionGetService;

    @GetMapping(value = "/question/normal")
    public List<ResponseNormalQuestionDto> getNormalQuestions(
            @Parameter(name = "categories", in = ParameterIn.QUERY,
                    description = "중복 가능",
                    example = "DATABASE,\n" +
                            "    NETWORK,\n" +
                            "    OPERATING_SYSTEM,\n" +
                            "    COMPUTER_ARCHITECTURE,\n" +
                            "    DATA_STRUCTURE,\n" +
                            "    ALGORITHM,\n" +
                            "    OOP,\n" +
                            "    DESIGN_PATTERN")
            @RequestParam(required = false) List<QuestionCategory> categories,
            @Parameter(name = "levels", in = ParameterIn.QUERY,
                    description = "중복 가능",
                    example = "LOW,MEDIUM,HIGH")
            @RequestParam(required = false) List<QuestionLevel> levels
    ){
        return normalQuestionGetService.getNormalQuestions(RequestGetNormalQuestionsDto.of(categories, levels));
    }

    @GetMapping(value ="/question/class")
    public List<ResponseNormalQuestionClassDto> getNormalQuestionClasses(){
        return normalQuestionGetService.getNormalQuestionByClass();
    }
}
