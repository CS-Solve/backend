package com.server.computer_science.question.major_question.admin.controller;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.server.computer_science.ControllerTest;
import com.server.computer_science.question.common.dto.request.RequestChangeContentDto;
import com.server.computer_science.question.common.dto.request.RequestChangeDescriptionDto;
import com.server.computer_science.question.common.dto.response.ResponseQuestionDto;
import com.server.computer_science.question.major_question.admin.service.AdminMajorQuestionClassifiedGetService;
import com.server.computer_science.question.major_question.admin.service.implement.AdminMajorMultipleChoiceQuestionUpdateService;
import com.server.computer_science.question.major_question.common.domain.MajorMultipleChoiceQuestion;
import com.server.computer_science.question.major_question.admin.dto.RequestMakeMultipleChoiceQuestionDto;
import com.server.computer_science.question.major_question.common.exception.DuplicateQuestionException;
import com.server.computer_science.question.major_question.admin.service.AdminMajorQuestionMakeService;
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

import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@WebMvcTest(AdminMajorQuestionController.class)
@DisplayName("단위 테스트 - 전공 개념 Admin Controller")
class AdminMajorQuestionControllerTest extends ControllerTest {

    private final String tag = "전공 문제";
    @MockBean
    private AdminMajorQuestionMakeService adminMajorQuestionMakeService;
    @MockBean
    private AdminMajorMultipleChoiceQuestionUpdateService adminMajorMultipleChoiceQuestionUpdateService;
    @MockBean
    private AdminMajorQuestionClassifiedGetService adminMajorQuestionClassifiedGetService;
    private MajorMultipleChoiceQuestion majorMultipleChoiceQuestion;
    private RequestMakeMultipleChoiceQuestionDto requestMakeMultipleChoiceQuestionDto;

