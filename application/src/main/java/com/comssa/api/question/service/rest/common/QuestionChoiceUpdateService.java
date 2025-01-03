package com.comssa.api.question.service.rest.common;

import com.comssa.persistence.question.domain.common.QuestionChoice;
import com.comssa.persistence.question.dto.common.request.RequestChangeQuestionContentDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface QuestionChoiceUpdateService<T extends QuestionChoice> {

	default T changeContent(Long questionChoiceId, RequestChangeQuestionContentDto requestChangeQuestionContentDto) {
		T questionChoice = findById(questionChoiceId);
		questionChoice.changeContent(requestChangeQuestionContentDto.getContent());
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
