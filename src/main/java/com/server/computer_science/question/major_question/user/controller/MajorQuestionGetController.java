package com.server.computer_science.question.major_question.user.controller;

import com.server.computer_science.question.major_question.user.dto.response.ResponseMajorQuestionClassCountDto;
import com.server.computer_science.question.major_question.user.service.MajorQuestionCountService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@Api(tags ={"단답형 문제 조회"})
@RestController
@RequiredArgsConstructor
public class MajorQuestionGetController {
    private final MajorQuestionCountService majorQuestionCountService;


    @ApiOperation("단답형 문제 분야-난이도별 문제 숫자 조회 API")
    @GetMapping(value ="/question/normal/class-count")
    public List<ResponseMajorQuestionClassCountDto> getNormalQuestionClasses(){
        return majorQuestionCountService.getNormalQuestionCountByClass();
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
