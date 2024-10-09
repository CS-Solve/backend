package com.server.computer_science.question.normal_question.admin.controller;


import com.server.computer_science.question.normal_question.admin.service.AdminNormalQuestionMakeService;
import com.server.computer_science.question.normal_question.common.exception.DuplicateQuestionException;
import com.server.computer_science.question.normal_question.user.dto.request.RequestMakeNormalQuestionDto;
import com.server.computer_science.question.normal_question.user.dto.response.ResponseClassifiedNormalQuestionDto;
import com.server.computer_science.question.normal_question.common.service.NormalQuestionClassifiedGetService;
import com.server.computer_science.question.normal_question.user.dto.response.ResponseNormalQuestionDto;
import com.server.computer_science.question.normal_question.user.service.NormalQuestionCountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(tags ={"단답형 문제 - ADMIN"})
@RequestMapping("/admin")
public class AdminNormalQuestionController {
    @Qualifier("BasicAdminNormalQuestionClassifiedGetService")
    private final NormalQuestionClassifiedGetService normalQuestionClassifiedGetService;
    private final AdminNormalQuestionMakeService adminNormalQuestionMakeService;

    @ApiOperation("단답형 문제 조회 - ADMIN")
    @GetMapping("/question/normal")
    public List<ResponseClassifiedNormalQuestionDto> getAllNormalQuestionForAdmin(){
        return normalQuestionClassifiedGetService.getClassifiedAllNormalQuestions();
    }

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
