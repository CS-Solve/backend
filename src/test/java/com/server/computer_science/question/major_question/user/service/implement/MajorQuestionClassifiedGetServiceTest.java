package com.server.computer_science.question.major_question.user.service.implement;

import com.server.computer_science.ServiceIntegrationTest;
import com.server.computer_science.question.common.domain.QuestionCategory;
import com.server.computer_science.question.common.domain.QuestionLevel;
import com.server.computer_science.question.major_question.common.domain.MajorMultipleChoiceQuestion;
import com.server.computer_science.question.major_question.common.repository.MajorQuestionRepository;
import com.server.computer_science.question.major_question.user.dto.request.RequestGetQuestionByCategoryAndLevelDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@DisplayName("전공 문제 - Service 계층 이하 통합 테스트")
class MajorQuestionClassifiedGetServiceTest extends ServiceIntegrationTest {

    @Autowired
    private BasicMajorQuestionClassifiedGetService basicMajorQuestionClassifiedGetService;

    @Autowired
    private MajorQuestionRepository majorQuestionRepository;

    private MajorMultipleChoiceQuestion majorMultipleChoiceQuestion;

    private List<String> levels = Arrays.stream(QuestionLevel.values())
            .map(QuestionLevel::getKorean)
            .collect(Collectors.toList());;
    private List<String> majorCategories = Arrays.stream(QuestionCategory.values())
            .filter(QuestionCategory::isCanBeShownInMajor)
            .map(QuestionCategory::getKorean)
            .collect(Collectors.toList());

    @BeforeEach
    void setUp() {
        majorMultipleChoiceQuestion = MajorMultipleChoiceQuestion.makeForTest();
        MajorMultipleChoiceQuestion majorMultipleChoiceQuestion = MajorMultipleChoiceQuestion.makeForTest();

    }

    @Test
    @DisplayName("모든 카테고리 선택 후 허용된 특정 문제 존재 확인")
    void getApprovedClassifiedMajorMultipleChoiceQuestions() {
        //given
        majorMultipleChoiceQuestion.toggleApproved();
        majorQuestionRepository.save(majorMultipleChoiceQuestion);
        RequestGetQuestionByCategoryAndLevelDto allQuestionRequestDto =
                RequestGetQuestionByCategoryAndLevelDto.fromKorean(majorCategories,levels);

        //when
        Map<QuestionCategory,List<MajorMultipleChoiceQuestion>> questions =
                basicMajorQuestionClassifiedGetService.getApprovedClassifiedMajorMultipleChoiceQuestions(allQuestionRequestDto);
        List<MajorMultipleChoiceQuestion> selectedCategoryQuestions = questions.get(majorMultipleChoiceQuestion.getQuestionCategory());

        //then
        Assertions.assertThat(selectedCategoryQuestions).contains(majorMultipleChoiceQuestion);
    }

    @Test
    void getApprovedClassifiedShortAnsweredMajorQuestions() {
    }
}