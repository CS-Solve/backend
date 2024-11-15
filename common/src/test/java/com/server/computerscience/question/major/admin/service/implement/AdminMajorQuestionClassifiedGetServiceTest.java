package com.server.computerscience.question.major.admin.service.implement;

import com.server.computerscience.ServiceIntegrationTest;
import com.server.computerscience.question.common.domain.QuestionCategory;
import com.server.computerscience.question.major.common.domain.MajorMultipleChoiceQuestion;
import com.server.computerscience.question.major.common.repository.MajorMultipleChoiceQuestionRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

@DisplayName("전공 문제 - Admin Get Service 계층 이하 통합 테스트")
class AdminMajorQuestionClassifiedServiceTest extends ServiceIntegrationTest {
    @Autowired
    private BasicAdminMajorQuestionClassifiedGetService basicAdminMajorQuestionClassifiedGetService;
    @Autowired
    private MajorMultipleChoiceQuestionRepository majorMultipleChoiceQuestionRepository;

    private MajorMultipleChoiceQuestion majorMultipleChoiceQuestion;

    @BeforeEach
    void setUp() {
        majorMultipleChoiceQuestion = MajorMultipleChoiceQuestion.makeForTest();
    }

    @Test
    @DisplayName("관리자 조회시 비허용 문제 존재 여부 조회")
    void checkMajorQuestionIsApproved() {
        //given
        majorMultipleChoiceQuestionRepository.save(majorMultipleChoiceQuestion);

        //when
        Map<QuestionCategory, List<MajorMultipleChoiceQuestion>> questions = basicAdminMajorQuestionClassifiedGetService
                .getClassifiedAllMajorQuestions();
        List<MajorMultipleChoiceQuestion> selectedCategoryQuestion = questions.get(
                majorMultipleChoiceQuestion.getQuestionCategory());

		/*then
		문제를 허용하지 않더라도 조회 되어야함
		 */
        Assertions.assertThat(selectedCategoryQuestion).contains(majorMultipleChoiceQuestion);
    }

    @Test
    @DisplayName("관리자 조회시 문제 허용 여부에 따른 정렬 여부 확인")
    void checkMajorQuestionIsSorted() {

        MajorMultipleChoiceQuestion approvedMajorQuestion = MajorMultipleChoiceQuestion.makeForTest();
        approvedMajorQuestion.toggleApproved();
        majorMultipleChoiceQuestionRepository.save(approvedMajorQuestion);

        majorMultipleChoiceQuestionRepository.save(majorMultipleChoiceQuestion);

        //when
        Map<QuestionCategory, List<MajorMultipleChoiceQuestion>> questions = basicAdminMajorQuestionClassifiedGetService
                .getClassifiedAllMajorQuestions();
        List<MajorMultipleChoiceQuestion> selectedCategoryQuestion = questions.get(
                majorMultipleChoiceQuestion.getQuestionCategory());

		/*
		then
		허용되지 않는 문제부터 나오도록 정렬되어야함
		 */
        Assertions.assertThat(selectedCategoryQuestion)
                .isSortedAccordingTo(Comparator.comparing(MajorMultipleChoiceQuestion::isIfApproved));
    }

}
