package com.server.computer_science.question.normal_question.controller;


import com.server.computer_science.question.normal_question.dto.request.RequestNormalQuestionDto;
import com.server.computer_science.question.normal_question.dto.response.ResponseNormalQuestionDto;
import com.server.computer_science.question.normal_question.service.NormalProblemMakeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NormalProblemMakeController {
    private final NormalProblemMakeService normalProblemMakeService;

    @PostMapping(value = "/question/normal")
    public List<ResponseNormalQuestionDto> makeNormalQuestion(@RequestBody List<RequestNormalQuestionDto> requestNormalQuestionDto){
        return normalProblemMakeService.makeNormalQuestion(requestNormalQuestionDto);
    }


}
