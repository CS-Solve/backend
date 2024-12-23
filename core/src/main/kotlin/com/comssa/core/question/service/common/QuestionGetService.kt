package com.comssa.core.question.service.common

import com.comssa.persistence.exception.WrongQuestionTypeException
import com.comssa.persistence.question.domain.common.Question
import com.comssa.persistence.question.domain.license.LicenseMultipleChoiceQuestion
import com.comssa.persistence.question.domain.major.MajorDescriptiveQuestion
import com.comssa.persistence.question.domain.major.MajorMultipleChoiceQuestion
import com.comssa.persistence.question.repository.jpa.QuestionRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class QuestionGetService(
	private val questionRepository: QuestionRepository,
) {
	fun getQuestionByIdFetchChoiceIfShould(questionId: Long): Question {
		val question =
			questionRepository.findById(questionId).orElseThrow { NoSuchElementException() }
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

	fun getMajorDescriptiveQuestion(questionId: Long): MajorDescriptiveQuestion {
		val question =
			questionRepository.findById(questionId).orElseThrow { NoSuchElementException() }
				?: throw NoSuchElementException("Question with ID $questionId not found")
		return question as? MajorDescriptiveQuestion
			?: throw WrongQuestionTypeException()
	}
}
