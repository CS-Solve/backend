package com.comssa.api.question.common.service;


import com.comssa.persistence.question.common.domain.QuestionChoice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface MultipleChoiceQuestionGradeService<T extends QuestionChoice> {

	default boolean isChoiceAnswer(Long choiceId) {
		T questionChoice = findById(choiceId);
		//선택될 시의 추가 행동을 수행한다.
		questionChoice.select();
		return questionChoice.isAnswerStatus();
	}

	T findById(Long id);
}
