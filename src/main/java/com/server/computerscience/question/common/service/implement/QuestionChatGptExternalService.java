package com.server.computerscience.question.common.service.implement;

import java.util.List;

import org.springframework.stereotype.Service;

import com.server.computerscience.chatbot.domain.QuestionToChatGptContentMapper;
import com.server.computerscience.chatbot.service.implement.ChatGptService;
import com.server.computerscience.question.common.domain.Question;
import com.server.computerscience.question.common.dto.request.RequestQuestionCommandDto;
import com.server.computerscience.question.common.service.QuestionExternalService;
import com.server.computerscience.question.common.service.QuestionSelectorService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionChatGptExternalService implements QuestionExternalService {
	private final QuestionSelectorService questionSelectorService;
	private final QuestionToChatGptContentMapper questionToChatGptContentMapper;
	private final ChatGptService chatGptService;

	@Override
	public void sendQuestionToExternal(RequestQuestionCommandDto requestQuestionCommandDto) {
		List<? extends Question> question = questionSelectorService.getAllQuestions(requestQuestionCommandDto.getQuestionCategories(),
			requestQuestionCommandDto.isMultipleChoice());
		chatGptService.batchSend(questionToChatGptContentMapper.getContentsFromQuestion(question));
	}
}
