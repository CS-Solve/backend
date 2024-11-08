package com.server.computerscience.chatbot.service.implement;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.computerscience.chatbot.dto.request.ChatGptRequestFileUploadDto;

@Service
public class FileConvertService {
	private final ObjectMapper objectMapper = new ObjectMapper();
	private String jsonFileName = "uploadData";

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

	public ByteArrayResource convertToByteArrayResource(List<ChatGptRequestFileUploadDto> dataForFile) {
		try {
			// JSON 데이터 작성
			StringBuilder jsonContent = new StringBuilder();
			ObjectMapper objectMapper = new ObjectMapper();
			for (ChatGptRequestFileUploadDto dto : dataForFile) {
				String jsonLine = objectMapper.writeValueAsString(dto);
				jsonContent.append(jsonLine).append("\n");
			}

			// JSON 데이터를 ByteArrayResource로 변환
			byte[] byteArray = jsonContent.toString().getBytes();
			return new ByteArrayResource(byteArray) {
				@Override
				public String getFilename() {
					return jsonFileName;  // 파일 이름 지정
				}
			};
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
