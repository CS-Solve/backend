package com.server.computer_science.question.license_question.controller;


import com.server.computer_science.question.license_question.dto.request.RequestMakeNormalLicenseQuestionDto;
import com.server.computer_science.question.license_question.service.LicenseQuestionMakeService;
import com.server.computer_science.question.common.dto.response.ResponseNormalQuestionDto;
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
}