package com.server.computerscience.question.common.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.server.computerscience.question.common.dto.request.RequestQuestionCommandDto;
import com.server.computerscience.question.common.service.QuestionExternalService;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@Controller
@Api(tags = {"AI 문제 수정 - ADMIN"})
@RequestMapping("/admin/question")
@RequiredArgsConstructor
public class AdminQuestionUpdateController {

	private final QuestionExternalService questionExternalService;

	@PostMapping("/update/external")
	public ResponseEntity<Void> updateQuestionExternal(
		@RequestBody RequestQuestionCommandDto requestQuestionCommandDto
	) {
		questionExternalService.sendQuestionToExternal(requestQuestionCommandDto);
		return ResponseEntity.ok().build();
	}
}
