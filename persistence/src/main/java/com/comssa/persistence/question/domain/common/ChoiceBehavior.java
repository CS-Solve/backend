package com.comssa.persistence.question.domain.common;

import java.util.List;

public interface ChoiceBehavior {
	List<? extends QuestionChoice> getQuestionChoices();
}
