package com.study.computerscience.chatbot.service.implement

import com.study.computerscience.chatbot.config.RestTemplateService
import com.study.computerscience.chatbot.dto.request.ChatGptBatchRequestDto
import com.study.computerscience.chatbot.dto.request.ChatGptRequestFileUploadDto
import com.study.computerscience.chatbot.dto.request.ChatGptRequestFileUploadDto.Companion.from
import com.study.computerscience.chatbot.dto.request.ChatGptRestRequestDto
import com.study.computerscience.chatbot.dto.request.ChatMessageDto
import com.study.computerscience.chatbot.dto.response.ChatGptBatchResponseDto
import com.study.computerscience.chatbot.dto.response.ChatGptFileUploadResponseDto
import com.study.computerscience.chatbot.dto.response.ChatGptResponseDto
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.ByteArrayResource
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import java.time.LocalDateTime

@Service
class ChatGptService(
	private val restTemplateService: RestTemplateService,
	private val fileConvertService: com.study.computerscience.chatbot.service.implement.FileConvertService,
) {
	@Value("\${openai.secret-key}")
	private lateinit var secretKey: String
	private val model = "gpt-4o-mini"
	private val baseUrl = "https://api.openai.com"
	private val advancedChatApiUrl = "/v1/chat/completions"
	private val fileUploadUrl = "/v1/files"
	private val batchCreateUrl = "/v1/batches"

	fun sendChatMessage(chatMessages: List<ChatMessageDto?>): String {
		val chatGptRestRequestDto: ChatGptRestRequestDto = ChatGptRestRequestDto.from(model, chatMessages)
		val response =
			restTemplateService
				.sendPostRequest(
					baseUrl + advancedChatApiUrl,
					secretKey,
					MediaType.APPLICATION_JSON,
					chatGptRestRequestDto,
					ChatGptResponseDto::class.java,
				).body
		return response?.firstChoiceContent ?: "응답을 생성할 수 없습니다."
	}

	fun sendFileUploadMessage(chatMessages: List<ChatMessageDto?>): ChatGptFileUploadResponseDto {
		val dataForFIle = makeFileUploadDto(chatMessages)
		// 메모리 내 ByteArrayResource로 변환
		val resource = fileConvertService.dataToChatGptJson(dataForFIle)
		val body = makeBodyForFileUpload(resource)
		val response =
			restTemplateService
				.sendPostRequest(
					baseUrl + fileUploadUrl,
					secretKey,
					MediaType.MULTIPART_FORM_DATA,
					body,
					ChatGptFileUploadResponseDto::class.java,
				).body
		return response ?: ChatGptFileUploadResponseDto()
	}

	private fun makeFileUploadDto(chatMessages: List<ChatMessageDto?>): List<ChatGptRequestFileUploadDto> {
		val chatGptRequestFileUploadDto =
			from(
				ChatGptRestRequestDto.from(model, chatMessages),
				LocalDateTime.now().toString(),
				"POST",
				advancedChatApiUrl,
			)
		return listOf(chatGptRequestFileUploadDto)
	}

	private fun makeBodyForFileUpload(resource: ByteArrayResource): MultiValueMap<String, Any> {
		// body 생성
		// body 생성
		val body: MultiValueMap<String, Any> = LinkedMultiValueMap()
		body.add("file", resource)
		body.add("purpose", "batch")
		return body
	}

	fun sendBatchMessage(chatGptBatchRequestDto: ChatGptBatchRequestDto): ChatGptBatchResponseDto =
		restTemplateService
			.sendPostRequest(
				baseUrl + batchCreateUrl,
				secretKey,
				MediaType.APPLICATION_JSON,
				chatGptBatchRequestDto,
				ChatGptBatchResponseDto::class.java,
			).body ?: ChatGptBatchResponseDto()
}
