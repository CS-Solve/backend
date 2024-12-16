package com.comssa.api.question.major.admin.controller;

import com.comssa.api.ControllerTest;
import com.comssa.api.question.controller.view.AdminMajorQuestionViewController;
import com.comssa.api.question.service.rest.major.AdminMajorQuestionClassifiedGetService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest({
	AdminMajorQuestionViewController.class
})
@ContextConfiguration(classes = AdminMajorQuestionViewController.class)
@DisplayName("단위 테스트 - 전공 개념 Admin View Controller")
class AdminMajorQuestionViewControllerTest extends ControllerTest {

	@MockBean
	private AdminMajorQuestionClassifiedGetService adminMajorQuestionClassifiedGetService;

	@Test
	@DisplayName("전공 문제 update 페이지 View")
	void updateMajorQuestionPage() throws Exception {
		// Given

		when(adminMajorQuestionClassifiedGetService.getClassifiedAllMajorMultipleChoiceQuestions())
			.thenReturn(new HashMap<>());

		// When & Then
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/question/major/multiple/update"))
			.andExpect(status().isOk()) // 상태 코드 200 확인
			.andExpect(view().name("question-update")) // 뷰 이름 확인
			.andExpect(model().attributeExists("classifiedQuestions")) // 모델 속성 확인
			.andDo(print());
	}
}
