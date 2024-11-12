package com.server.computerscience.question.license.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.server.computerscience.question.common.dto.request.RequestChangeContentDto;
import com.server.computerscience.question.common.dto.response.ResponseQuestionChoiceDto;
import com.server.computerscience.question.common.dto.response.ResponseQuestionDto;
import com.server.computerscience.question.license.dto.request.RequestMakeLicenseMultipleChoiceQuestionDto;
import com.server.computerscience.question.license.service.AdminLicenseMuiltipleChoiceQuestionUpdateService;
import com.server.computerscience.question.license.service.AdminLicenseQuestionChoiceUpdateService;
import com.server.computerscience.question.license.service.AdminLicenseQuestionMakeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin/question/license")
@Api(tags = {"자격증 문제 - ADMIN"})
@RequiredArgsConstructor
public class AdminLicenseQuestionController {
	private final AdminLicenseQuestionMakeService adminLicenseQuestionMakeService;
	private final AdminLicenseMuiltipleChoiceQuestionUpdateService adminLicenseMuiltipleChoiceQuestionUpdateService;
	private final AdminLicenseQuestionChoiceUpdateService adminLicenseQuestionChoiceUpdateService;

	@ApiOperation("단답형 문제 세션으로 생성")
	@PostMapping
	public ResponseEntity<List<ResponseQuestionDto>> makeLicenseQuestionOfSession(
		@RequestBody RequestMakeLicenseMultipleChoiceQuestionDto requestMakeLicenseMultipleChoiceQuestionDto) {
		return ResponseEntity.ok(
			adminLicenseQuestionMakeService.makeLicenseNormalQuestion(requestMakeLicenseMultipleChoiceQuestionDto));
	}

	@ApiOperation("단답형 문제 이미지 업로드")
	@PatchMapping("/{id}/image")
	public ResponseEntity<String> updateLicenseQuestionWithImage(@PathVariable("id") Long licenseQuestionId,
		@RequestPart(value = "image") MultipartFile file) {
		return ResponseEntity.ok(
			adminLicenseQuestionMakeService.updateLicenseQuestionWithImage(licenseQuestionId, file));
	}

	@ApiOperation("단답형 문제 상태 업데이트 - 문제 지문 업데이트")
	@PatchMapping(value = "/{id}/content")
	public ResponseEntity<ResponseQuestionDto> changeContent(
		@PathVariable("id") Long questionId,
		@RequestBody RequestChangeContentDto requestChangeContentDto) {
		return ResponseEntity.ok(ResponseQuestionDto.forAdmin(
			adminLicenseMuiltipleChoiceQuestionUpdateService.changeContent(questionId, requestChangeContentDto)));
	}

	@ApiOperation("단답형 문제 상태 업데이트 - 문제 해설 업데이트")
	@PatchMapping(value = "/{id}/description")
	public ResponseEntity<ResponseQuestionDto> changeDescription(
		@PathVariable("id") Long questionId,
		@RequestBody RequestChangeContentDto requestChangeContentDto) {
		return ResponseEntity.ok(ResponseQuestionDto.forAdmin(
			adminLicenseMuiltipleChoiceQuestionUpdateService.changeDescription(questionId, requestChangeContentDto)));
	}

	@ApiOperation("단답형 문제 상태 업데이트 - 문제 해설 업데이트")
	@PatchMapping(value = "/{id}/toggle-approve")
	public ResponseEntity<ResponseQuestionDto> toggleIsApprove(
		@PathVariable("id") Long questionId
	) {
		return ResponseEntity.ok(ResponseQuestionDto.forAdmin(
			adminLicenseMuiltipleChoiceQuestionUpdateService.toggleApprove(questionId)));
	}

	@ApiOperation("단답형 문제 상태 업데이트 - 문제 삭제")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> changeDescription(
		@PathVariable("id") Long questionId) {
		adminLicenseMuiltipleChoiceQuestionUpdateService.deleteQuestion(questionId);
		return ResponseEntity.noContent().build();
	}

	@ApiOperation("단답형 선택지 업데이트 - 선택지 지문 업데이트")
	@PatchMapping(value = "/choice/{id}")
	public ResponseEntity<ResponseQuestionChoiceDto> changeChoiceContent(
		@PathVariable("id") Long licenseChoiceId,
		@RequestBody RequestChangeContentDto requestChangeContentDto) {
		return ResponseEntity.ok(ResponseQuestionChoiceDto.of(adminLicenseQuestionChoiceUpdateService.changeContent(
			licenseChoiceId,
			requestChangeContentDto)));
	}

	@ApiOperation("단답형 선택지 업데이트 - 선택지 삭제")
	@DeleteMapping(value = "/choice/{id}")
	public ResponseEntity<Void> deleteChoiceContent(
		@PathVariable("id") Long licenseChoiceId) {
		adminLicenseQuestionChoiceUpdateService.deleteQuestionChoice(
			licenseChoiceId);
		return ResponseEntity.noContent().build();
	}

	@ApiOperation("단답형 선택지 업데이트 - 정답 여부 변경")
	@PatchMapping(value = "/choice/{id}/toggle")
	public ResponseEntity<ResponseQuestionChoiceDto> changeChoiceContent(
		@PathVariable("id") Long licenseChoiceId) {
		return ResponseEntity.ok(ResponseQuestionChoiceDto.of(adminLicenseQuestionChoiceUpdateService
			.toggleAnswerStatus(
				licenseChoiceId)));
	}
}
