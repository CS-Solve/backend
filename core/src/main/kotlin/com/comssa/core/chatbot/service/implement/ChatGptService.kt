package com.comssa.core.chatbot.service.implement

import com.comssa.core.chatbot.config.RestTemplateService
import com.comssa.persistence.chatbot.dto.request.ChatGptBatchRequestDto
import com.comssa.persistence.chatbot.dto.request.ChatGptMessageDto
import com.comssa.persistence.chatbot.dto.request.ChatGptRequestFileUploadDto
import com.comssa.persistence.chatbot.dto.request.ChatGptRestRequestDto
import com.comssa.persistence.chatbot.dto.response.ChatGptBatchResponseDto
import com.comssa.persistence.chatbot.dto.response.ChatGptFileUploadResponseDto
import com.comssa.persistence.chatbot.dto.response.ChatGptResponseDto
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
	private val fileConvertService: FileConvertService,
) {
	@Value("\${openai.secret-key}")
	private lateinit var secretKey: String
	private val model = "gpt-4o-mini"
	private val baseUrl = "https://api.openai.com"
	private val advancedChatApiUrl = "/v1/chat/completions"
	private val fileUploadUrl = "/v1/files"
	private val batchCreateUrl = "/v1/batches"

	/**
	 * 단순하게 메시지를 보내는 역할만을 한다
	 * 메시지는 채팅용일 수도, 정답 채점을 위한 데이터일 수있고 이를 신경 쓰지 않는다.
	 * 오로지 메시지와 역할만 보낸다.
	 */
	fun sendChatMessage(chatMessages: List<ChatGptMessageDto?>): String {
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

	fun sendFileUploadMessage(chatMessages: List<ChatGptMessageDto?>): ChatGptFileUploadResponseDto {
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

	private fun makeFileUploadDto(chatMessages: List<ChatGptMessageDto?>): List<ChatGptRequestFileUploadDto> {
		val chatGptRequestFileUploadDto =
			ChatGptRequestFileUploadDto.from(
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
