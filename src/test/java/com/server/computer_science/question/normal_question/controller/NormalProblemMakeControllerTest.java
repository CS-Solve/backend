package com.server.computer_science.question.normal_question.controller;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.computer_science.question.normal_question.domain.NormalQuestion;
import com.server.computer_science.question.normal_question.dto.request.RequestMakeNormalQuestionDto;
import com.server.computer_science.question.normal_question.dto.response.ResponseNormalQuestionDto;
import com.server.computer_science.question.normal_question.service.NormalQuestionMakeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@WebMvcTest(NormalProblemMakeController.class)
@AutoConfigureRestDocs
class NormalProblemMakeControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private NormalQuestionMakeService normalQuestionMakeService;

    private NormalQuestion normalQuestion;
    private RequestMakeNormalQuestionDto requestMakeNormalQuestionDto;

    @BeforeEach
    void setUp() {
        normalQuestion = NormalQuestion.makeForTest();
        requestMakeNormalQuestionDto = new RequestMakeNormalQuestionDto();
    }

    @Test
    void makeNormalQuestion() {
    }

    @Test
    @DisplayName("퀴즈 생성 단일")
    void MakeSingleNormalQuestion() throws Exception {
        final String PATH = "/question/normal-single";
        final String document_Name ="성공";
        Mockito.when(normalQuestionMakeService.makeNormalQuestion(requestMakeNormalQuestionDto)).thenReturn(ResponseNormalQuestionDto.of(normalQuestion));

        mockMvc.perform(RestDocumentationRequestBuilders.post(PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestMakeNormalQuestionDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andDo(MockMvcRestDocumentation.document(
                        document_Name
                ))
                .andDo(MockMvcRestDocumentationWrapper.document(
                        document_Name
                        ,resource(
                                ResourceSnippetParameters.builder()
                                .build()
                        )
                ));
    }
}