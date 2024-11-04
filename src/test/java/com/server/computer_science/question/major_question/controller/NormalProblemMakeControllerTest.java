package com.server.computer_science.question.major_question.controller;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.computer_science.question.major_question.admin.controller.AdminMajorQuestionController;
import com.server.computer_science.question.major_question.admin.service.implement.AdminMajorMultipleChoiceQuestionUpdateService;
import com.server.computer_science.question.major_question.common.domain.MajorMultipleChoiceQuestion;
import com.server.computer_science.question.major_question.common.service.MajorQuestionClassifiedGetService;
import com.server.computer_science.question.major_question.admin.dto.RequestMakeMajorMultipleChoiceQuestionDto;
import com.server.computer_science.question.major_question.common.exception.DuplicateQuestionException;
import com.server.computer_science.question.major_question.admin.service.AdminMajorQuestionMakeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@WebMvcTest(AdminMajorQuestionController.class)
@AutoConfigureRestDocs
@AutoConfigureMockMvc
class MajorQuestionControllerTest {

    private final String tag = "단답형 문제";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private AdminMajorQuestionMakeService adminMajorQuestionMakeService;
    @MockBean
    private AdminMajorMultipleChoiceQuestionUpdateService adminMajorMultipleChoiceQuestionUpdateService;
    @MockBean
    @Qualifier("basicAdminNormalQuestionClassifiedGetService")  // Qualifier 지정
    private MajorQuestionClassifiedGetService majorQuestionClassifiedGetService;


    private MajorMultipleChoiceQuestion majorMultipleChoiceQuestion;
    private RequestMakeMajorMultipleChoiceQuestionDto requestMakeMajorMultipleChoiceQuestionDto;

    @BeforeEach
    void setUp() {
        majorMultipleChoiceQuestion = MajorMultipleChoiceQuestion.makeForTest();
        requestMakeMajorMultipleChoiceQuestionDto = new RequestMakeMajorMultipleChoiceQuestionDto();
    }

    @Test
    void MakeMultiNormalQuestion() {
    }

//    @Test
//    @DisplayName("퀴즈 생성 단일 - 성공")
//    void MakeSingleNormalQuestion() throws Exception {
//        final String PATH = "/admin/question/normal-single";
//        final String document_Name ="성공";
//        Mockito.when(adminNormalQuestionMakeService.makeNormalQuestion(any())).thenReturn(ResponseNormalQuestionDto.forUser(normalQuestion));
//
//        mockMvc.perform(RestDocumentationRequestBuilders.post(PATH)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(requestMakeNormalQuestionDto)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(print())
//                .andDo(MockMvcRestDocumentation.document(
//                        document_Name
//                ))
//                .andDo(MockMvcRestDocumentationWrapper.document(
//                        document_Name
//                        ,resource(
//                                ResourceSnippetParameters.builder()
//                                        .tag(tag)
//                                        .description("단답형 문제 생성 - 성공")
//                                .build()
//                        )
//                ));
//    }

    @Test
    @DisplayName("퀴즈 생성 단일 - 실패")
    void MakeSingleNormalQuestionWithDuplicateError() throws Exception {
        final String PATH = "/admin/question/normal-single";
        final String document_Name ="실패 - 중복된 문제";
        Mockito.doThrow(DuplicateQuestionException.class).when(adminMajorQuestionMakeService).makeNormalQuestion(any());
        mockMvc.perform(RestDocumentationRequestBuilders.post(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestMakeMajorMultipleChoiceQuestionDto)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(print())
                .andDo(MockMvcRestDocumentation.document(
                        document_Name
                ))
                .andDo(MockMvcRestDocumentationWrapper.document(
                        document_Name
                        ,resource(
                                ResourceSnippetParameters.builder()
                                        .tag(tag)

                                        .build()
                        )
                ));
    }
}