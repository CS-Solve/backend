package com.server.computerscience.question.common.service.implement;

import com.server.computerscience.chatbot.domain.QuestionToChatGptContentMapper;
import com.server.computerscience.chatbot.dto.response.ChatGptFileUploadResponseDto;
import com.server.computerscience.chatbot.service.implement.ChatManageService;
import com.server.computerscience.question.common.domain.Question;
import com.server.computerscience.question.common.dto.request.RequestQuestionCommandDto;
import com.server.computerscience.question.common.service.ExternalQuestionService;
import com.server.computerscience.question.common.service.QuestionSelectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExternalSenderQuestion implements ExternalQuestionService {
	private final QuestionSelectorService questionSelectorService;
	private final QuestionToChatGptContentMapper questionToChatGptContentMapper;
	private final ChatManageService chatManageService;

	@Override
	public ChatGptFileUploadResponseDto sendQuestionToExternal(RequestQuestionCommandDto requestQuestionCommandDto) {
		/**
		 * 카테고리에 해당된 모든 문제를 가져온다.
		 */
		List<? extends Question> question = questionSelectorService.getAllQuestions(requestQuestionCommandDto
				.getQuestionCategories(),
			requestQuestionCommandDto.isMultipleChoice());

		return chatManageService.talkForBatch(questionToChatGptContentMapper.getContentsFromQuestion(question),
			requestQuestionCommandDto.getCommand());
	}
}
