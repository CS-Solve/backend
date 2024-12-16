package com.comssa.api.question.common.controller;

import com.comssa.api.question.license.service.QuestionCommonChoiceUpdateService;
import com.comssa.persistence.question.common.dto.request.RequestChangeContentDto;
import com.comssa.persistence.question.common.dto.response.ResponseQuestionChoiceDto;
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
	private final QuestionCommonChoiceUpdateService questionCommonChoiceUpdateService;

	@ApiOperation("단답형 선택지 업데이트 - 선택지 지문 업데이트")
	@PatchMapping(value = "/question/common/choice/{id}")
	public ResponseEntity<ResponseQuestionChoiceDto> changeChoiceContent(
		@PathVariable("id") Long choiceId,
		@RequestBody RequestChangeContentDto requestChangeContentDto) {

		return ResponseEntity.ok(ResponseQuestionChoiceDto.of(
			questionCommonChoiceUpdateService.changeContent(
				choiceId,
				requestChangeContentDto)));
	}

	@ApiOperation("단답형 선택지 업데이트 - 정답 여부 변경")
	@PatchMapping(value = "/question/common/choice/{id}/toggle")
	public ResponseEntity<ResponseQuestionChoiceDto> changeChoiceContent(
		@PathVariable("id") Long licenseChoiceId) {
		return ResponseEntity.ok(ResponseQuestionChoiceDto.of(
			questionCommonChoiceUpdateService
				.toggleAnswerStatus(
					licenseChoiceId)));
	}

	@ApiOperation("단답형 선택지 업데이트 - 선택지 삭제")
	@DeleteMapping(value = "/question/common/choice/{id}")
	public ResponseEntity<Void> deleteChoiceContent(
		@PathVariable("id") Long licenseChoiceId) {
		questionCommonChoiceUpdateService.deleteQuestionChoice(
			licenseChoiceId);
		return ResponseEntity.noContent().build();
	}
}
