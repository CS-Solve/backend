package com.server.computerscience.chatbot.service.implement;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.computerscience.chatbot.dto.request.ChatGptRequestFileUploadDto;

@Service
public class FileConvertService {
	private final ObjectMapper objectMapper = new ObjectMapper();

	public File convertToFile(List<ChatGptRequestFileUploadDto> dataForFile) {
		File jsonlFile = new File("uploadData.jsonl");

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(jsonlFile))) {
			for (ChatGptRequestFileUploadDto dto : dataForFile) {
				// 각 객체를 JSON 문자열로 변환
				String jsonLine = objectMapper.writeValueAsString(dto);
				// 변환된 JSON을 한 줄로 작성
				writer.write(jsonLine);
				writer.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		return jsonlFile;
	}
}
