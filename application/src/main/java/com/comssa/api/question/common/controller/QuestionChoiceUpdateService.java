//package com.comssa.api.question.common.controller;
//
//import com.comssa.persistence.question.common.dto.request.RequestChangeContentDto;
//import com.comssa.persistence.question.common.dto.response.ResponseQuestionChoiceDto;
//import io.swagger.annotations.ApiOperation;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.PatchMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/admin")
//@RequiredArgsConstructor
//public class QuestionChoiceUpdateService {
//	private final QuestionServiceFactory questionServiceFactory;
//
//	@ApiOperation("단답형 선택지 업데이트 - 선택지 지문 업데이트")
//	@PatchMapping(value = "/question/{questionField}/{questionType}/choice/{id}")
//	public ResponseEntity<ResponseQuestionChoiceDto> changeChoiceContent(
//		@PathVariable("id") Long choiceId,
//		@PathVariable("questionField") String questionField,
//		@PathVariable("questionType") String questionType,
//		@RequestBody RequestChangeContentDto requestChangeContentDto) {
//		QuestionChoiceUpdateService questionChoiceUpdateService = questionServiceFactory.getQuestionService(
//			questionField,
//
//			)
//		return ResponseEntity.ok(ResponseQuestionChoiceDto.of(adminLicenseQuestionChoiceUpdateService.changeContent(
//			choiceId,
//			requestChangeContentDto)));
//	}
//
//	@ApiOperation("단답형 선택지 업데이트 - 정답 여부 변경")
//	@PatchMapping(value = "/question/{questionField}/{questionType}/choice/{id}/toggle")
//	public ResponseEntity<ResponseQuestionChoiceDto> changeChoiceContent(
//		@PathVariable("questionField") String questionField,
//		@PathVariable("questionType") String questionType,
//		@PathVariable("id") Long licenseChoiceId) {
//		return ResponseEntity.ok(ResponseQuestionChoiceDto.of(adminLicenseQuestionChoiceUpdateService
//			.toggleAnswerStatus(
//				licenseChoiceId)));
//	}
//
//	@ApiOperation("단답형 선택지 업데이트 - 선택지 삭제")
//	@DeleteMapping(value = "/question/{questionField}/{questionType}/choice/{id}")
//	public ResponseEntity<Void> deleteChoiceContent(
//		@PathVariable("questionField") String questionField,
//		@PathVariable("questionType") String questionType,
//		@PathVariable("id") Long licenseChoiceId) {
//		adminLicenseQuestionChoiceUpdateService.deleteQuestionChoice(
//			licenseChoiceId);
//		return ResponseEntity.noContent().build();
//	}
//}
