package com.comssa.api.question.service.rest.common.implement;

import com.comssa.persistence.question.domain.common.QuestionChoice;
import com.comssa.persistence.question.repository.jpa.QuestionChoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionChoiceUpdateService
	implements com.comssa.api.question.service.rest.common.QuestionChoiceUpdateService<QuestionChoice> {
	private final QuestionChoiceRepository questionChoiceRepository;

	@Override
	public void deleteQuestionChoice(Long questionChoiceId) {
		questionChoiceRepository.deleteById(questionChoiceId);
	}

	@Override
	public QuestionChoice findById(Long questionChoiceId) {
		return questionChoiceRepository.findById(questionChoiceId).orElse(null);
	}
}
