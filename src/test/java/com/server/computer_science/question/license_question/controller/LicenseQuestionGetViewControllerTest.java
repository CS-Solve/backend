package com.server.computer_science.question.license_question.controller;

import com.server.computer_science.ControllerTest;
import com.server.computer_science.question.common.domain.QuestionCategory;
import com.server.computer_science.question.common.dto.response.ResponseClassifiedMultipleQuestionDto;
import com.server.computer_science.question.license_question.domain.LicenseCategory;
import com.server.computer_science.question.license_question.domain.LicenseMultipleChoiceQuestion;
import com.server.computer_science.question.license_question.domain.LicenseSession;
import com.server.computer_science.question.license_question.dto.response.ResponseLicenseSessionDto;
import com.server.computer_science.question.license_question.service.LicenseQuestionGetService;
import com.server.computer_science.question.license_question.service.LicenseSessionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@DisplayName("단위 테스트 - 자격증 Get View Controller")
@WebMvcTest(LicenseQuestionGetViewController.class)
class LicenseQuestionGetViewControllerTest extends ControllerTest {

    @MockBean
    private LicenseQuestionGetService licenseQuestionGetService;
    @MockBean
    private LicenseSessionService licenseSessionService;
    private LicenseSession licenseSession;
    private LicenseMultipleChoiceQuestion licenseMultipleChoiceQuestion;
    private List<LicenseMultipleChoiceQuestion> licenseMultipleChoiceQuestions;
    private Map<QuestionCategory,List<LicenseMultipleChoiceQuestion>>  licenseMultipleChoiceQuestionsMap;
    private List<ResponseClassifiedMultipleQuestionDto> responseClassifiedMultipleQuestionDtos;
    private LicenseCategory licenseCategory;
    private QuestionCategory questionCategory;

    @BeforeEach
    void setUp() {
        licenseMultipleChoiceQuestions = new ArrayList<>();
        licenseMultipleChoiceQuestion = LicenseMultipleChoiceQuestion.makeForTest("test");
        licenseMultipleChoiceQuestions.add(licenseMultipleChoiceQuestion);

        licenseCategory = LicenseCategory.SQLD;
        questionCategory = QuestionCategory.DATA_MODELING;

        licenseMultipleChoiceQuestionsMap = new HashMap<>();
        licenseMultipleChoiceQuestionsMap.put(questionCategory,licenseMultipleChoiceQuestions);

        responseClassifiedMultipleQuestionDtos = new ArrayList<>();
        responseClassifiedMultipleQuestionDtos.add(ResponseClassifiedMultipleQuestionDto.forUser(questionCategory,licenseMultipleChoiceQuestions));

        licenseSession = LicenseSession.from("test", licenseCategory);
    }

    @Test
    @DisplayName("조회")
    void licenseQuestionPage() throws Exception {
        final String PATH ="/question/license/1";
        Mockito.when(licenseSessionService.getLicenseSessionById(any())).thenReturn(licenseSession);
        Mockito.when(licenseQuestionGetService.getClassifiedLicenseMultipleChoiceQuestion(any())).thenReturn(licenseMultipleChoiceQuestionsMap);

        mockMvc.perform(MockMvcRequestBuilders.get(PATH))
                .andExpect(view().name("license-question"))
                .andExpect(model().attributeExists("licenseSession"))
                .andExpect(model().attributeExists("questions"))
                .andDo(print());
    }

    @Test
    @DisplayName("업데이트")
    void updatelicenseQuestionPage() throws Exception {
        final String PATH ="/admin/question/license/update/1";
        Mockito.when(licenseQuestionGetService.getClassifiedLicenseMultipleChoiceQuestion(any())).thenReturn(licenseMultipleChoiceQuestionsMap);

        mockMvc.perform(MockMvcRequestBuilders.get(PATH))
                .andExpect(view().name("license-question-update"))
                .andExpect(model().attributeExists("classifiedQuestions"))
                .andDo(print());
    }
}