package com.api.computerscience.question.major.admin.service

import com.core.computerscience.chatbot.dto.response.ChatGptFileUploadResponseDto
import com.persistence.computerscience.question.common.domain.dto.request.RequestQuestionCommandDto

@org.springframework.stereotype.Service
interface ExternalQuestionService {
	fun sendQuestionToExternal(requestQuestionCommandDto: RequestQuestionCommandDto): ChatGptFileUploadResponseDto?
}
