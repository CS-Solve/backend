package com.comssa.api.question.common.controller;

import com.comssa.api.ControllerTest;
import com.comssa.api.question.common.service.implement.QuestionCommonUpdateService;
import com.comssa.persistence.question.common.domain.Question;
import com.comssa.persistence.question.common.dto.request.RequestChangeContentDto;
import com.comssa.persistence.question.common.dto.request.RequestChangeDescriptionDto;
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

import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(QuestionCommonUpdateController.class)
@ContextConfiguration(classes = QuestionCommonUpdateController.class)
class QuestionCommonUpdateControllerTest extends ControllerTest {
	private final String tag = "문제 수정";
	private final String baseApiUrl = "/admin/question/common";
	private final String idUrl = "/1";
	@MockBean
	private QuestionCommonUpdateService questionCommonUpdateService;

	private Question question;

	@BeforeEach
	void setUp() {

	}

	@Test
	@DisplayName("본문 업데이트")
	void updateLicenseQuestionContent() throws Exception {
		final String path = baseApiUrl + idUrl + "/content";
		final String document_Name = "성공";
		Mockito.when(questionCommonUpdateService.changeContent(any(), any()))
			.thenReturn(question);

		mockMvc.perform(RestDocumentationRequestBuilders.patch(path)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(RequestChangeContentDto.forTest())))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andDo(print())
			.andDo(MockMvcRestDocumentation.document(
				document_Name
			))
			.andDo(MockMvcRestDocumentationWrapper.document(
				document_Name,
				resource(
					ResourceSnippetParameters.builder()
						.tag(tag)
						.description("본")
						.build()
				)
			));
	}

	@Test
	@DisplayName("해설 업데이트")
	void updateLicenseQuestionDescription() throws Exception {
		final String path = baseApiUrl + idUrl + "/description";
		final String document_Name = "성공";
		Mockito.when(questionCommonUpdateService.changeDescription(any(), any()))
			.thenReturn(question);

		mockMvc.perform(RestDocumentationRequestBuilders.patch(path)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(RequestChangeDescriptionDto.forTest())))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andDo(print())
			.andDo(MockMvcRestDocumentation.document(
				document_Name
			))
			.andDo(MockMvcRestDocumentationWrapper.document(
				document_Name,
				resource(
					ResourceSnippetParameters.builder()
						.tag(tag)
						.description("해설")
						.build()
				)
			));
	}

	@Test
	@DisplayName("개시 여부 변경")
	void updateSingleMajorMultipleChoiceQuestionApprove() throws Exception {
		final String PATH = "/admin/question/common/1/toggle-approve";
		final String document_Name = "성공";
		Mockito.when(questionCommonUpdateService.toggleApprove(any()))
			.thenReturn(question);
		mockMvc.perform(RestDocumentationRequestBuilders.patch(PATH)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andDo(print())
			.andDo(MockMvcRestDocumentation.document(
				document_Name
			))
			.andDo(MockMvcRestDocumentationWrapper.document(
				document_Name, resource(
					ResourceSnippetParameters.builder()
						.tag(tag)
						.description("개시 여부")
						.build()
				)
			));
	}
}
