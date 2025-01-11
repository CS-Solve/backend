package com.comssa.core.question.service.common

import com.comssa.core.chatbot.service.implement.ChatManageService
import com.comssa.persistence.question.domain.major.MajorDescriptiveQuestion
import com.comssa.persistence.question.dto.common.request.RequestChangeQuestionGradeStandardDto
import com.comssa.persistence.question.dto.common.request.RequestDoGradeDescriptiveAnswerDto
import org.springframework.stereotype.Service

@Service
class DescriptiveQuestionService(
	private val questionGetService: QuestionGetService,
	private val chatManageService: ChatManageService,
) {
	fun gradeDescriptiveQuestion(
		id: Long,
		requestDoGradeDescriptiveAnswerDto: RequestDoGradeDescriptiveAnswerDto,
	): String {
		val question =
			questionGetService.getMajorDescriptiveQuestion(id)
		return chatManageService.talkForGradeDescriptiveQuestion(
			question.gradeStandard,
			question.content,
			requestDoGradeDescriptiveAnswerDto.content,
		)
	}

	fun changeGradeStandard(
		id: Long,
		requestChangeQuestionGradeStandardDto: RequestChangeQuestionGradeStandardDto,
	): MajorDescriptiveQuestion {
		val question =
			questionGetService.getMajorDescriptiveQuestion(id)
		question.changeGradeStandard(requestChangeQuestionGradeStandardDto.gradeStandard)
		return question
	}
}
