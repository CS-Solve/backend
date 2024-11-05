//package com.server.computer_science.question.major_question.user.controller;
//
//import com.server.computer_science.question.major_question.user.dto.response.ResponseMajorQuestionClassCountDto;
//import com.server.computer_science.question.major_question.user.service.MajorQuestionCountService;
//import io.swagger.annotations.*;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RequestMapping("/api")
//@Api(tags ={"단답형 문제 조회"})
//@Controller
//@RequiredArgsConstructor
//public class MajorQuestionGetController {
//    private final MajorQuestionCountService majorQuestionCountService;
//
//
//    @ApiOperation("단답형 문제 분야-난이도별 문제 숫자 조회 API")
//    @GetMapping(value ="/question/normal/class-count")
//    @ResponseBody
//    public List<ResponseMajorQuestionClassCountDto> getNormalQuestionClasses(){
//        return majorQuestionCountService.getNormalQuestionCountByClass();
//    }
//}
