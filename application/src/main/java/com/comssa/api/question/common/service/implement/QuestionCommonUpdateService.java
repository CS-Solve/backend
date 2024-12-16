package com.comssa.api.question.common.service.implement;

import com.comssa.api.question.common.service.QuestionUpdateService;
import com.comssa.persistence.question.common.domain.Question;
import com.comssa.persistence.question.common.service.QuestionRepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionCommonUpdateService implements QuestionUpdateService<Question> {
	private final QuestionRepositoryService questionRepositoryService;

	@Override
	public void deleteQuestion(Long questionId) {
		questionRepositoryService.delete(questionId);
	}

	@Override
	public Question findById(Long questionId) {
		return questionRepositoryService.findById(questionId);
	}
}
