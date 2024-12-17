package com.comssa.core.question.common.service

import com.comssa.core.chatbot.service.implement.ChatManageService
import com.comssa.core.question.common.dto.RequestGradeStandardDto
import com.comssa.core.question.common.dto.RequestUserDescriptiveAnswerDto
import com.comssa.persistence.question.domain.major.MajorDescriptiveQuestion
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
			questionGetService.getMajorDescriptiveQuestion(id)
		return chatManageService.talkForGradeQuestion(
			question.gradeStandard,
			question.content,
			requestUserDescriptiveAnswerDto.content,
		)
	}

	fun changeGradeStandard(
		id: Long,
		requestGradeStandardDto: RequestGradeStandardDto,
	): MajorDescriptiveQuestion {
		val question =
			questionGetService.getMajorDescriptiveQuestion(id)
		question.changeGradeStandard(requestGradeStandardDto.gradeStandard)
		return question
	}
}
