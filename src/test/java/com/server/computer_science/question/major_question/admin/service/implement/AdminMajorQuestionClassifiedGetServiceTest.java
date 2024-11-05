package com.server.computer_science.question.major_question.admin.service.implement;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.server.computer_science.ServiceIntegrationTest;
import com.server.computer_science.question.common.domain.QuestionCategory;
import com.server.computer_science.question.major_question.common.domain.MajorMultipleChoiceQuestion;
import com.server.computer_science.question.major_question.common.repository.MajorQuestionRepository;

@DisplayName("전공 문제 - Admin Get Service 계층 이하 통합 테스트")
class AdminMajorQuestionClassifiedServiceTest extends ServiceIntegrationTest {
	@Autowired
	private BasicAdminMajorQuestionClassifiedGetService basicAdminMajorQuestionClassifiedGetService;
	@Autowired
	private MajorQuestionRepository majorQuestionRepository;

	private MajorMultipleChoiceQuestion majorMultipleChoiceQuestion;

	@BeforeEach
	void setUp() {
		majorMultipleChoiceQuestion = MajorMultipleChoiceQuestion.makeForTest();
	}

	@Test
	@DisplayName("관리자 조회시 비허용 문제 존재 여부 조회")
	void checkMajorQuestionIsApproved() {
		//given
		majorQuestionRepository.save(majorMultipleChoiceQuestion);

		//when
		Map<QuestionCategory, List<MajorMultipleChoiceQuestion>> questions = basicAdminMajorQuestionClassifiedGetService.getClassifiedAllMajorQuestions();
		List<MajorMultipleChoiceQuestion> selectedCategoryQuestion = questions.get(
			majorMultipleChoiceQuestion.getQuestionCategory());

        /*
        문제를 허용하지 않더라도 조회 되어야함
         */
		//then
		Assertions.assertThat(selectedCategoryQuestion).contains(majorMultipleChoiceQuestion);
	}

	@Test
	@DisplayName("관리자 조회시 문제 허용 여부에 따른 정렬 여부 확인")
	void checkMajorQuestionIsSorted() {
		//given
        /*
        허용된 문제를 먼저 저장
         */
		MajorMultipleChoiceQuestion approvedMajorQuestion = MajorMultipleChoiceQuestion.makeForTest();
		approvedMajorQuestion.toggleApproved();
		majorQuestionRepository.save(approvedMajorQuestion);

		majorQuestionRepository.save(majorMultipleChoiceQuestion);

		//when
		Map<QuestionCategory, List<MajorMultipleChoiceQuestion>> questions = basicAdminMajorQuestionClassifiedGetService.getClassifiedAllMajorQuestions();
		List<MajorMultipleChoiceQuestion> selectedCategoryQuestion = questions.get(
			majorMultipleChoiceQuestion.getQuestionCategory());

        /*
        허용되지 않는 문제부터 나오도록 정렬되어야함
         */
		//then
		Assertions.assertThat(selectedCategoryQuestion)
			.isSortedAccordingTo(Comparator.comparing(MajorMultipleChoiceQuestion::isIfApproved));
	}

}
