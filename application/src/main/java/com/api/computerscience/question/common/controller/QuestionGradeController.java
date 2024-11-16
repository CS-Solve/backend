package com.api.computerscience.question.common.controller;

import com.api.computerscience.question.license.service.LicenseQuestionChoiceGradeService;
import com.api.computerscience.question.major.user.service.implement.MajorQuestionChoiceGradeService;
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
	private final LicenseQuestionChoiceGradeService licenseQuestionChoiceGradeService;
	private final MajorQuestionChoiceGradeService majorQuestionChoiceGradeService;

	@ApiOperation("자격증 문제 채점")
	@PatchMapping("/question/license/choice/{choiceId}/select")
	public ResponseEntity<Boolean> isLicenseChoiceAnswer(
		@PathVariable("choiceId") Long choiceId
	) {
		return ResponseEntity.ok(licenseQuestionChoiceGradeService.isChoiceAnswer(choiceId));
	}

	@ApiOperation("자격증 문제 채점")
	@PatchMapping("/question/major/choice/{choiceId}/select")
	public ResponseEntity<Boolean> isMajorChoiceAnswer(
		@PathVariable("choiceId") Long choiceId
	) {
		return ResponseEntity.ok(majorQuestionChoiceGradeService.isChoiceAnswer(choiceId));
	}

}
