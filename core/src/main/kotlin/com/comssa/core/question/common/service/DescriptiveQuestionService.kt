package com.comssa.core.question.common.service

import com.comssa.core.chatbot.service.implement.ChatManageService
import com.comssa.core.question.common.dto.RequestUserDescriptiveAnswerDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class DescriptiveQuestionService(
	private val questionGetService: QuestionGetService,
	private val chatManageService: ChatManageService,
) {
	fun gradeDescriptiveQuestion(
		id: Long,
		requestUserDescriptiveAnswerDto: RequestUserDescriptiveAnswerDto,
	): String {
		val question =
			questionGetService.getDescriptiveQuestion(id)
		return chatManageService.talkForGradeQuestion(
			question.gradeStandard,
			question.content,
			requestUserDescriptiveAnswerDto.content,
		)
	}
}
