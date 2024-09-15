package com.server.computer_science.question.normal_question.controller;


import com.server.computer_science.question.normal_question.service.NormalProblemMakeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NormalProblemMakeController {

    private final NormalProblemMakeService normalProblemMakeService;
}
