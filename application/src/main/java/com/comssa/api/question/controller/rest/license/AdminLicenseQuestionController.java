package com.comssa.api.question.controller.rest.license;

import com.comssa.api.question.service.license.implement.AdminLicenseQuestionMakeService;
import com.comssa.persistence.question.common.dto.response.ResponseMultipleChoiceQuestionDto;
import com.comssa.persistence.question.license.dto.request.RequestMakeLicenseMultipleChoiceQuestionDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/question/license")
@Api(tags = {"자격증 문제 - ADMIN"})
@RequiredArgsConstructor
public class AdminLicenseQuestionController {
	private final AdminLicenseQuestionMakeService adminLicenseQuestionMakeService;

	@ApiOperation("단답형 문제 세션으로 생성")
	@PostMapping
	public ResponseEntity<List<ResponseMultipleChoiceQuestionDto>> makeLicenseQuestionOfSession(
		@RequestBody RequestMakeLicenseMultipleChoiceQuestionDto requestMakeLicenseMultipleChoiceQuestionDto) {
		return ResponseEntity.ok(
			adminLicenseQuestionMakeService.makeLicenseNormalQuestion(requestMakeLicenseMultipleChoiceQuestionDto));
	}
}
