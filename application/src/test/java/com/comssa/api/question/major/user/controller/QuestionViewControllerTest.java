package com.comssa.api.question.major.user.controller;


import com.comssa.api.ViewControllerTest;
import com.comssa.api.question.controller.view.QuestionViewController;
import com.comssa.api.question.service.rest.license.implement.LicenseSessionService;
import com.comssa.api.question.service.rest.license.implement.UserLicenseQuestionGetService;
import com.comssa.api.question.service.rest.major.implement.UserMajorQuestionClassifiedGetService;
import com.comssa.api.question.service.view.HtmlTagService;
import com.comssa.persistence.question.domain.common.Question;
import com.comssa.persistence.question.domain.common.QuestionCategory;
import com.comssa.persistence.question.domain.common.QuestionLevel;
import com.comssa.persistence.question.domain.license.LicenseCategory;
import com.comssa.persistence.question.domain.license.LicenseMultipleChoiceQuestion;
import com.comssa.persistence.question.domain.license.LicenseSession;
import com.comssa.persistence.question.domain.major.MajorMultipleChoiceQuestion;
import com.comssa.persistence.question.dto.common.response.ResponseClassifiedQuestionDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(QuestionViewController.class)
@ContextConfiguration(classes = QuestionViewController.class)
@DisplayName("단위 테스트 - 문제 View Controller")
class QuestionViewControllerTest extends ViewControllerTest {
	private final List<String> levels = Arrays.stream(QuestionLevel.values())
		.map(QuestionLevel::getKorean)
		.collect(Collectors.toList());
	private final List<String> categories = Arrays.stream(QuestionCategory.values())
		.filter(QuestionCategory::isCanBeShownInMajor)
		.map(QuestionCategory::getKorean)
		.collect(Collectors.toList());
	private final Map<QuestionCategory, List<Question>> map = new HashMap<>();
	boolean multipleChoice = true;
	@MockBean
	private UserMajorQuestionClassifiedGetService userMajorQuestionClassifiedGetService;


	/*
	License관련
	 */
	@MockBean
	private UserLicenseQuestionGetService userLicenseQuestionGetService;
	@MockBean
	private List<Question> licenseMultipleChoiceQuestions;
	@MockBean
	private HtmlTagService htmlTagService;
	@MockBean
	private LicenseSessionService licenseSessionService;

	private Map<QuestionCategory, List<Question>> licenseMultipleChoiceQuestionsMap;
	private LicenseSession licenseSession;
	private List<ResponseClassifiedQuestionDto> responseClassifiedQuestionDtos;
	private LicenseCategory licenseCategory;
	private LicenseMultipleChoiceQuestion licenseMultipleChoiceQuestion;
	private QuestionCategory questionCategory;

	@BeforeEach
	void setUp() {
		responseClassifiedQuestionDtos = new ArrayList<>();
		MajorMultipleChoiceQuestion majorMultipleChoiceQuestion = MajorMultipleChoiceQuestion.makeForTest();
		List<Question> majorMultipleChoiceQuestions = Collections.singletonList(
			majorMultipleChoiceQuestion);
		ResponseClassifiedQuestionDto responseClassifiedQuestionDto =
			ResponseClassifiedQuestionDto.from(majorMultipleChoiceQuestion.getQuestionCategory(),
				majorMultipleChoiceQuestions);
		responseClassifiedQuestionDtos.add(responseClassifiedQuestionDto);
		map.put(majorMultipleChoiceQuestion.getQuestionCategory(), majorMultipleChoiceQuestions);


		/**
		 * License관련
		 */
		licenseMultipleChoiceQuestions = new ArrayList<>();
		licenseMultipleChoiceQuestion = LicenseMultipleChoiceQuestion.makeForTest("test");
		licenseMultipleChoiceQuestions.add(licenseMultipleChoiceQuestion);


		questionCategory = QuestionCategory.DATA_MODELING;

		licenseMultipleChoiceQuestionsMap = new HashMap<>();
		licenseMultipleChoiceQuestionsMap.put(questionCategory, licenseMultipleChoiceQuestions);

		responseClassifiedQuestionDtos = new ArrayList<>();
		responseClassifiedQuestionDtos.add(
			ResponseClassifiedQuestionDto.from(questionCategory, licenseMultipleChoiceQuestions));
		licenseCategory = LicenseCategory.SQLD;

		licenseSession = LicenseSession.from("test", licenseCategory);
	}

	@Test
	@DisplayName("전공 문제 조회")
	void majorQuestionPage() throws Exception {
		final String path = "/question/major";
		Mockito.when(userMajorQuestionClassifiedGetService.getApprovedClassifiedMajorMultipleChoiceQuestions(any()))
			.thenReturn(map);

		mockMvc.perform(MockMvcRequestBuilders.get(path)
				.param("levels", levels.toArray(new String[0]))
				.param("categories", categories.toArray(new String[0]))
				.param("multipleChoice", String.valueOf(multipleChoice)))
			.andExpect(status().isOk())
			.andExpect(view().name("question"))
			.andExpect(model().attributeExists("questions"))
			.andDo(print());
	}

	@Test
	@DisplayName("자격증 문제 조회")
	void
	licenseQuestionPage() throws Exception {
		final String PATH = "/question/license/1";
		Mockito.when(licenseSessionService.getLicenseSessionById(any())).thenReturn(licenseSession);
		Mockito.when(userLicenseQuestionGetService.getClassifiedLicenseMultipleChoiceQuestion(any()))
			.thenReturn(licenseMultipleChoiceQuestionsMap);

		mockMvc.perform(MockMvcRequestBuilders.get(PATH))
			.andExpect(view().name("question"))
			.andExpect(model().attributeExists("questions"))
			.andDo(print());
	}
}
