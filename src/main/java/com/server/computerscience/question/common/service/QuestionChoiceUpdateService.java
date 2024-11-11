package com.server.computerscience.question.common.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.server.computerscience.question.common.domain.QuestionChoice;
import com.server.computerscience.question.common.dto.request.RequestChangeContentDto;

@Service
@Transactional
public interface QuestionChoiceUpdateService<T extends QuestionChoice> {

	default T changeContent(Long questionChoiceId, RequestChangeContentDto requestChangeContentDto) {
		T questionChoice = findById(questionChoiceId);
		questionChoice.changeContent(requestChangeContentDto.getContent());
		return questionChoice;
	}

	default T toggleAnswerStatus(Long questionChoiceId) {
		T questionChoice = findById(questionChoiceId);
		questionChoice.toggleAnswerStatus();
		return questionChoice;
	}

	T findById(Long questionChoiceId);
}
