package com.server.computer_science.question.normal_question.controller;


import com.server.computer_science.question.normal_question.dto.request.RequestMakeNormalQuestionDto;

import com.server.computer_science.question.normal_question.dto.response.ResponseNormalQuestionDto;
import com.server.computer_science.question.normal_question.exception.DuplicateQuestionException;
import com.server.computer_science.question.admin.service.AdminNormalQuestionMakeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags ={"단답형 문제 생성"})
@RestController
@RequiredArgsConstructor
public class NormalProblemMakeController {
    private final AdminNormalQuestionMakeService adminNormalQuestionMakeService;

    @ApiOperation("단답형 문제 리스트로 생성 API")
    @PostMapping(value = "/question/normal-multi")
    public ResponseEntity<List<ResponseNormalQuestionDto>> MakeMultiNormalQuestion(@RequestBody List<RequestMakeNormalQuestionDto> requestMakeNormalQuestionDtos){
        return ResponseEntity.ok(adminNormalQuestionMakeService.makeNormalQuestions(requestMakeNormalQuestionDtos));
    }

    @ApiOperation("단답형 문제 단일 생성 API")
    @PostMapping(value = "/question/normal-single")
    public ResponseEntity<ResponseNormalQuestionDto> MakeSingleNormalQuestion(@RequestBody RequestMakeNormalQuestionDto requestMakeNormalQuestionDto) throws DuplicateQuestionException {

        return ResponseEntity.ok(adminNormalQuestionMakeService.makeNormalQuestion(requestMakeNormalQuestionDto));
    }

    @ExceptionHandler(DuplicateQuestionException.class)
    public ResponseEntity<String> handleException(DuplicateQuestionException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }
}
