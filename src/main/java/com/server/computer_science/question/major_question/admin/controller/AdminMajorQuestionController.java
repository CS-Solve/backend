package com.server.computer_science.question.major_question.admin.controller;


import com.server.computer_science.question.common.dto.request.RequestChangeDescriptionDto;
import com.server.computer_science.question.common.dto.request.RequestChangeContentDto;
import com.server.computer_science.question.major_question.admin.dto.ResponseMajorQuestionForAdminDto;
import com.server.computer_science.question.major_question.admin.service.AdminMajorQuestionClassifiedGetService;
import com.server.computer_science.question.major_question.admin.service.AdminMajorQuestionMakeService;
import com.server.computer_science.question.major_question.admin.service.implement.AdminMajorMultipleChoiceQuestionUpdateService;
import com.server.computer_science.question.major_question.common.exception.DuplicateQuestionException;
import com.server.computer_science.question.major_question.admin.dto.RequestMakeMultipleChoiceQuestionDto;
import com.server.computer_science.question.major_question.user.service.MajorQuestionClassifiedGetService;
import com.server.computer_science.question.common.dto.response.ResponseClassifiedMultipleQuestionDto;
import com.server.computer_science.question.common.dto.response.ResponseQuestionDto;
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
public class AdminMajorQuestionController {
    private final AdminMajorQuestionClassifiedGetService adminMajorQuestionClassifiedGetService;
    private final AdminMajorQuestionMakeService adminMajorQuestionMakeService;
    private final AdminMajorMultipleChoiceQuestionUpdateService adminMajorMultipleChoiceQuestionUpdateService;

    @ApiOperation("단답형 문제 조회")
    @GetMapping("/question/major")
    public List<ResponseClassifiedMultipleQuestionDto> getAllNormalQuestionForAdmin(){
        return adminMajorQuestionClassifiedGetService.getClassifiedAllMajorQuestions();
    }

    @ApiOperation("단답형 문제 리스트로 생성")
    @PostMapping(value = "/question/major-multi")
    public ResponseEntity<List<ResponseQuestionDto>> MakeMultiNormalQuestion(@RequestBody List<RequestMakeMultipleChoiceQuestionDto> requestMakeMultipleChoiceQuestionDtos){
        return ResponseEntity.ok(adminMajorQuestionMakeService.makeMultipleChoiceQuestions(requestMakeMultipleChoiceQuestionDtos));
    }

    @ApiOperation("단답형 문제 단일로 생성")
    @PostMapping(value = "/question/major-single")
    public ResponseEntity<ResponseQuestionDto> MakeSingleNormalQuestion(@RequestBody RequestMakeMultipleChoiceQuestionDto requestMakeMultipleChoiceQuestionDto) throws DuplicateQuestionException {
        return ResponseEntity.ok(adminMajorQuestionMakeService.makeMultipleChoiceQuestion(requestMakeMultipleChoiceQuestionDto));
    }

    @ApiOperation("단답형 문제 상태 업데이트 - Approve 토글")
    @PatchMapping(value = "/question/major/{id}/toggle-approve")
    public ResponseEntity<ResponseQuestionDto> toggleApproveNormalQuestion(@PathVariable("id")Long questionId) {
        return ResponseEntity.ok(ResponseMajorQuestionForAdminDto.forAdmin(adminMajorMultipleChoiceQuestionUpdateService.toggleApproveNormalQuestion(questionId)));
    }
    @ApiOperation("단답형 문제 상태 업데이트 - 단답형-주관식 토글")
    @PatchMapping(value = "/question/major/{id}/toggle-multiple")
    public ResponseEntity<ResponseQuestionDto> toggleCanBeShortAnswered(@PathVariable("id")Long questionId) {
        return ResponseEntity.ok(ResponseMajorQuestionForAdminDto.forAdmin(adminMajorMultipleChoiceQuestionUpdateService.toggleCanBeShortAnswered(questionId)));
    }
    @ApiOperation("단답형 문제 상태 업데이트 - 문제 업데이트")
    @PatchMapping(value = "/question/major/{id}/content")
    public ResponseEntity<ResponseQuestionDto> changeQuestion(
            @PathVariable("id")Long questionId,
            @RequestBody RequestChangeContentDto requestChangeContentDto) {
        return ResponseEntity.ok(ResponseMajorQuestionForAdminDto.forAdmin(adminMajorMultipleChoiceQuestionUpdateService.changeContent(questionId, requestChangeContentDto)));
    }

    @ApiOperation("단답형 문제 상태 업데이트 - 해설 업데이트")
    @PatchMapping(value = "/question/major/{id}/description")
    public ResponseEntity<ResponseQuestionDto> changeDescription(
            @PathVariable("id")Long questionId,
            @RequestBody RequestChangeDescriptionDto requestChangeDescriptionDto
            ) {
        return ResponseEntity.ok(ResponseMajorQuestionForAdminDto.forAdmin(adminMajorMultipleChoiceQuestionUpdateService.changeDescription(questionId,requestChangeDescriptionDto)));
    }

    @ApiOperation("단답형 문제 삭제")
    @DeleteMapping(value ="/question/major/{id}")
    public ResponseEntity<Void> deleteNormalQuestion(@PathVariable("id")Long questionId) {
        adminMajorMultipleChoiceQuestionUpdateService.deleteQuestion(questionId);
        return ResponseEntity.noContent().build();
    }


    @ExceptionHandler(DuplicateQuestionException.class)
    public ResponseEntity<String> handleException(DuplicateQuestionException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }
}