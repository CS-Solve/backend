package com.server.computerscience.chatbot.service.implement

import com.fasterxml.jackson.databind.ObjectMapper
import com.server.computerscience.chatbot.dto.request.ChatGptRequestFileUploadDto
import org.springframework.core.io.ByteArrayResource
import org.springframework.stereotype.Service
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.OutputStreamWriter

@Service
class FileConvertService {
	private val objectMapper = ObjectMapper()

	fun dataToChatGptJson(dataForFile: List<ChatGptRequestFileUploadDto?>): ByteArrayResource {
		try {
			ByteArrayOutputStream().use { outputStream ->
				OutputStreamWriter(outputStream).use { writer ->
					for (dto in dataForFile) {
						val jsonLine = objectMapper.writeValueAsString(dto)
						writer.write(jsonLine)
						writer.write("\n") // 줄 바꿈 추가
					}
					writer.flush() // OutputStreamWriter 버퍼 비우기
					return object : ByteArrayResource(outputStream.toByteArray()) {
						override fun getFilename(): String = "uploadData.jsonl"
					}
				}
			}
		} catch (e: IOException) {
			e.printStackTrace()
			return ByteArrayResource(ByteArray(0))
		}
	}
}
