package com.server.computerscience.question.common.service;

import com.server.computerscience.chatbot.dto.response.ChatGptFileUploadResponseDto;
import com.server.computerscience.question.common.dto.request.RequestQuestionCommandDto;
import org.springframework.stereotype.Service;


@Service
public interface ExternalQuestionService {
	ChatGptFileUploadResponseDto sendQuestionToExternal(RequestQuestionCommandDto requestQuestionCommandDto);
}
