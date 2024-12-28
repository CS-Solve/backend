package com.comssa.api.question.major.admin.controller;

import com.comssa.api.ControllerTest;
import com.comssa.api.question.controller.rest.major.MajorQuestionUpdateController;
import com.comssa.api.question.service.rest.major.implement.AdminMajorMultipleChoiceQuestionUpdateService;
import com.comssa.core.question.service.common.DescriptiveQuestionService;
import com.comssa.persistence.question.domain.major.MajorMultipleChoiceQuestion;
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

@WebMvcTest(MajorQuestionUpdateController.class)
@ContextConfiguration(classes = {MajorQuestionUpdateController.class})
@DisplayName("단위 테스트 - 전공 개념 업데이트")
public class MajorQuestionUpdateControllerTest extends ControllerTest {
	private final String tag = "전공 문제";

	@MockBean
	private AdminMajorMultipleChoiceQuestionUpdateService adminMajorMultipleChoiceQuestionUpdateService;
	@MockBean
	private DescriptiveQuestionService descriptiveQuestionService;

	private MajorMultipleChoiceQuestion majorMultipleChoiceQuestion;

	@BeforeEach
	void setUp() {
		majorMultipleChoiceQuestion = MajorMultipleChoiceQuestion.makeForTest();
	}

	@Test
	@DisplayName("전공 문제 업데이트 - 객관식 여부")
	void updateSingleMajorMultipleChoiceQuestionShortAnswered() throws Exception {
		final String PATH = "/admin/question/major/multiple/1/toggle-multiple";
		final String document_Name = "성공";
		Mockito.when(adminMajorMultipleChoiceQuestionUpdateService.toggleCanBeShortAnswered(any()))
			.thenReturn(majorMultipleChoiceQuestion);
		mockMvc.perform(RestDocumentationRequestBuilders.patch(PATH)
				.contentType(MediaType.APPLICATION_JSON)
			)
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andDo(print())
			.andDo(MockMvcRestDocumentation.document(
				document_Name
			))
			.andDo(MockMvcRestDocumentationWrapper.document(
				document_Name, resource(
					ResourceSnippetParameters.builder()
						.tag(tag)
						.description("단답형 문제")
						.build()
				)
			));
	}
}
