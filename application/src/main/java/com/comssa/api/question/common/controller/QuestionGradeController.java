package com.comssa.api.question.common.controller;

import com.comssa.api.question.common.service.MultipleChoiceQuestionGradeService;
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

	@ApiOperation("자격증 문제 채점")
	@PatchMapping("/question/{questionField}/{questionType}/{choiceId}/{questionAct}")
	public ResponseEntity<Boolean> isMajorChoiceAnswer(
		@PathVariable("questionField") String questionField,
		@PathVariable("questionType") String questionType,
		@PathVariable("questionAct") String questionAct,
		@PathVariable("choiceId") Long choiceId
	) {
		MultipleChoiceQuestionGradeService multipleChoiceQuestionGradeService
			= questionServiceFactory.getMultipleChoiceQuestionGradeService(
			questionField,
			questionType,
			questionAct,
			MultipleChoiceQuestionGradeService.class
		);
		return ResponseEntity.ok(multipleChoiceQuestionGradeService.isChoiceAnswer(choiceId));
	}
}
