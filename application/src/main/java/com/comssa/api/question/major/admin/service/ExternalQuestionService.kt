package com.comssa.api.question.major.admin.service

import com.comssa.core.chatbot.dto.response.ChatGptFileUploadResponseDto
import com.comssa.persistence.question.common.domain.dto.request.RequestQuestionCommandDto

@org.springframework.stereotype.Service
interface ExternalQuestionService {
	fun sendQuestionToExternal(requestQuestionCommandDto: RequestQuestionCommandDto): ChatGptFileUploadResponseDto?
}
