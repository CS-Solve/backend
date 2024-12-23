package com.comssa.api.question.major.admin.service.implement;

import com.comssa.api.ServiceIntegrationTest;
import com.comssa.api.question.service.rest.major.implement.BasicAdminMajorQuestionClassifiedGetService;
import com.comssa.persistence.question.domain.common.Question;
import com.comssa.persistence.question.domain.common.QuestionCategory;
import com.comssa.persistence.question.domain.major.MajorMultipleChoiceQuestion;
import com.comssa.persistence.question.repository.jpa.MajorMultipleChoiceQuestionJpaRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

@DisplayName("전공 문제 - Admin Get Service 계층 이하 통합 테스트")
class AdminMajorQuestionClassifiedGetServiceTest extends ServiceIntegrationTest {
	@Autowired
	private BasicAdminMajorQuestionClassifiedGetService basicAdminMajorQuestionClassifiedGetService;
	@Autowired
	private MajorMultipleChoiceQuestionJpaRepository majorMultipleChoiceQuestionJpaRepository;

	private MajorMultipleChoiceQuestion majorMultipleChoiceQuestion;

	@BeforeEach
	void setUp() {
		majorMultipleChoiceQuestionJpaRepository.deleteAll();
		majorMultipleChoiceQuestion = MajorMultipleChoiceQuestion.makeForTest();
	}

	@Test
	@DisplayName("관리자 조회시 허용하지 않은 문제도 존재해야한다.")
	void checkMajorQuestionIsApproved() {
		//given
		majorMultipleChoiceQuestionJpaRepository.save(majorMultipleChoiceQuestion);

		//when
		Map<QuestionCategory, List<Question>> questions = basicAdminMajorQuestionClassifiedGetService
			.getClassifiedAllMajorMultipleChoiceQuestions();
		List<Question> selectedCategoryQuestion = questions.get(
			majorMultipleChoiceQuestion.getQuestionCategory());

		/*
		then
		문제를 허용하기 전이더라도 조회 되어야함
		 */
		Assertions.assertThat(selectedCategoryQuestion.get(0).getId()).isEqualTo(majorMultipleChoiceQuestion.getId());
	}

	@Test
	@DisplayName("관리자 조회시 문제 허용 여부에 따른 정렬 여부 확인")
	void checkMajorQuestionIsSorted() {

		MajorMultipleChoiceQuestion approvedMajorQuestion = MajorMultipleChoiceQuestion.makeForTest();
		approvedMajorQuestion.toggleApproved();
		majorMultipleChoiceQuestionJpaRepository.save(approvedMajorQuestion);

		majorMultipleChoiceQuestionJpaRepository.save(majorMultipleChoiceQuestion);

		//when
		Map<QuestionCategory, List<Question>> questions = basicAdminMajorQuestionClassifiedGetService
			.getClassifiedAllMajorMultipleChoiceQuestions();
		List<Question> selectedCategoryQuestion = questions.get(
			majorMultipleChoiceQuestion.getQuestionCategory());

		/*
		then
		허용되지 않는 문제부터 나오도록 정렬되어야함
		 */
		Assertions.assertThat(selectedCategoryQuestion)
			.isSortedAccordingTo(Comparator.comparing(Question::isIfApproved));
	}

}
