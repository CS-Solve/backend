package com.server.computerscience.question.common.domain;

import java.util.List;

public interface ChoiceProvider {
	List<? extends QuestionChoice> getQuestionChoices();
}
