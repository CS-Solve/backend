package com.comssa.core.question.common.service

import com.comssa.persistence.question.common.domain.Question
import com.comssa.persistence.question.common.service.QuestionRepositoryService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class QuestionGetService(
	private val questionRepositoryService: QuestionRepositoryService,
) {
	fun getQuestionById(questionId: Long): Question =
		questionRepositoryService.findById(questionId)
			?: throw NoSuchElementException("Question with ID $questionId not found")
}
