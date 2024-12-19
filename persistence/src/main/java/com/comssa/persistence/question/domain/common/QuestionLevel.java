package com.comssa.persistence.question.domain.common;

public enum QuestionLevel {

    LOW("하"), MEDIUM("중"), HIGH("상"), NONE("");
    private final String korean;

    QuestionLevel(String korean) {
        this.korean = korean;
    }

    public String getKorean() {
        return korean;
    }
}
