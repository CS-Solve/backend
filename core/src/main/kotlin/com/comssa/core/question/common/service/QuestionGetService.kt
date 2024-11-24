package com.comssa.core.question.common.service

import com.comssa.persistence.question.common.domain.Question
import com.comssa.persistence.question.common.service.QuestionRepositoryService
import com.comssa.persistence.question.license.domain.LicenseMultipleChoiceQuestion
import com.comssa.persistence.question.major.domain.common.MajorMultipleChoiceQuestion
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class QuestionGetService(
	private val questionRepositoryService: QuestionRepositoryService,
) {
	fun getQuestionByIdFetchChoiceIfShould(questionId: Long): Question {
		val question =
			questionRepositoryService.findById(questionId)
				?: throw NoSuchElementException("Question with ID $questionId not found")
		// MajorMultipleChoiceQuestion 타입이면 choices를 지연 로딩
		when (question) {
			is MajorMultipleChoiceQuestion -> {
				question.questionChoices.size // 지연 로딩 강제 초기화
			}

			is LicenseMultipleChoiceQuestion -> {
				question.questionChoices.size // 지연 로딩 강제 초기화
			}
		}
		return question
	}

	fun getDescriptiveQuestion(questionId: Long): Question {
		val question =
			questionRepositoryService.findById(questionId)
				?: throw NoSuchElementException("Question with ID $questionId not found")
		return question
	}
}
