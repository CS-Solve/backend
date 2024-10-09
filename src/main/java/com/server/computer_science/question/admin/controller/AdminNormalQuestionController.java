package com.server.computer_science.question.admin.controller;


import com.server.computer_science.question.admin.service.AdminNormalQuestionGetService;
import com.server.computer_science.question.admin.service.AdminNormalQuestionMakeService;
import com.server.computer_science.question.normal_question.dto.response.ResponseNormalQuestionDto;
import com.server.computer_science.question.normal_question.dto.response.ResponseNormalQuestionDtoForAdmin;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminNormalQuestionController {
    private final AdminNormalQuestionGetService adminNormalQuestionGetService;

    @GetMapping("/question/normal")
    public List<ResponseNormalQuestionDto> getAllNormalQuestionForAdmin(){
        return adminNormalQuestionGetService.getAllNormalQuestionForAdmin();
    }
}
