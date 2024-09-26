package com.server.computer_science.question.normal_question.controller;


import com.server.computer_science.question.normal_question.dto.request.RequestMakeNormalQuestionDto;

import com.server.computer_science.question.normal_question.dto.response.ResponseNormalQuestionDto;
import com.server.computer_science.question.normal_question.service.NormalQuestionMakeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags ={"단답형 문제 생성"})
@RestController
@RequiredArgsConstructor
public class NormalProblemMakeController {
    private final NormalQuestionMakeService normalQuestionMakeService;

    @ApiOperation("단답형 문제 생성 API")
    @PostMapping(value = "/question/normal")
    public List<ResponseNormalQuestionDto> makeNormalQuestion(@RequestBody List<RequestMakeNormalQuestionDto> requestMakeNormalQuestionDtos){
        return normalQuestionMakeService.makeNormalQuestion(requestMakeNormalQuestionDtos);
    }


}
