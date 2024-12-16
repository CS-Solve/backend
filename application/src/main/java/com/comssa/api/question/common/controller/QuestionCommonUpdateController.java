package com.comssa.api.question.common.controller;


import com.comssa.api.question.common.service.implement.QuestionCommonUpdateService;
import com.comssa.persistence.question.common.dto.request.RequestChangeContentDto;
import com.comssa.persistence.question.common.dto.response.ResponseMultipleChoiceQuestionDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@Api(tags = {"문제 수정"})
@RequestMapping("/admin")
public class QuestionCommonUpdateController {
	private final QuestionCommonUpdateService questionCommonUpdateService;

	@ApiOperation("문제 개시 허용")
	@PatchMapping(value = "/question/common/{id}/toggle-approve")
	public ResponseEntity<ResponseMultipleChoiceQuestionDto> toggleApproveNormalQuestion(
		@PathVariable("id") Long questionId
	) {
		questionCommonUpdateService.toggleApprove(questionId);
		return ResponseEntity.ok().build();
	}

	@ApiOperation("문제 본문 업데이트")
	@PatchMapping(value = "/question/common/{id}/content")
	public ResponseEntity<ResponseMultipleChoiceQuestionDto> changeQuestion(
		@PathVariable("id") Long questionId,
		@RequestBody RequestChangeContentDto requestChangeContentDto) {
		questionCommonUpdateService.changeContent(questionId, requestChangeContentDto);
		return ResponseEntity.ok().build();
	}

	@ApiOperation("해설 업데이트")
	@PatchMapping(value = "/question/common/{id}/description")
	public ResponseEntity<ResponseMultipleChoiceQuestionDto> changeDescription(
		@PathVariable("id") Long questionId,
		@RequestBody RequestChangeContentDto requestChangeContentDto
	) {
		questionCommonUpdateService.changeDescription(questionId, requestChangeContentDto);
		return ResponseEntity.ok().build();
	}

	@ApiOperation("이미지 업로드")
	@PatchMapping("/question/common/{id}/image")
	public ResponseEntity<String> updateLicenseQuestionWithImage(
		@PathVariable("id") Long licenseQuestionId,
		@RequestPart(value = "image") MultipartFile file) {
		return ResponseEntity.ok(questionCommonUpdateService.updateImage(licenseQuestionId, file));
	}

	@ApiOperation(" 문제 삭제")
	@DeleteMapping(value = "/question/common/{id}")
	public ResponseEntity<Void> changeDescription(
		@PathVariable("id") Long questionId) {
		questionCommonUpdateService.deleteQuestion(questionId);
		return ResponseEntity.noContent().build();
	}
}
