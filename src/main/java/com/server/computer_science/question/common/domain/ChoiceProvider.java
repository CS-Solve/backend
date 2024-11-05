package com.server.computer_science.question.common.domain;

import java.util.List;

public interface ChoiceProvider {
	List<? extends QuestionChoice> getQuestionChoices();
}
