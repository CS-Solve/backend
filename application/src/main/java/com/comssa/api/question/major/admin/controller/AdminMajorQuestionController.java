package com.comssa.api.question.major.admin.controller;

import com.comssa.api.question.major.admin.service.AdminMajorQuestionClassifiedGetService;
import com.comssa.api.question.major.admin.service.AdminMajorQuestionMakeService;
import com.comssa.api.question.major.admin.service.implement.AdminMajorMultipleChoiceQuestionUpdateService;
import com.comssa.api.question.major.common.exception.DuplicateQuestionException;
import com.comssa.persistence.question.common.dto.request.RequestChangeContentDto;
import com.comssa.persistence.question.common.dto.response.ResponseClassifiedMultipleQuestionDto;
import com.comssa.persistence.question.common.dto.response.ResponseMultipleChoiceQuestionDto;
import com.comssa.persistence.question.major.admin.dto.RequestMakeMultipleChoiceQuestionDto;
import com.comssa.persistence.question.major.admin.dto.ResponseMajorMultipleChoiceQuestionForAdminDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
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
	private final AdminMajorQuestionClassifiedGetService adminMajorQuestionClassifiedGetService;
	private final AdminMajorQuestionMakeService adminMajorQuestionMakeService;
	private final AdminMajorMultipleChoiceQuestionUpdateService adminMajorMultipleChoiceQuestionUpdateService;

	@ApiOperation("단답형 문제 조회")
	@GetMapping("/question/major")
	public List<ResponseClassifiedMultipleQuestionDto> getAllMajorQuestionForAdmin() {
		return adminMajorQuestionClassifiedGetService.getClassifiedAllMajorQuestions()
			.entrySet().stream()
			.map(entry -> ResponseClassifiedMultipleQuestionDto.forAdmin(entry.getKey(), entry.getValue()))
			.collect(Collectors.toList());
	}

	@ApiOperation("단답형 문제 리스트로 생성")
	@PostMapping(value = "/question/major-multi")
	public ResponseEntity<List<ResponseMultipleChoiceQuestionDto>> makeMultiMajorQuestion(
		@RequestBody List<RequestMakeMultipleChoiceQuestionDto> requestMakeMultipleChoiceQuestionDtos) {
		return ResponseEntity.ok(
			adminMajorQuestionMakeService.makeMultipleChoiceQuestions(requestMakeMultipleChoiceQuestionDtos)
				.stream()
				.map(ResponseMultipleChoiceQuestionDto::forMajor)
				.collect(Collectors.toList()));
	}

	@ApiOperation("단답형 문제 단일로 생성")
	@PostMapping(value = "/question/major-single")
	public ResponseEntity<ResponseMultipleChoiceQuestionDto> makeSingleNormalQuestion(
		@RequestBody RequestMakeMultipleChoiceQuestionDto requestMakeMultipleChoiceQuestionDto) throws
		DuplicateQuestionException {
		return ResponseEntity.ok(
			ResponseMultipleChoiceQuestionDto.forMajor(
				adminMajorQuestionMakeService.makeMultipleChoiceQuestion(requestMakeMultipleChoiceQuestionDto)));
	}

	@ApiOperation("단답형 문제 상태 업데이트 - Approve 토글")
	@PatchMapping(value = "/question/major/{id}/toggle-approve")
	public ResponseEntity<ResponseMultipleChoiceQuestionDto> toggleApproveNormalQuestion(
		@PathVariable("id") Long questionId) {
		return ResponseEntity.ok(ResponseMajorMultipleChoiceQuestionForAdminDto.forMajor(
			adminMajorMultipleChoiceQuestionUpdateService.toggleApprove(questionId)));
	}

	@ApiOperation("단답형 문제 상태 업데이트 - 단답형-주관식 토글")
	@PatchMapping(value = "/question/major/{id}/toggle-multiple")
	public ResponseEntity<ResponseMultipleChoiceQuestionDto> toggleCanBeShortAnswered(
		@PathVariable("id") Long questionId) {
		return ResponseEntity.ok(ResponseMajorMultipleChoiceQuestionForAdminDto.forMajor(
			adminMajorMultipleChoiceQuestionUpdateService.toggleCanBeShortAnswered(questionId)));
	}

	@ApiOperation("단답형 문제 상태 업데이트 - 문제 업데이트")
	@PatchMapping(value = "/question/major/{id}/content")
	public ResponseEntity<ResponseMultipleChoiceQuestionDto> changeQuestion(
		@PathVariable("id") Long questionId,
		@RequestBody RequestChangeContentDto requestChangeContentDto) {
		return ResponseEntity.ok(ResponseMajorMultipleChoiceQuestionForAdminDto.forMajor(
			adminMajorMultipleChoiceQuestionUpdateService.changeContent(questionId, requestChangeContentDto)));
	}

	@ApiOperation("단답형 문제 상태 업데이트 - 해설 업데이트")
	@PatchMapping(value = "/question/major/{id}/description")
	public ResponseEntity<ResponseMultipleChoiceQuestionDto> changeDescription(
		@PathVariable("id") Long questionId,
		@RequestBody RequestChangeContentDto requestChangeContentDto
	) {
		return ResponseEntity.ok(ResponseMajorMultipleChoiceQuestionForAdminDto.forMajor(
			adminMajorMultipleChoiceQuestionUpdateService.changeDescription(questionId, requestChangeContentDto)));
	}

	@ApiOperation("단답형 문제 삭제")
	@DeleteMapping(value = "/question/major/{id}")
	public ResponseEntity<Void> deleteMajorQuestion(@PathVariable("id") Long questionId) {
		adminMajorMultipleChoiceQuestionUpdateService.deleteQuestion(questionId);
		return ResponseEntity.noContent().build();
	}

	@ExceptionHandler(DuplicateQuestionException.class)
	public ResponseEntity<String> handleException(DuplicateQuestionException exception) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
	}
}
