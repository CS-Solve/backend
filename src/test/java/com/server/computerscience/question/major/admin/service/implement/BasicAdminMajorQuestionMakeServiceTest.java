package com.server.computerscience.question.major.admin.service.implement;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.server.computerscience.ServiceIntegrationTest;
import com.server.computerscience.question.major.admin.dto.RequestMakeMultipleChoiceQuestionDto;
import com.server.computerscience.question.major.common.domain.MajorMultipleChoiceQuestion;
import com.server.computerscience.question.major.common.repository.MajorQuestionRepository;

@DisplayName("전공 문제 - Admin Get Service 계층 이하 통합 테스트")
class BasicAdminMajorQuestionMakeServiceTest extends ServiceIntegrationTest {

	@Autowired
	private BasicAdminMajorQuestionMakeService basicAdminMajorQuestionMakeService;
	@Autowired
	private MajorQuestionRepository majorQuestionRepository;
	private MajorMultipleChoiceQuestion majorMultipleChoiceQuestion;

	@BeforeEach
	void setUp() {
		majorMultipleChoiceQuestion = MajorMultipleChoiceQuestion.makeForTest();
	}

	@Test
	@DisplayName("객관식 문제 단체로 생성")
	void makeMajorMultipleChoiceQuestions() {
		//given
		List<RequestMakeMultipleChoiceQuestionDto> makeMultipleChoiceQuestionDtoList =
			List.of(RequestMakeMultipleChoiceQuestionDto.from(majorMultipleChoiceQuestion,
				majorMultipleChoiceQuestion.getQuestionChoices()));

		//when
		List<MajorMultipleChoiceQuestion> newQuestions = basicAdminMajorQuestionMakeService.makeMultipleChoiceQuestions(
			makeMultipleChoiceQuestionDtoList);
		MajorMultipleChoiceQuestion newMultipleChoiceQuestion = newQuestions.get(0);

		//then
		/*
		DB 저장 확인
		 */
		Assertions.assertThat(majorQuestionRepository.findById(newMultipleChoiceQuestion.getId())).isNotNull();
	}

	@Test
	@DisplayName("단체로 중복된 본문이 있는 문제 저장 - 한 개만 저장됨")
	void makeMajorMultipleChoiceQuestionsDuplicateContent() {
		//given
		List<RequestMakeMultipleChoiceQuestionDto> makeMultipleChoiceQuestionDtoList =
			List.of(RequestMakeMultipleChoiceQuestionDto.from(majorMultipleChoiceQuestion,
					majorMultipleChoiceQuestion.getQuestionChoices()),
				RequestMakeMultipleChoiceQuestionDto.from(majorMultipleChoiceQuestion,
					majorMultipleChoiceQuestion.getQuestionChoices())
			);
		//when
		List<MajorMultipleChoiceQuestion> newQuestions = basicAdminMajorQuestionMakeService.makeMultipleChoiceQuestions(
			makeMultipleChoiceQuestionDtoList);
		/*
		then
        DB에 저장되어야하는 엔티티는 하나
		 */
		Assertions.assertThat(majorQuestionRepository.findAll().size()).isEqualTo(1);
	}
}