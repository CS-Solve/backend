package com.comssa.api.question.license;


import com.comssa.api.ViewControllerTest;
import com.comssa.api.question.controller.view.LicenseQuestionGetViewController;
import com.comssa.api.question.service.license.implement.AdminLicenseQuestionGetService;
import com.comssa.api.question.service.license.implement.LicenseSessionService;
import com.comssa.api.question.service.license.implement.UserLicenseQuestionGetService;
import com.comssa.persistence.question.common.domain.QuestionCategory;
import com.comssa.persistence.question.common.dto.response.ResponseClassifiedQuestionDto;
import com.comssa.persistence.question.license.domain.LicenseCategory;
import com.comssa.persistence.question.license.domain.LicenseMultipleChoiceQuestion;
import com.comssa.persistence.question.license.domain.LicenseSession;
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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@DisplayName("단위 테스트 - 자격증 Get View Controller")
@WebMvcTest(LicenseQuestionGetViewController.class)
@ContextConfiguration(classes = LicenseQuestionGetViewController.class)
class LicenseQuestionGetViewControllerTest extends ViewControllerTest {

	@MockBean
	private UserLicenseQuestionGetService userLicenseQuestionGetService;
	@MockBean
	private AdminLicenseQuestionGetService adminLicenseQuestionGetService;
	@MockBean
	private LicenseSessionService licenseSessionService;
	private LicenseSession licenseSession;
	private LicenseMultipleChoiceQuestion licenseMultipleChoiceQuestion;
	private List<LicenseMultipleChoiceQuestion> licenseMultipleChoiceQuestions;
	private Map<QuestionCategory, List<LicenseMultipleChoiceQuestion>> licenseMultipleChoiceQuestionsMap;
	private List<ResponseClassifiedQuestionDto> responseClassifiedQuestionDtos;
	private LicenseCategory licenseCategory;
	private QuestionCategory questionCategory;

	@BeforeEach
	void setUp() {
		licenseMultipleChoiceQuestions = new ArrayList<>();
		licenseMultipleChoiceQuestion = LicenseMultipleChoiceQuestion.makeForTest("test");
		licenseMultipleChoiceQuestions.add(licenseMultipleChoiceQuestion);

		licenseCategory = LicenseCategory.SQLD;
		questionCategory = QuestionCategory.DATA_MODELING;

		licenseMultipleChoiceQuestionsMap = new HashMap<>();
		licenseMultipleChoiceQuestionsMap.put(questionCategory, licenseMultipleChoiceQuestions);

		responseClassifiedQuestionDtos = new ArrayList<>();
		responseClassifiedQuestionDtos.add(
			ResponseClassifiedQuestionDto.getQuestions(questionCategory, licenseMultipleChoiceQuestions));

		licenseSession = LicenseSession.from("test", licenseCategory);
	}

	@Test
	@DisplayName("조회")
	void licenseQuestionPage() throws Exception {
		final String PATH = "/question/license/1";
		Mockito.when(licenseSessionService.getLicenseSessionById(any())).thenReturn(licenseSession);
		Mockito.when(userLicenseQuestionGetService.getClassifiedLicenseMultipleChoiceQuestion(any()))
			.thenReturn(licenseMultipleChoiceQuestionsMap);

		mockMvc.perform(MockMvcRequestBuilders.get(PATH))
			.andExpect(view().name("question"))
			.andExpect(model().attributeExists("questionSession"))
			.andExpect(model().attributeExists("questions"))
			.andDo(print());
	}

	@Test
	@DisplayName("업데이트")
	void updateLicenseQuestionPage() throws Exception {
		final String PATH = "/admin/question/license/update/1";
		Mockito.when(userLicenseQuestionGetService.getClassifiedLicenseMultipleChoiceQuestion(any()))
			.thenReturn(licenseMultipleChoiceQuestionsMap);

		mockMvc.perform(MockMvcRequestBuilders.get(PATH))
			.andExpect(view().name("question-update"))
			.andExpect(model().attributeExists("classifiedQuestions"))
			.andDo(print());
	}
}
