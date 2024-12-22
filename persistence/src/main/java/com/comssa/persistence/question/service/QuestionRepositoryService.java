package com.comssa.persistence.question.service;

import com.comssa.persistence.question.domain.common.Question;
import com.comssa.persistence.question.repository.querydsl.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionRepositoryService<T extends Question> {
	private final QuestionRepository<T> questionRepository;

	@Nullable
	public T findById(Long id) {
		return questionRepository.findById(id).orElse(null);
	}


	@Nullable
	public T findByIdFetchCommentsOrderByCreatedAtDesc(Long id) {
		return questionRepository.findByIdFetchCommentsOrderByCreatedAtDesc(id).orElse(null);
	}

	public void delete(Long id) {
		questionRepository.deleteById(id);
	}
}
