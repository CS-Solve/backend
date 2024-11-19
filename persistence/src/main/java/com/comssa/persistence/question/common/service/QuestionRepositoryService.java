package com.comssa.persistence.question.common.service;

import com.comssa.persistence.question.common.domain.Question;
import com.comssa.persistence.question.common.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
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
	public Question findByIdFetchChoices(Long id) {
		return questionRepository.findById(id).orElse(null);
	}
}
