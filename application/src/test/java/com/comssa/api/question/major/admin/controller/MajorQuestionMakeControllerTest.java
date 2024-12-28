package com.comssa.api.question.major.admin.controller;

import com.comssa.api.ControllerTest;
import com.comssa.api.question.controller.rest.major.MajorQuestionMakeController;
import com.comssa.api.question.service.rest.major.AdminMajorQuestionMakeService;
import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
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

import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(MajorQuestionMakeController.class)
@ContextConfiguration(classes = {MajorQuestionMakeController.class})
@DisplayName("단위 테스트 - 전공 개념 생성")
class MajorQuestionMakeControllerTest extends ControllerTest {

	private final String tag = "전공 문제";
	@MockBean
	private AdminMajorQuestionMakeService adminMajorQuestionMakeService;

	@Test
	@DisplayName("전공 문제 단체 생성 - 성공")
	void makeSingleMajorMultipleChoiceQuestions() throws Exception {
		final String PATH = "/admin/question/major/multiple";
		final String document_Name = "성공";
		Mockito.when(adminMajorQuestionMakeService.makeMultipleChoiceQuestions(any())).thenReturn(new ArrayList<>());
		mockMvc.perform(RestDocumentationRequestBuilders.post(PATH)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(new ArrayList<>())))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andDo(print())
			.andDo(MockMvcRestDocumentation.document(
				document_Name
			))
			.andDo(MockMvcRestDocumentationWrapper.document(
				document_Name, resource(
					ResourceSnippetParameters.builder()
						.tag(tag)
						.description("단답형")
						.build()
				)
			));
	}

}
