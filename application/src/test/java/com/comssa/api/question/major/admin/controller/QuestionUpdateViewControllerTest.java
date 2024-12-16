package com.comssa.api.question.major.admin.controller;

import com.comssa.api.ControllerTest;
import com.comssa.api.question.controller.view.QuestionUpdateViewController;
import com.comssa.api.question.service.rest.license.implement.AdminLicenseQuestionGetService;
import com.comssa.api.question.service.rest.license.implement.UserLicenseQuestionGetService;
import com.comssa.api.question.service.rest.major.AdminMajorQuestionClassifiedGetService;
import com.comssa.persistence.question.common.domain.Question;
import com.comssa.persistence.question.common.domain.QuestionCategory;
import com.comssa.persistence.question.license.domain.LicenseMultipleChoiceQuestion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest({
	QuestionUpdateViewController.class
})
@ContextConfiguration(classes = QuestionUpdateViewController.class)
@DisplayName("단위 테스트 - 전공 업데이트 View Controller")
class QuestionUpdateViewControllerTest extends ControllerTest {

	@MockBean
	private AdminMajorQuestionClassifiedGetService adminMajorQuestionClassifiedGetService;

	/*
	자격증
	 */
	@MockBean
	private UserLicenseQuestionGetService userLicenseQuestionGetService;
	@MockBean
	private AdminLicenseQuestionGetService adminLicenseQuestionGetService;
	private LicenseMultipleChoiceQuestion licenseMultipleChoiceQuestion;
	private List<Question> licenseMultipleChoiceQuestions;
	private Map<QuestionCategory, List<Question>> licenseMultipleChoiceQuestionsMap;

	private QuestionCategory questionCategory;

	@BeforeEach
	void setUp() {
		licenseMultipleChoiceQuestions = new ArrayList<>();
		licenseMultipleChoiceQuestion = LicenseMultipleChoiceQuestion.makeForTest("test");
		licenseMultipleChoiceQuestions.add(licenseMultipleChoiceQuestion);


		questionCategory = QuestionCategory.DATA_MODELING;

		licenseMultipleChoiceQuestionsMap = new HashMap<>();
		licenseMultipleChoiceQuestionsMap.put(questionCategory, licenseMultipleChoiceQuestions);


	}

	@Test
	@DisplayName("업데이트 페이지")
	void updateMajorQuestionPage() throws Exception {
		// Given

		when(adminMajorQuestionClassifiedGetService.getClassifiedAllMajorMultipleChoiceQuestions())
			.thenReturn(new HashMap<>());

		// When & Then
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/question/major/multiple"))
			.andExpect(status().isOk()) // 상태 코드 200 확인
			.andExpect(view().name("question-update")) // 뷰 이름 확인
			.andExpect(model().attributeExists("classifiedQuestions")) // 모델 속성 확인
			.andDo(print());
	}


	@Test
	@DisplayName("업데이트 페이지")
	void updateLicenseQuestionPage() throws Exception {
		final String PATH = "/admin/question/license/1";
		Mockito.when(userLicenseQuestionGetService.getClassifiedLicenseMultipleChoiceQuestion(any()))
			.thenReturn(licenseMultipleChoiceQuestionsMap);

		mockMvc.perform(MockMvcRequestBuilders.get(PATH))
			.andExpect(view().name("question-update"))
			.andExpect(model().attributeExists("classifiedQuestions"))
			.andDo(print());
	}
}
