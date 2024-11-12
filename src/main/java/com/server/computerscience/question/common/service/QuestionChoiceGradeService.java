package com.server.computerscience.question.common.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.server.computerscience.question.common.domain.QuestionChoice;

@Service
@Transactional
public interface QuestionChoiceGradeService<T extends QuestionChoice> {

	default boolean isChoiceAnswer(Long choiceId) {
		T questionChoice = findById(choiceId);
		//선택될 시의 추가 행동을 수행한다.
		questionChoice.select();
		return questionChoice.isAnswerStatus();
	}

	T findById(Long id);
}
