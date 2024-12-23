package com.comssa.persistence.question.service;

import com.comssa.persistence.question.domain.common.Question;
import com.comssa.persistence.question.repository.jpa.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionRepositoryService {
	private final QuestionRepository questionRepository;

	@Nullable
	public Question findById(Long id) {
		return questionRepository.findById(id).orElse(null);
	}


	@Nullable
	public Question findByIdFetchCommentsOrderByCreatedAtDesc(Long id) {
		return questionRepository.findByIdFetchCommentsOrderByCreatedAtDesc(id).orElse(null);
	}
}
