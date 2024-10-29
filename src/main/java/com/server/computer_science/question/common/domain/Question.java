package com.server.computer_science.question.common.domain;

import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

@Getter
@MappedSuperclass
public abstract class Question {
    protected String content;
    @Enumerated(value = EnumType.STRING)
    protected QuestionCategory questionCategory;
    @Enumerated(value = EnumType.STRING)
    protected QuestionLevel questionLevel;
    protected String description;
    protected String imageUrl;


    public void changeDescription(String description) {
        this.description = description;
    }
    public void changeQuestion(String question) {
        this.content = question;
    }

}
