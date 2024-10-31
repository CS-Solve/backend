package com.server.computer_science.question.license_question.controller;


import com.server.computer_science.question.license_question.dto.request.RequestMakeNormalLicenseQuestionDto;
import com.server.computer_science.question.license_question.service.LicenseQuestionMakeService;
import com.server.computer_science.question.common.dto.response.ResponseNormalQuestionDto;
import com.server.computer_science.question.normal_question.admin.dto.RequestChangeContentDto;
import com.server.computer_science.question.normal_question.admin.dto.RequestChangeDescriptionDto;
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

    @ApiOperation("단답형 문제 세션으로 생성")
    @PostMapping
    public ResponseEntity<List<ResponseNormalQuestionDto>> makeLicenseQuestionOfSession(@RequestBody RequestMakeNormalLicenseQuestionDto requestMakeNormalLicenseQuestionDto){
        return ResponseEntity.ok(licenseQuestionMakeService.makeLicenseNormalQuestion(requestMakeNormalLicenseQuestionDto));
    }

    @ApiOperation("단답형 문제 이미지 업로드")
    @PatchMapping("/{license-question-id}")
    public ResponseEntity<String>  updateLicenseQuestionWithImage(@PathVariable("license-question-id") Long licenseQuestionId, @RequestPart(value="image",required = true) MultipartFile file){
        return ResponseEntity.ok(licenseQuestionMakeService.updateLicenseQuestionWithImage(licenseQuestionId,file));

    }

    @ApiOperation("단답형 문제 상태 업데이트 - 문제 지문 업데이트")
    @PatchMapping(value = "/{id}/content")
    public ResponseEntity<ResponseNormalQuestionDto> changeContent(
            @PathVariable("id")Long questionId,
            @RequestBody RequestChangeContentDto requestChangeContentDto) {
        return ResponseEntity.ok(licenseQuestionMakeService.changeContent(questionId, requestChangeContentDto));
    }

    @ApiOperation("단답형 문제 상태 업데이트 - 문제 해설 업데이트")
    @PatchMapping(value = "/{id}/description")
    public ResponseEntity<ResponseNormalQuestionDto> changeDescription(
            @PathVariable("id")Long questionId,
            @RequestBody RequestChangeDescriptionDto changeDescriptionDto) {
        return ResponseEntity.ok(licenseQuestionMakeService.changeDescription(questionId, changeDescriptionDto));
    }

    @ApiOperation("단답형 문제 상태 업데이트 - 문제 삭제")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> changeDescription(
            @PathVariable("id")Long questionId) {
        licenseQuestionMakeService.deleteLicenseQuestion(questionId);
        return ResponseEntity.noContent().build();
    }


}
