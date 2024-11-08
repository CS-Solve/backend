package com.server.computerscience.chatbot.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.server.computerscience.chatbot.dto.request.ChatContentDto;
import com.server.computerscience.question.common.domain.Question;
import com.server.computerscience.question.license.domain.LicenseMultipleChoiceQuestion;
import com.server.computerscience.question.major.common.domain.MajorMultipleChoiceQuestion;

@Service
public class QuestionToChatGptContentMapper {

	public List<ChatContentDto> getContentsFromQuestion(List<? extends Question> questions) {
		List<ChatContentDto> chatContentDtos = new ArrayList<>();

		for (Question question : questions) {
			ChatContentDto chatContentDto = createChatContentDtoForChatGpt(question);
			if (chatContentDto != null) {
				chatContentDtos.add(chatContentDto);
			}
		}

		return chatContentDtos;
	}

	private ChatContentDto createChatContentDtoForChatGpt(Question question) {
		if (question instanceof LicenseMultipleChoiceQuestion) {
			LicenseMultipleChoiceQuestion licenseQuestion = (LicenseMultipleChoiceQuestion)question;
			return ChatContentDto.from(
				ChatContentType.TEXT,
				licenseQuestion + licenseQuestion.getQuestionChoices().toString()
			);
		} else if (question instanceof MajorMultipleChoiceQuestion) {
			MajorMultipleChoiceQuestion majorQuestion = (MajorMultipleChoiceQuestion)question;
			return ChatContentDto.from(
				ChatContentType.TEXT,
				majorQuestion + majorQuestion.getQuestionChoices().toString()
			);
		}
		return null; // 지원되지 않는 Question 타입인 경우 null 반환
	}
}
