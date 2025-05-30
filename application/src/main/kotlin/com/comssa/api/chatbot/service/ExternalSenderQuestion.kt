package com.comssa.api.chatbot.service

import com.comssa.api.question.service.rest.common.QuestionSelectorService
import com.comssa.core.chatbot.service.implement.ChatManageService
import com.comssa.persistence.chatbot.dto.response.ChatGptFileUploadResponseDto
import com.comssa.persistence.question.domain.common.Question
import com.comssa.persistence.question.dto.common.request.RequestDoQuestionCommandDto
import org.springframework.stereotype.Service

@Service
class ExternalSenderQuestion(
	private val questionSelectorService: QuestionSelectorService,
	private val questionToChatGptContentMapper: QuestionToChatGptContentMapper,
	private val chatManageService: ChatManageService,
) : ExternalQuestionService {
	override fun sendQuestionToExternal(
		requestDoQuestionCommandDto: RequestDoQuestionCommandDto,
	): ChatGptFileUploadResponseDto {
		/**
		 * 카테고리에 해당된 모든 문제를 가져온다.
		 */
		val question: List<Question> =
			questionSelectorService.getAllQuestions(
				requestDoQuestionCommandDto
					.questionCategories,
				requestDoQuestionCommandDto.multipleChoice,
			)
		return chatManageService.talkForBatch(
			questionToChatGptContentMapper.getContentsFromQuestion(question),
			requestDoQuestionCommandDto.command,
		)
	}
}
