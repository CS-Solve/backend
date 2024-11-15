package com.study.computerscience.question.major.admin.service.implement

import com.study.computerscience.chatbot.dto.response.ChatGptFileUploadResponseDto
import com.study.computerscience.chatbot.service.implement.ChatManageService
import com.study.computerscience.question.common.domain.Question
import com.study.computerscience.question.common.dto.request.RequestQuestionCommandDto
import com.study.computerscience.question.common.service.QuestionSelectorService
import com.study.computerscience.question.major.admin.service.ExternalQuestionService
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
