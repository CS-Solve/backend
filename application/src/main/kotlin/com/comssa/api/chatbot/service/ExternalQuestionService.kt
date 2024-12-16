package com.comssa.api.chatbot.service

import com.comssa.core.chatbot.dto.response.ChatGptFileUploadResponseDto
import com.comssa.persistence.question.common.dto.request.RequestQuestionCommandDto
import org.springframework.stereotype.Service

@Service
interface ExternalQuestionService {
	fun sendQuestionToExternal(requestQuestionCommandDto: RequestQuestionCommandDto): ChatGptFileUploadResponseDto?
}
