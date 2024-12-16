package com.comssa.api.question.major.admin.controller;

import com.comssa.api.question.major.admin.service.AdminMajorQuestionMakeService;
import com.comssa.api.question.major.admin.service.implement.AdminMajorMultipleChoiceQuestionUpdateService;
import com.comssa.api.question.major.common.exception.DuplicateQuestionException;
import com.comssa.persistence.question.common.dto.response.ResponseDescriptiveQuestionDto;
import com.comssa.persistence.question.common.dto.response.ResponseMultipleChoiceQuestionDto;
import com.comssa.persistence.question.major.admin.dto.RequestMakeMajorDescriptiveQuestionDto;
import com.comssa.persistence.question.major.admin.dto.RequestMakeMajorMultipleChoiceQuestionDto;
import com.comssa.persistence.question.major.admin.dto.ResponseMajorMultipleChoiceQuestionForAdminDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Api(tags = {"전공 문제 - ADMIN"})
@RequestMapping("/admin")
public class AdminMajorQuestionController {

	private final AdminMajorQuestionMakeService adminMajorQuestionMakeService;
	private final AdminMajorMultipleChoiceQuestionUpdateService adminMajorMultipleChoiceQuestionUpdateService;


	@ApiOperation("단답형 문제 리스트로 생성")
	@PostMapping(value = "/question/major-multi")
	public ResponseEntity<List<ResponseMultipleChoiceQuestionDto>> makeMultiMajorQuestion(
		@RequestBody List<RequestMakeMajorMultipleChoiceQuestionDto> requestMakeMajorMultipleChoiceQuestionDtos) {
		return ResponseEntity.ok(
			adminMajorQuestionMakeService.makeMultipleChoiceQuestions(requestMakeMajorMultipleChoiceQuestionDtos)
				.stream()
				.map(ResponseMultipleChoiceQuestionDto::forAdminMajor)
				.collect(Collectors.toList()));
	}

	@ApiOperation("서술형 문제 리스트로 생성")
	@PostMapping(value = "/question/major/descriptive")
	public ResponseEntity<List<ResponseDescriptiveQuestionDto>> makeDescriptiveQuestion(
		@RequestBody List<RequestMakeMajorDescriptiveQuestionDto> requestMakeMajorDescriptiveQuestionDtos
	) {
		return ResponseEntity.ok(
			adminMajorQuestionMakeService.makeDescriptiveQuestions(
					requestMakeMajorDescriptiveQuestionDtos
				)
				.stream()
				.map(ResponseDescriptiveQuestionDto::forAdminMajor)
				.collect(Collectors.toList())
		);
	}

	@ApiOperation("단답형 문제 단일로 생성")
	@PostMapping(value = "/question/major-single")
	public ResponseEntity<ResponseMultipleChoiceQuestionDto> makeSingleNormalQuestion(
		@RequestBody RequestMakeMajorMultipleChoiceQuestionDto requestMakeMajorMultipleChoiceQuestionDto) throws
		DuplicateQuestionException {
		return ResponseEntity.ok(
			ResponseMultipleChoiceQuestionDto.forAdminMajor(
				adminMajorQuestionMakeService.makeMultipleChoiceQuestion(requestMakeMajorMultipleChoiceQuestionDto)));
	}
	
	@ApiOperation("단답형 문제 상태 업데이트 - 단답형-주관식 토글")
	@PatchMapping(value = "/question/major/{id}/toggle-multiple")
	public ResponseEntity<ResponseMultipleChoiceQuestionDto> toggleCanBeShortAnswered(
		@PathVariable("id") Long questionId) {
		return ResponseEntity.ok(ResponseMajorMultipleChoiceQuestionForAdminDto.forAdminMajor(
			adminMajorMultipleChoiceQuestionUpdateService.toggleCanBeShortAnswered(questionId)));
	}


	@ApiOperation("단답형 문제 삭제")
	@DeleteMapping(value = "/question/major/{id}")
	public ResponseEntity<Void> deleteMajorQuestion(@PathVariable("id") Long questionId) {
		adminMajorMultipleChoiceQuestionUpdateService.deleteQuestion(questionId);
		return ResponseEntity.noContent().build();
	}
}
