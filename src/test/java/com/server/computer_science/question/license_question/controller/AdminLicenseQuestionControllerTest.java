package com.server.computer_science.question.license_question.controller;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.server.computer_science.ControllerTest;
import com.server.computer_science.question.common.dto.response.ResponseQuestionDto;
import com.server.computer_science.question.license_question.domain.LicenseCategory;
import com.server.computer_science.question.license_question.domain.LicenseMultipleChoiceQuestion;
import com.server.computer_science.question.license_question.dto.request.RequestMakeLicenseMultipleChoiceQuestionDto;
import com.server.computer_science.question.license_question.service.AdminLicenseMuiltipleChoiceQuestionUpdateService;
import com.server.computer_science.question.license_question.service.LicenseQuestionMakeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(AdminLicenseQuestionController.class)
@DisplayName("단위 테스트 - 자격증 Admin Controller")
class AdminLicenseQuestionControllerTest extends ControllerTest {
    private final String tag = "자격증 문제";

    @MockBean
    private LicenseQuestionMakeService licenseQuestionMakeService;
    @MockBean
    private AdminLicenseMuiltipleChoiceQuestionUpdateService adminLicenseMuiltipleChoiceQuestionUpdateService;

    private final String baseApiUrl = "/admin/question/license";
    private LicenseMultipleChoiceQuestion licenseMultipleChoiceQuestion;
    private List<LicenseMultipleChoiceQuestion> licenseMultipleChoiceQuestions;
    private List<ResponseQuestionDto> responseQuestionDtos;


    @BeforeEach
    void setUp() {
        licenseMultipleChoiceQuestions = new ArrayList<>();
        responseQuestionDtos= new ArrayList<>();
        licenseMultipleChoiceQuestion = LicenseMultipleChoiceQuestion.makeForTest("test");
        licenseMultipleChoiceQuestions.add(licenseMultipleChoiceQuestion);
        responseQuestionDtos.add(ResponseQuestionDto.forAdmin(licenseMultipleChoiceQuestion));
    }

    @Test
    @DisplayName("자격증 문제 생성")
    void makeLicenseQuestion() throws Exception {
        final String path = baseApiUrl;
        final String document_Name ="성공";
        Mockito.when(licenseQuestionMakeService.makeLicenseNormalQuestion(any())).thenReturn(responseQuestionDtos);

        mockMvc.perform(RestDocumentationRequestBuilders.post(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(RequestMakeLicenseMultipleChoiceQuestionDto.from(
                                "test",
                                LicenseCategory.SQLD,
                                licenseMultipleChoiceQuestions,
                                LicenseMultipleChoiceQuestion::getQuestionChoices))))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(responseQuestionDtos)))
                .andDo(print())
                .andDo(MockMvcRestDocumentation.document(
                        document_Name
                ))
                .andDo(MockMvcRestDocumentationWrapper.document(
                        document_Name
                        ,resource(
                                ResourceSnippetParameters.builder()
                                        .tag(tag)
                                        .description("자격증")
                                        .build()
                        )
                ));
    }



}