package com.server.computer_science.question.normal_question.controller;

import com.server.computer_science.question.common.QuestionCategory;
import com.server.computer_science.question.common.QuestionLevel;
import com.server.computer_science.question.normal_question.dto.request.RequestGetNormalQuestionsDto;
import com.server.computer_science.question.normal_question.dto.response.ResponseNormalQuestionClassCountDto;
import com.server.computer_science.question.normal_question.dto.response.ResponseNormalQuestionDto;
import com.server.computer_science.question.normal_question.service.NormalQuestionCountService;
import com.server.computer_science.question.normal_question.service.UserNormalQuestionGetService;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@Api(tags ={"단답형 문제 조회"})
@RestController
@RequiredArgsConstructor
public class NormalProblemGetController {
    private final UserNormalQuestionGetService userNormalQuestionGetService;
    private final NormalQuestionCountService normalQuestionCountService;


    @ApiOperation("단답형 문제 분야-난이도별 문제 숫자 조회 API")
    @GetMapping(value ="/question/class")
    public List<ResponseNormalQuestionClassCountDto> getNormalQuestionClasses(){
        return normalQuestionCountService.getNormalQuestionCountByClass();
    }

//        @ApiOperation("단답형 문제 조회 API")
//    @GetMapping(value = "/question/normal")
//    public List<ResponseNormalQuestionDto> getNormalQuestions(
//            @Parameter(name = "categories", in = ParameterIn.QUERY,
//                    description = "중복 가능",
//                    example = "DATABASE,\n" +
//                            "    NETWORK,\n" +
//                            "    OPERATING_SYSTEM,\n" +
//                            "    COMPUTER_ARCHITECTURE,\n" +
//                            "    DATA_STRUCTURE,\n" +
//                            "    ALGORITHM,\n" +
//                            "    OOP,\n" +
//                            "    DESIGN_PATTERN")
//            @RequestParam(required = false) List<QuestionCategory> categories,
//            @Parameter(name = "levels", in = ParameterIn.QUERY,
//                    description = "중복 가능",
//                    example = "LOW,MEDIUM,HIGH")
//            @RequestParam(required = false) List<QuestionLevel> levels
//    ){
//        return userNormalQuestionGetService.getNormalQuestions(RequestGetNormalQuestionsDto.of(categories, levels));
//    }
}
