package com.comssa.persistence.question.domain.common;

import java.util.List;

public interface ChoiceBehavior<T extends QuestionChoice> {
	List<T> getQuestionChoices();
}
