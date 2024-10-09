package com.server.computer_science.question.normal_question.admin.controller;


import com.server.computer_science.question.normal_question.admin.service.AdminNormalQuestionMakeService;
import com.server.computer_science.question.normal_question.admin.service.AdminNormalQuestionUpdateService;
import com.server.computer_science.question.normal_question.common.exception.DuplicateQuestionException;
import com.server.computer_science.question.normal_question.user.dto.request.RequestMakeNormalQuestionDto;
import com.server.computer_science.question.normal_question.user.dto.response.ResponseClassifiedNormalQuestionDto;
import com.server.computer_science.question.normal_question.common.service.NormalQuestionClassifiedGetService;
import com.server.computer_science.question.normal_question.user.dto.response.ResponseNormalQuestionDto;
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
    @Qualifier("basicAdminNormalQuestionClassifiedGetService")
    private final NormalQuestionClassifiedGetService normalQuestionClassifiedGetService;
    private final AdminNormalQuestionMakeService adminNormalQuestionMakeService;
    private final AdminNormalQuestionUpdateService adminNormalQuestionUpdateService;

    @ApiOperation("단답형 문제 조회")
    @GetMapping("/question/normal")
    public List<ResponseClassifiedNormalQuestionDto> getAllNormalQuestionForAdmin(){
        return normalQuestionClassifiedGetService.getClassifiedAllNormalQuestions();
    }

    @ApiOperation("단답형 문제 리스트로 생성")
    @PostMapping(value = "/question/normal-multi")
    public ResponseEntity<List<ResponseNormalQuestionDto>> MakeMultiNormalQuestion(@RequestBody List<RequestMakeNormalQuestionDto> requestMakeNormalQuestionDtos){
        return ResponseEntity.ok(adminNormalQuestionMakeService.makeNormalQuestions(requestMakeNormalQuestionDtos));
    }

    @ApiOperation("단답형 문제 단일로 생성")
    @PostMapping(value = "/question/normal-single")
    public ResponseEntity<ResponseNormalQuestionDto> MakeSingleNormalQuestion(@RequestBody RequestMakeNormalQuestionDto requestMakeNormalQuestionDto) throws DuplicateQuestionException {
        return ResponseEntity.ok(adminNormalQuestionMakeService.makeNormalQuestion(requestMakeNormalQuestionDto));
    }

    @ApiOperation("단답형 문제 상태 업데이트 - Approve 토글")
    @PatchMapping(value = "/question/normal/toggle-approve/{id}")
    public ResponseEntity<ResponseNormalQuestionDto> toggleApproveNormalQuestion(@PathVariable("id")Long questionId) {
        return ResponseEntity.ok(adminNormalQuestionUpdateService.toggleApproveNormalQuestion(questionId));
    }
    @ApiOperation("단답형 문제 상태 업데이트 - 단답형-주관식 토글")
    @PatchMapping(value = "/question/normal/toggle-multiple/{id}")
    public ResponseEntity<ResponseNormalQuestionDto> toggleCanBeShortAnswered(@PathVariable("id")Long questionId) {
        return ResponseEntity.ok(adminNormalQuestionUpdateService.toggleCanBeShortAnswered(questionId));
    }


    @ExceptionHandler(DuplicateQuestionException.class)
    public ResponseEntity<String> handleException(DuplicateQuestionException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }
}
