package com.comssa.api.question.controller.rest.major;

import com.comssa.api.question.service.rest.major.AdminMajorQuestionMakeService;
import com.comssa.api.question.service.rest.major.implement.AdminMajorMultipleChoiceQuestionUpdateService;
import com.comssa.persistence.question.dto.common.request.RequestMakeMultipleChoiceQuestionDto;
import com.comssa.persistence.question.dto.common.response.ResponseDescriptiveQuestionDto;
import com.comssa.persistence.question.dto.common.response.ResponseMultipleChoiceQuestionDto;
import com.comssa.persistence.question.dto.common.response.ResponseQuestionDto;
import com.comssa.persistence.question.dto.major.request.RequestMakeMajorDescriptiveQuestionDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
	@PostMapping(value = "/question/major/multiple")
	public ResponseEntity<List<ResponseMultipleChoiceQuestionDto>> makeMultiMajorQuestion(
		@RequestBody List<RequestMakeMultipleChoiceQuestionDto> requestMakeMultipleChoiceQuestionDtos) {
		return ResponseEntity.ok(
			adminMajorQuestionMakeService
				.makeMultipleChoiceQuestions(requestMakeMultipleChoiceQuestionDtos)
				.stream()
				.map(question -> (ResponseMultipleChoiceQuestionDto) ResponseQuestionDto.from(question))
				.collect(Collectors.toList()));
	}

	@ApiOperation("단답형 문제 상태 업데이트 - 단답형-주관식 토글")
	@PatchMapping(value = "/question/major/multiple/{id}/toggle-multiple")
	public ResponseEntity<ResponseMultipleChoiceQuestionDto> toggleCanBeShortAnswered(
		@PathVariable("id") Long questionId) {
		return ResponseEntity.ok(ResponseQuestionDto.from(
			adminMajorMultipleChoiceQuestionUpdateService.toggleCanBeShortAnswered(questionId)));
	}

	@ApiOperation("서술형 문제 리스트로 생성")
	@PostMapping(value = "/question/major/descriptive")
	public ResponseEntity<List<ResponseDescriptiveQuestionDto>> makeDescriptiveQuestion(
		@RequestBody List<RequestMakeMajorDescriptiveQuestionDto> requestMakeMajorDescriptiveQuestionDtos
	) {
		return ResponseEntity.ok(
			adminMajorQuestionMakeService
				.makeDescriptiveQuestions(requestMakeMajorDescriptiveQuestionDtos)
				.stream()
				.map(question -> (ResponseDescriptiveQuestionDto) ResponseQuestionDto.from(question))
				.collect(Collectors.toList()));
	}


}
