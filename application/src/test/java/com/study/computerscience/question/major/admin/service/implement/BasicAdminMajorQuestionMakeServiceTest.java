package com.study.computerscience.question.major.admin.service.implement;

import com.study.computerscience.ServiceIntegrationTest;
import com.study.computerscience.question.major.admin.dto.RequestMakeMultipleChoiceQuestionDto;
import com.study.computerscience.question.major.common.domain.MajorMultipleChoiceQuestion;
import com.study.computerscience.question.major.common.repository.MajorMultipleChoiceQuestionRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DisplayName("전공 문제 - Admin Get Service 계층 이하 통합 테스트")
class BasicAdminMajorQuestionMakeServiceTest extends ServiceIntegrationTest {

    @Autowired
    private BasicAdminMajorQuestionMakeService basicAdminMajorQuestionMakeService;
    @Autowired
    private MajorMultipleChoiceQuestionRepository majorMultipleChoiceQuestionRepository;
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
        Assertions.assertThat(majorMultipleChoiceQuestionRepository.findById(newMultipleChoiceQuestion.getId()))
                .isNotNull();
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
        Assertions.assertThat(majorMultipleChoiceQuestionRepository.findAll().size()).isEqualTo(1);
    }
}
