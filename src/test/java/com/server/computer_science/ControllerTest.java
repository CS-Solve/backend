package com.server.computer_science;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.computer_science.question.major_question.admin.controller.AdminMajorQuestionController;
import com.server.computer_science.question.major_question.admin.controller.AdminMajorQuestionViewController;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith({RestDocumentationExtension.class})
@AutoConfigureRestDocs
@AutoConfigureMockMvc
public class ControllerTest {
    @Autowired
    public MockMvc mockMvc;
    @Autowired
    public ObjectMapper objectMapper;
}

