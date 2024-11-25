package com.comssa.api.question.major.user.controller;


import com.comssa.api.ViewControllerTest;
import com.comssa.api.question.major.user.service.implement.UserMajorQuestionClassifiedGetService;
import com.comssa.persistence.question.common.domain.QuestionCategory;
import com.comssa.persistence.question.common.domain.QuestionLevel;
import com.comssa.persistence.question.common.dto.response.ResponseClassifiedQuestionDto;
import com.comssa.persistence.question.major.domain.common.MajorMultipleChoiceQuestion;
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

@WebMvcTest(MajorQuestionGetViewController.class)
@ContextConfiguration(classes = MajorQuestionGetViewController.class)
@DisplayName("단위 테스트 - 전공 문제 View Controller")
class MajorQuestionGetViewControllerTest extends ViewControllerTest {
	private final List<String> levels = Arrays.stream(QuestionLevel.values())
		.map(QuestionLevel::getKorean)
		.collect(Collectors.toList());
	private final List<String> categories = Arrays.stream(QuestionCategory.values())
		.filter(QuestionCategory::isCanBeShownInMajor)
		.map(QuestionCategory::getKorean)
		.collect(Collectors.toList());
	private final Map<QuestionCategory, List<MajorMultipleChoiceQuestion>> map = new HashMap<>();
	boolean multipleChoice = true;
	@MockBean
	private UserMajorQuestionClassifiedGetService userMajorQuestionClassifiedGetService;
	private List<ResponseClassifiedQuestionDto> responseClassifiedQuestionDtos;

	@BeforeEach
	void setUp() {
		responseClassifiedQuestionDtos = new ArrayList<>();
		MajorMultipleChoiceQuestion majorMultipleChoiceQuestion = MajorMultipleChoiceQuestion.makeForTest();
		List<MajorMultipleChoiceQuestion> majorMultipleChoiceQuestions = Collections.singletonList(
			majorMultipleChoiceQuestion);
		ResponseClassifiedQuestionDto responseClassifiedQuestionDto =
			ResponseClassifiedQuestionDto.multipleQuestionForUser(majorMultipleChoiceQuestion.getQuestionCategory(),
				majorMultipleChoiceQuestions);
		responseClassifiedQuestionDtos.add(responseClassifiedQuestionDto);
		map.put(majorMultipleChoiceQuestion.getQuestionCategory(), majorMultipleChoiceQuestions);
	}

	@Test
	@DisplayName("객관식 문제 조회")
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

}
