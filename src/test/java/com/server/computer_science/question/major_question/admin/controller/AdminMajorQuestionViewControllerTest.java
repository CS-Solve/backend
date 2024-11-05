package com.server.computer_science.question.major_question.admin.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.HashMap;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.server.computer_science.ControllerTest;
import com.server.computer_science.question.major_question.admin.service.AdminMajorQuestionClassifiedGetService;

@WebMvcTest({
	AdminMajorQuestionViewController.class
})
@DisplayName("단위 테스트 - 전공 개념 Admin View Controller")
class AdminMajorQuestionViewControllerTest extends ControllerTest {

	@MockBean
	private AdminMajorQuestionClassifiedGetService adminMajorQuestionClassifiedGetService;

	@Test
	@DisplayName("전공 문제 update 페이지 View")
	void updateMajorQuestionPage() throws Exception {
		// Given

		when(adminMajorQuestionClassifiedGetService.getClassifiedAllMajorQuestions())
			.thenReturn(new HashMap<>());

		// When & Then
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/question/update"))
			.andExpect(status().isOk()) // 상태 코드 200 확인
			.andExpect(view().name("major-question-update")) // 뷰 이름 확인
			.andExpect(model().attributeExists("classifiedQuestions")) // 모델 속성 확인
			.andDo(print());
	}
}
