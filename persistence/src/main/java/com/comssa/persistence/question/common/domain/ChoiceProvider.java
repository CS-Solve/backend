package com.comssa.persistence.question.common.domain;

import java.util.List;

public interface ChoiceProvider {
	List<? extends QuestionChoice> getQuestionChoices();
}
