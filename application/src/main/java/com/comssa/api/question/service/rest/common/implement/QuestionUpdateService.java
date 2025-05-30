package com.comssa.api.question.service.rest.common.implement;

import com.comssa.api.question.service.rest.common.FileUploadService;
import com.comssa.persistence.question.domain.common.Question;
import com.comssa.persistence.question.repository.jpa.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class QuestionUpdateService
	implements com.comssa.api.question.service.rest.common.QuestionUpdateService<Question> {
	private final QuestionRepository questionRepository;
	private final FileUploadService fileUploadService;

	@Override
	public String updateImage(Long questionId, MultipartFile image) {
		try {
			Question question = findById(questionId);
			String imageUrl = fileUploadService.uploadImage(image, "license");
			assert question != null;
			question.updateImage(imageUrl);
			return imageUrl;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void deleteQuestion(Long questionId) {
		questionRepository.deleteById(questionId);
	}

	@Override
	public Question findById(Long questionId) {
		return questionRepository.findById(questionId).get();
	}
}
