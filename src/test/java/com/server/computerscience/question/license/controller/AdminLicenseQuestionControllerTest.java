package com.server.computerscience.question.license.controller;

import static com.epages.restdocs.apispec.ResourceDocumentation.*;
import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.server.computerscience.ControllerTest;
import com.server.computerscience.question.common.dto.request.RequestChangeContentDto;
import com.server.computerscience.question.common.dto.request.RequestChangeDescriptionDto;
import com.server.computerscience.question.common.dto.response.ResponseQuestionDto;
import com.server.computerscience.question.license.domain.LicenseCategory;
import com.server.computerscience.question.license.domain.LicenseMultipleChoiceQuestion;
import com.server.computerscience.question.license.dto.request.RequestMakeLicenseMultipleChoiceQuestionDto;
import com.server.computerscience.question.license.service.AdminLicenseMuiltipleChoiceQuestionUpdateService;
import com.server.computerscience.question.license.service.AdminLicenseQuestionChoiceUpdateService;
import com.server.computerscience.question.license.service.AdminLicenseQuestionMakeService;

@WebMvcTest(AdminLicenseQuestionController.class)
@DisplayName("단위 테스트 - 자격증 Admin Controller")
class AdminLicenseQuestionControllerTest extends ControllerTest {
	private final String tag = "자격증 문제";
	private final String baseApiUrl = "/admin/question/license";
	private final String idUrl = "/1";
	@MockBean
	private AdminLicenseQuestionChoiceUpdateService adminLicenseQuestionChoiceUpdateService;
	@MockBean
	private AdminLicenseQuestionMakeService adminLicenseQuestionMakeService;
	@MockBean
	private AdminLicenseMuiltipleChoiceQuestionUpdateService adminLicenseMuiltipleChoiceQuestionUpdateService;
	private LicenseMultipleChoiceQuestion licenseMultipleChoiceQuestion;
	private List<LicenseMultipleChoiceQuestion> licenseMultipleChoiceQuestions;
	private ResponseQuestionDto responseQuestionDto;
	private List<ResponseQuestionDto> responseQuestionDtos;

	@BeforeEach
	void setUp() {
		licenseMultipleChoiceQuestions = new ArrayList<>();
		responseQuestionDtos = new ArrayList<>();
		licenseMultipleChoiceQuestion = LicenseMultipleChoiceQuestion.makeForTest("test");
		licenseMultipleChoiceQuestions.add(licenseMultipleChoiceQuestion);
		responseQuestionDto = ResponseQuestionDto.forAdmin(licenseMultipleChoiceQuestion);
		responseQuestionDtos.add(responseQuestionDto);
	}

	@Test
	@DisplayName("생성")
	void makeLicenseQuestion() throws Exception {
		final String path = baseApiUrl;
		final String document_Name = "성공";
		Mockito.when(adminLicenseQuestionMakeService.makeLicenseNormalQuestion(any())).thenReturn(responseQuestionDtos);

		mockMvc.perform(RestDocumentationRequestBuilders.post(path)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(RequestMakeLicenseMultipleChoiceQuestionDto.from(
					"test",
					LicenseCategory.SQLD,
					licenseMultipleChoiceQuestions,
					LicenseMultipleChoiceQuestion::getQuestionChoices))))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(responseQuestionDtos)))
			.andDo(print())
			.andDo(MockMvcRestDocumentation.document(
				document_Name
			))
			.andDo(MockMvcRestDocumentationWrapper.document(
				document_Name, resource(
					ResourceSnippetParameters.builder()
						.tag(tag)
						.description("자격증")
						.build()
				)
			));
	}

	@Test
	@DisplayName("본문 업데이트")
	void updateLicenseQuestionContent() throws Exception {
		final String path = baseApiUrl + idUrl + "/content";
		final String document_Name = "성공";
		Mockito.when(adminLicenseMuiltipleChoiceQuestionUpdateService.changeContent(any(), any()))
			.thenReturn(licenseMultipleChoiceQuestion);

		mockMvc.perform(RestDocumentationRequestBuilders.patch(path)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(RequestChangeContentDto.forTest())))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(responseQuestionDto)))
			.andDo(print())
			.andDo(MockMvcRestDocumentation.document(
				document_Name
			))
			.andDo(MockMvcRestDocumentationWrapper.document(
				document_Name,
				resource(
					ResourceSnippetParameters.builder()
						.tag(tag)
						.description("자격증")
						.build()
				)
			));
	}

	@Test
	@DisplayName("해설 업데이트")
	void updateLicenseQuestionDescription() throws Exception {
		final String path = baseApiUrl + idUrl + "/description";
		final String document_Name = "성공";
		Mockito.when(adminLicenseMuiltipleChoiceQuestionUpdateService.changeDescription(any(), any()))
			.thenReturn(licenseMultipleChoiceQuestion);

		mockMvc.perform(RestDocumentationRequestBuilders.patch(path)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(RequestChangeDescriptionDto.forTest())))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(responseQuestionDto)))
			.andDo(print())
			.andDo(MockMvcRestDocumentation.document(
				document_Name
			))
			.andDo(MockMvcRestDocumentationWrapper.document(
				document_Name,
				resource(
					ResourceSnippetParameters.builder()
						.tag(tag)
						.description("자격증")
						.build()
				)
			));
	}

	@Test
	@DisplayName("삭제")
	void deleteLicenseQuestion() throws Exception {
		final String path = baseApiUrl + idUrl;
		final String document_Name = "성공";

		mockMvc.perform(RestDocumentationRequestBuilders.delete(path))
			.andExpect(MockMvcResultMatchers.status().isNoContent())
			.andDo(print())
			.andDo(MockMvcRestDocumentation.document(
				document_Name
			))
			.andDo(MockMvcRestDocumentationWrapper.document(
				document_Name,
				resource(
					ResourceSnippetParameters.builder()
						.tag(tag)
						.description("자격증")
						.build()
				)
			));
	}

}
