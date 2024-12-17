package com.comssa.api.question.major.user.service.implement;

import com.comssa.api.ServiceIntegrationTest;
import com.comssa.api.question.service.rest.major.implement.UserMajorQuestionClassifiedGetService;
import com.comssa.persistence.question.domain.common.Question;
import com.comssa.persistence.question.domain.common.QuestionCategory;
import com.comssa.persistence.question.domain.common.QuestionLevel;
import com.comssa.persistence.question.domain.major.MajorMultipleChoiceQuestion;
import com.comssa.persistence.question.dto.major.request.RequestGetQuestionByCategoryAndLevelDto;
import com.comssa.persistence.question.repository.MajorMultipleChoiceQuestionRepository;
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

    private final List<String> levels = Arrays.stream(QuestionLevel.values())
            .map(QuestionLevel::getKorean)
            .collect(Collectors.toList());
    private final List<String> majorCategories = Arrays.stream(QuestionCategory.values())
            .filter(QuestionCategory::isCanBeShownInMajor)
            .map(QuestionCategory::getKorean)
            .collect(Collectors.toList());
    @Autowired
    private UserMajorQuestionClassifiedGetService userMajorQuestionClassifiedGetService;
    @Autowired
    private MajorMultipleChoiceQuestionRepository majorMultipleChoiceQuestionRepository;
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
        majorMultipleChoiceQuestionRepository.save(majorMultipleChoiceQuestion);
        RequestGetQuestionByCategoryAndLevelDto allQuestionRequestDto =
                RequestGetQuestionByCategoryAndLevelDto.fromKorean(majorCategories, levels);

        //when
        Map<QuestionCategory, List<Question>> questions =
                userMajorQuestionClassifiedGetService.getApprovedClassifiedMajorMultipleChoiceQuestions(
                        allQuestionRequestDto);
        List<Question> selectedCategoryQuestions = questions.get(
                majorMultipleChoiceQuestion.getQuestionCategory());

        //then
        Assertions.assertThat(selectedCategoryQuestions).contains(majorMultipleChoiceQuestion);
    }

}
