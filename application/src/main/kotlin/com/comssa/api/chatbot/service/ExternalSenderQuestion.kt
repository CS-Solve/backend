package com.comssa.api.chatbot.service

import com.comssa.api.question.service.common.QuestionSelectorService
import com.comssa.core.chatbot.dto.response.ChatGptFileUploadResponseDto
import com.comssa.core.chatbot.service.implement.ChatManageService
import com.comssa.persistence.question.common.domain.Question
import com.comssa.persistence.question.common.dto.request.RequestQuestionCommandDto
import org.springframework.stereotype.Service

@Service
class ExternalSenderQuestion(
	private val questionSelectorService: QuestionSelectorService,
	private val questionToChatGptContentMapper: QuestionToChatGptContentMapper,
	private val chatManageService: ChatManageService,
) : ExternalQuestionService {
	override fun sendQuestionToExternal(
		requestQuestionCommandDto: RequestQuestionCommandDto,
	): ChatGptFileUploadResponseDto {
		/**
		 * 카테고리에 해당된 모든 문제를 가져온다.
		 */
		val question: List<Question> =
			questionSelectorService.getAllQuestions(
				requestQuestionCommandDto
					.questionCategories,
				requestQuestionCommandDto.isMultipleChoice,
			)
		return chatManageService.talkForBatch(
			questionToChatGptContentMapper.getContentsFromQuestion(question),
			requestQuestionCommandDto.command,
		)
	}
}
