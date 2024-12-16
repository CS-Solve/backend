package com.comssa.api.question.common.service;


import com.comssa.persistence.question.common.domain.QuestionChoice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface MultipleChoiceQuestionGradeService {

	default boolean isChoiceAnswer(Long choiceId) {
		QuestionChoice questionChoice = findById(choiceId);
		//선택될 시의 추가 행동을 수행한다.
		questionChoice.select();
		return questionChoice.isAnswerStatus();
	}

	QuestionChoice findById(Long id);
}
