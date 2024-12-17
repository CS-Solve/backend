package com.comssa.api.chatbot.service

import com.comssa.core.chatbot.dto.response.ChatGptFileUploadResponseDto
import com.comssa.persistence.question.dto.common.request.RequestDoQuestionCommandDto
import org.springframework.stereotype.Service

@Service
interface ExternalQuestionService {
	fun sendQuestionToExternal(requestDoQuestionCommandDto: RequestDoQuestionCommandDto): ChatGptFileUploadResponseDto?
}
