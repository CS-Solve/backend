package com.server.computer_science.question.major_question.admin.controller;

import com.server.computer_science.ControllerTest;
import com.server.computer_science.question.common.dto.response.ResponseClassifiedMultipleQuestionDto;
import com.server.computer_science.question.major_question.admin.service.implement.BasicAdminMajorQuestionClassifiedGetService;
import com.server.computer_science.question.major_question.common.service.MajorQuestionClassifiedGetService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest({
        AdminMajorQuestionViewController.class
})
@DisplayName("단위 테스트 - 전공 개념 Admin View Controller")
class AdminMajorQuestionViewControllerTest extends ControllerTest {

    @MockBean
    @Qualifier("basicAdminMajorQuestionClassifiedGetService")  // Qualifier 지정
    private MajorQuestionClassifiedGetService majorQuestionClassifiedGetService;

    @Test
    @DisplayName("전공 문제 update 페이지 View")
    void testUpdateQuestionPage() throws Exception {
        // Given
        List<ResponseClassifiedMultipleQuestionDto> classifiedQuestions = new ArrayList<>(); // 테스트 데이터 예시
        when(majorQuestionClassifiedGetService.getClassifiedAllMajorQuestions())
                .thenReturn(classifiedQuestions);

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/question/update"))
                .andExpect(status().isOk()) // 상태 코드 200 확인
                .andExpect(view().name("major-question-update")) // 뷰 이름 확인
                .andExpect(model().attributeExists("classifiedQuestions")) // 모델 속성 확인
                .andExpect(model().attribute("classifiedQuestions", classifiedQuestions)) // 모델 값 검증
                .andDo(print());
    }
}