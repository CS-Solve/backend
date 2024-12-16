package com.comssa.api.question.controller.rest.common;

import com.comssa.api.question.service.rest.common.QuestionChoiceGradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"문제 채점"})
@RestController
@RequiredArgsConstructor
public class QuestionGradeController {
	private final QuestionServiceFactory questionServiceFactory;

	@ApiOperation("객관식 문제 채점")
	@PatchMapping("/question/{questionField}/{questionType}/choice/{choiceId}/{questionAct}")
	public ResponseEntity<Boolean> isMajorChoiceAnswer(
		@PathVariable("questionField") String questionField,
		@PathVariable("questionType") String questionType,
		@PathVariable("questionAct") String questionAct,
		@PathVariable("choiceId") Long choiceId
	) {
		QuestionChoiceGradeService questionChoiceGradeService
			= questionServiceFactory.getQuestionService(
			questionField,
			questionType,
			questionAct,
			QuestionChoiceGradeService.class
		);
		return ResponseEntity.ok(questionChoiceGradeService.isChoiceAnswer(choiceId));
	}
}
