package com.comssa.api.question.license;

import com.comssa.api.ControllerTest;
import com.comssa.api.question.license.controller.AdminLicenseQuestionController;
import com.comssa.api.question.license.service.AdminLicenseMuiltipleChoiceQuestionUpdateService;
import com.comssa.api.question.license.service.AdminLicenseQuestionChoiceUpdateService;
import com.comssa.api.question.license.service.AdminLicenseQuestionMakeService;
import com.comssa.persistence.question.common.dto.request.RequestChangeContentDto;
import com.comssa.persistence.question.common.dto.request.RequestChangeDescriptionDto;
import com.comssa.persistence.question.common.dto.response.ResponseMultipleChoiceQuestionDto;
import com.comssa.persistence.question.license.domain.LicenseCategory;
import com.comssa.persistence.question.license.domain.LicenseMultipleChoiceQuestion;
import com.comssa.persistence.question.license.dto.request.RequestMakeLicenseMultipleChoiceQuestionDto;
import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(AdminLicenseQuestionController.class)
@ContextConfiguration(classes = AdminLicenseQuestionController.class)
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
	private ResponseMultipleChoiceQuestionDto responseMultipleChoiceQuestionDto;
	private List<ResponseMultipleChoiceQuestionDto> responseMultipleChoiceQuestionDtos;

	@BeforeEach
	void setUp() {
		licenseMultipleChoiceQuestions = new ArrayList<>();
		responseMultipleChoiceQuestionDtos = new ArrayList<>();
		licenseMultipleChoiceQuestion = LicenseMultipleChoiceQuestion.makeForTest("test");
		licenseMultipleChoiceQuestions.add(licenseMultipleChoiceQuestion);
		responseMultipleChoiceQuestionDto = ResponseMultipleChoiceQuestionDto.forLicense(licenseMultipleChoiceQuestion);
		responseMultipleChoiceQuestionDtos.add(responseMultipleChoiceQuestionDto);
	}

	@Test
	@DisplayName("생성")
	void makeLicenseQuestion() throws Exception {
		final String path = baseApiUrl;
		final String document_Name = "성공";
		Mockito.when(adminLicenseQuestionMakeService
			.makeLicenseNormalQuestion(any())).thenReturn(responseMultipleChoiceQuestionDtos);

		mockMvc.perform(RestDocumentationRequestBuilders.post(path)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(RequestMakeLicenseMultipleChoiceQuestionDto.from(
					"test",
					LicenseCategory.SQLD,
					licenseMultipleChoiceQuestions,
					LicenseMultipleChoiceQuestion::getQuestionChoices))))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers
				.content()
				.json(objectMapper.writeValueAsString(responseMultipleChoiceQuestionDtos)))
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
			.andExpect(MockMvcResultMatchers
				.content()
				.json(objectMapper.writeValueAsString(responseMultipleChoiceQuestionDto)))
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
			.andExpect(MockMvcResultMatchers
				.content()
				.json(objectMapper.writeValueAsString(responseMultipleChoiceQuestionDto)))
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
