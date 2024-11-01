package com.server.computer_science.question.license_question.controller;


import com.server.computer_science.question.common.dto.response.ResponseQuestionDto;
import com.server.computer_science.question.license_question.dto.request.RequestMakeLicenseMultipleChoiceQuestionDto;
import com.server.computer_science.question.license_question.service.LicenseQuestionMakeService;
import com.server.computer_science.question.license_question.service.AdminLicenseQuestionUpdateService;
import com.server.computer_science.question.common.dto.request.RequestChangeContentDto;
import com.server.computer_science.question.common.dto.request.RequestChangeDescriptionDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/admin/question/license")
@Api(tags ={"자격증 문제 - ADMIN"})
@RequiredArgsConstructor
public class AdminLicenseQuestionController {
    private final LicenseQuestionMakeService licenseQuestionMakeService;
    private final AdminLicenseQuestionUpdateService adminLicenseQuestionUpdateService;

    @ApiOperation("단답형 문제 세션으로 생성")
    @PostMapping
    public ResponseEntity<List<ResponseQuestionDto>> makeLicenseQuestionOfSession(@RequestBody RequestMakeLicenseMultipleChoiceQuestionDto requestMakeLicenseMultipleChoiceQuestionDto){
        return ResponseEntity.ok(licenseQuestionMakeService.makeLicenseNormalQuestion(requestMakeLicenseMultipleChoiceQuestionDto));
    }

    @ApiOperation("단답형 문제 이미지 업로드")
    @PatchMapping("/{license-question-id}")
    public ResponseEntity<String>  updateLicenseQuestionWithImage(@PathVariable("license-question-id") Long licenseQuestionId, @RequestPart(value="image",required = true) MultipartFile file){
        return ResponseEntity.ok(licenseQuestionMakeService.updateLicenseQuestionWithImage(licenseQuestionId,file));

    }

    @ApiOperation("단답형 문제 상태 업데이트 - 문제 지문 업데이트")
    @PatchMapping(value = "/{id}/content")
    public ResponseEntity<ResponseQuestionDto> changeContent(
            @PathVariable("id")Long questionId,
            @RequestBody RequestChangeContentDto requestChangeContentDto) {
        return ResponseEntity.ok(ResponseQuestionDto.forAdmin(adminLicenseQuestionUpdateService.changeContent(questionId,requestChangeContentDto)));
    }

    @ApiOperation("단답형 문제 상태 업데이트 - 문제 해설 업데이트")
    @PatchMapping(value = "/{id}/description")
    public ResponseEntity<ResponseQuestionDto> changeDescription(
            @PathVariable("id")Long questionId,
            @RequestBody RequestChangeDescriptionDto changeDescriptionDto) {
        return ResponseEntity.ok(ResponseQuestionDto.forAdmin(adminLicenseQuestionUpdateService.changeDescription(questionId,changeDescriptionDto)));
    }

    @ApiOperation("단답형 문제 상태 업데이트 - 문제 삭제")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> changeDescription(
            @PathVariable("id")Long questionId) {
        adminLicenseQuestionUpdateService.deleteQuestion(questionId);
        return ResponseEntity.noContent().build();
    }

}
