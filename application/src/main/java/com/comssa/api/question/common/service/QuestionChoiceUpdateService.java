package com.comssa.api.question.common.service;

import com.comssa.persistence.question.common.domain.QuestionChoice;
import com.comssa.persistence.question.common.dto.request.RequestChangeContentDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	void deleteQuestionChoice(Long questionChoiceId);

	T findById(Long questionChoiceId);
}
