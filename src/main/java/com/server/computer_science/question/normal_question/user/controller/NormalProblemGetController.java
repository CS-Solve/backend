package com.server.computer_science.question.normal_question.user.controller;

import com.server.computer_science.question.normal_question.user.dto.response.ResponseNormalQuestionClassCountDto;
import com.server.computer_science.question.normal_question.user.service.NormalQuestionCountService;
import com.server.computer_science.question.normal_question.common.service.NormalQuestionClassifiedGetService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@Api(tags ={"단답형 문제 조회"})
@RestController
@RequiredArgsConstructor
public class NormalProblemGetController {
    private final NormalQuestionCountService normalQuestionCountService;


    @ApiOperation("단답형 문제 분야-난이도별 문제 숫자 조회 API")
    @GetMapping(value ="/question/normal/class-count")
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
