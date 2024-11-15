package com.study.computerscience.question.major.admin.service

import com.study.computerscience.chatbot.dto.response.ChatGptFileUploadResponseDto
import com.study.computerscience.question.common.dto.request.RequestQuestionCommandDto

@org.springframework.stereotype.Service
interface ExternalQuestionService {
	fun sendQuestionToExternal(requestQuestionCommandDto: RequestQuestionCommandDto): ChatGptFileUploadResponseDto?
}
