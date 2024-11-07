package com.server.computerscience.question.major.user.service.implement;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.server.computerscience.ServiceIntegrationTest;
import com.server.computerscience.question.common.domain.QuestionCategory;
import com.server.computerscience.question.common.domain.QuestionLevel;
import com.server.computerscience.question.major.common.domain.MajorMultipleChoiceQuestion;
import com.server.computerscience.question.major.common.repository.MajorQuestionRepository;
import com.server.computerscience.question.major.user.dto.request.RequestGetQuestionByCategoryAndLevelDto;

@DisplayName("전공 문제 - Service 계층 이하 통합 테스트")
class MajorQuestionClassifiedGetServiceTest extends ServiceIntegrationTest {

	private final List<String> levels = Arrays.stream(QuestionLevel.values())
		.map(QuestionLevel::getKorean)
		.collect(Collectors.toList());
	private final List<String> majorCategories = Arrays.stream(QuestionCategory.values())
		.filter(QuestionCategory::isCanBeShownInMajor)
		.map(QuestionCategory::getKorean)
		.collect(Collectors.toList());
	@Autowired
	private BasicMajorQuestionClassifiedGetService basicMajorQuestionClassifiedGetService;
	@Autowired
	private MajorQuestionRepository majorQuestionRepository;
	private MajorMultipleChoiceQuestion majorMultipleChoiceQuestion;

	@BeforeEach
	void setUp() {
		majorMultipleChoiceQuestion = MajorMultipleChoiceQuestion.makeForTest();

	}

	@Test
	@DisplayName("모든 카테고리 선택 후 허용된 객관식 문제 존재 확인")
	void getApprovedClassifiedMajorMultipleChoiceQuestions() {
		//given
		majorMultipleChoiceQuestion.toggleApproved();
		majorQuestionRepository.save(majorMultipleChoiceQuestion);
		RequestGetQuestionByCategoryAndLevelDto allQuestionRequestDto =
			RequestGetQuestionByCategoryAndLevelDto.fromKorean(majorCategories, levels);

		//when
		Map<QuestionCategory, List<MajorMultipleChoiceQuestion>> questions =
			basicMajorQuestionClassifiedGetService.getApprovedClassifiedMajorMultipleChoiceQuestions(
				allQuestionRequestDto);
		List<MajorMultipleChoiceQuestion> selectedCategoryQuestions = questions.get(
			majorMultipleChoiceQuestion.getQuestionCategory());

		//then
		Assertions.assertThat(selectedCategoryQuestions).contains(majorMultipleChoiceQuestion);
	}

	@Test
	@DisplayName("모든 카테고리 선택 후 허용된 주관식 문제 존재 확인")
	void getApprovedClassifiedShortAnsweredMajorQuestions() {
		majorMultipleChoiceQuestion.toggleApproved();
		//문제 주관식 가능 여부 허용
		majorMultipleChoiceQuestion.toggleCanBeShortAnswered();
		majorQuestionRepository.save(majorMultipleChoiceQuestion);
		RequestGetQuestionByCategoryAndLevelDto allQuestionRequestDto =
			RequestGetQuestionByCategoryAndLevelDto.fromKorean(majorCategories, levels);

		//when
		Map<QuestionCategory, List<MajorMultipleChoiceQuestion>> questions =
			basicMajorQuestionClassifiedGetService.getApprovedClassifiedShortAnsweredMajorQuestions(
				allQuestionRequestDto);
		List<MajorMultipleChoiceQuestion> selectedCategoryQuestions = questions.get(
			majorMultipleChoiceQuestion.getQuestionCategory());

		//then
		Assertions.assertThat(selectedCategoryQuestions.get(0).isCanBeShortAnswered()).isTrue();
	}
}
