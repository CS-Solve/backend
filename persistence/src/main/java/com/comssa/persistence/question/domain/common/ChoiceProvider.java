package com.comssa.persistence.question.domain.common;

import java.util.List;

public interface ChoiceProvider {
    List<? extends QuestionChoice> getQuestionChoices();
}
