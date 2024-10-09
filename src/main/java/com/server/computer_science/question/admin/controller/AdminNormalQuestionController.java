package com.server.computer_science.question.admin.controller;


import com.server.computer_science.question.admin.service.AdminNormalQuestionGetService;
import com.server.computer_science.question.normal_question.dto.response.ResponseClassifiedNormalQuestionDto;
import com.server.computer_science.question.normal_question.dto.response.ResponseNormalQuestionDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(tags ={"단답형 문제 - ADMIN"})
@RequestMapping("/admin")
public class AdminNormalQuestionController {
    private final AdminNormalQuestionGetService adminNormalQuestionGetService;

    @ApiOperation("단답형 문제 조회 - ADMIN")
    @GetMapping("/question/normal")
    public List<ResponseClassifiedNormalQuestionDto> getAllNormalQuestionForAdmin(){
        return adminNormalQuestionGetService.getAllNormalQuestionsClassifiedForAdmin();
    }
}
