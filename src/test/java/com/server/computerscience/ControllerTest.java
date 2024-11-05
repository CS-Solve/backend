package com.server.computerscience;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith({RestDocumentationExtension.class})
@AutoConfigureRestDocs
@AutoConfigureMockMvc
public class ControllerTest {
	@Autowired
	public MockMvc mockMvc;
	@Autowired
	public ObjectMapper objectMapper;
}

