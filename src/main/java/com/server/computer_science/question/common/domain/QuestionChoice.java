package com.server.computer_science.question.common.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.MappedSuperclass;

@Getter
@SuperBuilder
@NoArgsConstructor
@MappedSuperclass
public abstract class QuestionChoice {
    private String text;
    private int selectedCount;
    private boolean answerStatus;

    public QuestionChoice(String text, int selectedCount, boolean answerStatus) {
        this.text = text;
        this.selectedCount = selectedCount;
        this.answerStatus = answerStatus;
    }
}