    @BeforeEach
    void setUp() {
        majorMultipleChoiceQuestion = MajorMultipleChoiceQuestion.makeForTest();
        requestMakeMultipleChoiceQuestionDto = new RequestMakeMultipleChoiceQuestionDto();
    }
    @Test
    void MakeMultiNormalQuestion() {
    }
    @Test
    @DisplayName("전공 문제 조회")
    void getMajorMultipleChoiceQuestions() throws Exception {
        final String PATH = "/admin/question/major";
        final String document_Name ="성공";
        Mockito.when(adminMajorQuestionClassifiedGetService.getClassifiedAllMajorQuestions()).thenReturn(new ArrayList<>());
        mockMvc.perform(RestDocumentationRequestBuilders.get(PATH))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andDo(MockMvcRestDocumentation.document(
                        document_Name
                ))
                .andDo(MockMvcRestDocumentationWrapper.document(
                        document_Name
                        ,resource(
                                ResourceSnippetParameters.builder()
                                        .tag(tag)
                                        .description("단답형")
                                        .build()
                        )
                ));
    }
    @Test
    @DisplayName("전공 문제 단체 생성 - 성공")
    void MakeSingleMajorMultipleChoiceQuestions() throws Exception {
        final String PATH = "/admin/question/major-multi";
        final String document_Name ="성공";
        Mockito.when(adminMajorQuestionMakeService.makeMultipleChoiceQuestions(any())).thenReturn(new ArrayList<>());
        mockMvc.perform(RestDocumentationRequestBuilders.post(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new ArrayList<>())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andDo(MockMvcRestDocumentation.document(
                        document_Name
                ))
                .andDo(MockMvcRestDocumentationWrapper.document(
                        document_Name
                        ,resource(
                                ResourceSnippetParameters.builder()
                                        .tag(tag)
                                        .description("단답형")
                                        .build()
                        )
                ));
    }

    @Test
    @DisplayName("전공 문제 단일 생성 - 성공")
    void MakeMajorMultipleChoiceQuestions() throws Exception {
        final String PATH = "/admin/question/major-single";
        final String document_Name ="성공";
        Mockito.when(adminMajorQuestionMakeService.makeMultipleChoiceQuestion(any())).thenReturn(ResponseQuestionDto.forUser(majorMultipleChoiceQuestion,majorMultipleChoiceQuestion.getQuestionChoices()));
        mockMvc.perform(RestDocumentationRequestBuilders.post(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestMakeMultipleChoiceQuestionDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andDo(MockMvcRestDocumentation.document(
                        document_Name
                ))
                .andDo(MockMvcRestDocumentationWrapper.document(
                        document_Name
                        ,resource(
                                ResourceSnippetParameters.builder()
                                        .tag(tag)
                                        .description("단답형 문제")
                                        .build()
                        )
                ));
    }
    @Test
    @DisplayName("전공 문제 단일 생성 - 실패")
    void MakeSingleMajorQuestionWithDuplicateError() throws Exception {
        final String PATH = "/admin/question/major-single";
        final String document_Name ="실패 - 중복된 문제";
        Mockito.doThrow(DuplicateQuestionException.class).when(adminMajorQuestionMakeService).makeMultipleChoiceQuestion(any());
        mockMvc.perform(RestDocumentationRequestBuilders.post(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestMakeMultipleChoiceQuestionDto)))
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

    @Test
    @DisplayName("전공 문제 업데이트 - Approve")
    void updateSingleMajorMultipleChoiceQuestionApprove() throws Exception {
        final String PATH = "/admin/question/major/1/toggle-approve";
        final String document_Name ="성공";
        Mockito.when(adminMajorMultipleChoiceQuestionUpdateService.toggleApproveNormalQuestion(any())).thenReturn(majorMultipleChoiceQuestion);
        mockMvc.perform(RestDocumentationRequestBuilders.patch(PATH)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andDo(MockMvcRestDocumentation.document(
                        document_Name
                ))
                .andDo(MockMvcRestDocumentationWrapper.document(
                        document_Name
                        ,resource(
                                ResourceSnippetParameters.builder()
                                        .tag(tag)
                                        .description("단답형 문제 생성")
                                        .build()
                        )
                ));
    }
    @Test
    @DisplayName("전공 문제 업데이트 - 객관식 여부")
    void updateSingleMajorMultipleChoiceQuestionShortAnswered() throws Exception {
        final String PATH = "/admin/question/major/1/toggle-multiple";
        final String document_Name ="성공";
        Mockito.when(adminMajorMultipleChoiceQuestionUpdateService.toggleCanBeShortAnswered(any())).thenReturn(majorMultipleChoiceQuestion);
        mockMvc.perform(RestDocumentationRequestBuilders.patch(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andDo(MockMvcRestDocumentation.document(
                        document_Name
                ))
                .andDo(MockMvcRestDocumentationWrapper.document(
                        document_Name
                        ,resource(
                                ResourceSnippetParameters.builder()
                                        .tag(tag)
                                        .description("단답형 문제")
                                        .build()
                        )
                ));
    }

    @Test
    @DisplayName("전공 문제 업데이트 - 본문")
    void updateSingleMajorMultipleChoiceQuestionContent() throws Exception {
        final String PATH = "/admin/question/major/1/content";
        final String document_Name ="성공";
        Mockito.when(adminMajorMultipleChoiceQuestionUpdateService.changeContent(any(),any())).thenReturn(majorMultipleChoiceQuestion);
        mockMvc.perform(RestDocumentationRequestBuilders.patch(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(RequestChangeContentDto.forTest())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andDo(MockMvcRestDocumentation.document(
                        document_Name
                ))
                .andDo(MockMvcRestDocumentationWrapper.document(
                        document_Name
                        ,resource(
                                ResourceSnippetParameters.builder()
                                        .tag(tag)
                                        .description("단답형 문제")
                                        .build()
                        )
                ));
    }
    @Test
    @DisplayName("전공 문제 업데이트 - 해설")
    void updateSingleMajorMultipleChoiceQuestionDescription() throws Exception {
        final String PATH = "/admin/question/major/1/description";
        final String document_Name ="성공";
        Mockito.when(adminMajorMultipleChoiceQuestionUpdateService.changeDescription(any(),any())).thenReturn(majorMultipleChoiceQuestion);
        mockMvc.perform(RestDocumentationRequestBuilders.patch(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(RequestChangeDescriptionDto.forTest())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andDo(MockMvcRestDocumentation.document(
                        document_Name
                ))
                .andDo(MockMvcRestDocumentationWrapper.document(
                        document_Name
                        ,resource(
                                ResourceSnippetParameters.builder()
                                        .tag(tag)
                                        .description("단답형 문제")
                                        .build()
                        )
                ));
    }

    @Test
    @DisplayName("전공 문제 삭제")
    void deleteSingleMajorMultipleChoiceQuestion() throws Exception {
        final String PATH = "/admin/question/major/1";
        final String document_Name ="성공";
        mockMvc.perform(RestDocumentationRequestBuilders.delete(PATH)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(print())
                .andDo(MockMvcRestDocumentation.document(
                        document_Name
                ))
                .andDo(MockMvcRestDocumentationWrapper.document(
                        document_Name
                        ,resource(
                                ResourceSnippetParameters.builder()
                                        .tag(tag)
                                        .description("단답형 문제")
                                        .build()
                        )
                ));
    }
}