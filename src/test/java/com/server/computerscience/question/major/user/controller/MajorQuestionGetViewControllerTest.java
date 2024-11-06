package com.server.computerscience.question.major.user.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.server.computerscience.ControllerTest;
import com.server.computerscience.question.common.domain.QuestionCategory;
import com.server.computerscience.question.common.domain.QuestionLevel;
import com.server.computerscience.question.common.dto.response.ResponseClassifiedMultipleQuestionDto;
import com.server.computerscience.question.major.common.domain.MajorMultipleChoiceQuestion;
import com.server.computerscience.question.major.user.service.implement.BasicMajorQuestionClassifiedGetService;

@WebMvcTest(MajorQuestionGetViewController.class)
@DisplayName("단위 테스트 - 전공 문제 View Controller")
class MajorQuestionGetViewControllerTest extends ControllerTest {
	boolean multipleChoice = true;
	@MockBean
	private BasicMajorQuestionClassifiedGetService basicMajorQuestionClassifiedGetService;
	private List<ResponseClassifiedMultipleQuestionDto> responseClassifiedMultipleQuestionDtos;
	private List<String> levels = Arrays.stream(QuestionLevel.values())
		.map(QuestionLevel::getKorean)
		.collect(Collectors.toList());
	private List<String> categories = Arrays.stream(QuestionCategory.values())
		.filter(QuestionCategory::isCanBeShownInMajor)
		.map(QuestionCategory::getKorean)
		.collect(Collectors.toList());
	private Map<QuestionCategory, List<MajorMultipleChoiceQuestion>> map = new HashMap<>();

	@BeforeEach
	void setUp() {
		responseClassifiedMultipleQuestionDtos = new ArrayList<>();
		MajorMultipleChoiceQuestion majorMultipleChoiceQuestion = MajorMultipleChoiceQuestion.makeForTest();
		List<MajorMultipleChoiceQuestion> majorMultipleChoiceQuestions = Arrays.asList(majorMultipleChoiceQuestion);
		ResponseClassifiedMultipleQuestionDto responseClassifiedMultipleQuestionDto =
			ResponseClassifiedMultipleQuestionDto.forUser(majorMultipleChoiceQuestion.getQuestionCategory(),
				majorMultipleChoiceQuestions);
		responseClassifiedMultipleQuestionDtos.add(responseClassifiedMultipleQuestionDto);
		map.put(majorMultipleChoiceQuestion.getQuestionCategory(), majorMultipleChoiceQuestions);
	}

	@Test
	@DisplayName("객관식 문제 조회")
	void majorQuestionPage() throws Exception {
		final String path = "/question/major";
		Mockito.when(basicMajorQuestionClassifiedGetService.getApprovedClassifiedMajorMultipleChoiceQuestions(any()))
			.thenReturn(map);

		mockMvc.perform(MockMvcRequestBuilders.get(path)
				.param("levels", levels.toArray(new String[0]))
				.param("categories", categories.toArray(new String[0]))
				.param("multipleChoice", String.valueOf(multipleChoice)))
			.andExpect(status().isOk())
			.andExpect(view().name("major-question"))
			.andExpect(model().attributeExists("questions"))
			.andDo(print());
	}

}
