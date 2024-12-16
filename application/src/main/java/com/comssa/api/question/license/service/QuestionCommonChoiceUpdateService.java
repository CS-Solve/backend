package com.comssa.api.question.license.service;

import com.comssa.api.question.common.service.QuestionChoiceUpdateService;
import com.comssa.persistence.question.common.domain.QuestionChoice;
import com.comssa.persistence.question.common.repository.QuestionChoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionCommonChoiceUpdateService
	implements QuestionChoiceUpdateService<QuestionChoice> {
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
