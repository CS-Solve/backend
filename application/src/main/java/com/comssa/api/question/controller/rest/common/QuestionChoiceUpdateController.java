package com.comssa.api.question.controller.rest.common;

import com.comssa.api.question.service.rest.common.implement.QuestionChoiceUpdateService;
import com.comssa.persistence.question.dto.common.request.RequestChangeQuestionContentDto;
import com.comssa.persistence.question.dto.common.response.ResponseQuestionChoiceDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class QuestionChoiceUpdateController {
	private final QuestionChoiceUpdateService questionChoiceUpdateService;

	@ApiOperation("단답형 선택지 업데이트 - 선택지 지문 업데이트")
	@PatchMapping(value = "/question/common/choice/{id}")
	public ResponseEntity<ResponseQuestionChoiceDto> changeChoiceContent(
		@PathVariable("id") Long choiceId,
		@RequestBody RequestChangeQuestionContentDto requestChangeQuestionContentDto) {

		return ResponseEntity.ok(ResponseQuestionChoiceDto.from(
			questionChoiceUpdateService.changeContent(
				choiceId,
				requestChangeQuestionContentDto)));
	}

	@ApiOperation("단답형 선택지 업데이트 - 정답 여부 변경")
	@PatchMapping(value = "/question/common/choice/{id}/toggle")
	public ResponseEntity<ResponseQuestionChoiceDto> changeChoiceContent(
		@PathVariable("id") Long licenseChoiceId) {
		return ResponseEntity.ok(ResponseQuestionChoiceDto.from(
			questionChoiceUpdateService
				.toggleAnswerStatus(
					licenseChoiceId)));
	}

	@ApiOperation("단답형 선택지 업데이트 - 선택지 삭제")
	@DeleteMapping(value = "/question/common/choice/{id}")
	public ResponseEntity<Void> deleteChoiceContent(
		@PathVariable("id") Long licenseChoiceId) {
		questionChoiceUpdateService.deleteQuestionChoice(
			licenseChoiceId);
		return ResponseEntity.noContent().build();
	}
}
