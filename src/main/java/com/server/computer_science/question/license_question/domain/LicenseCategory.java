package com.server.computer_science.question.license_question.domain;

public enum LicenseCategory {
    SQLD("SQLD");

    private final String korean;

    LicenseCategory(String korean) {
        this.korean = korean;
    }

    public String getKorean() {
        return korean;
    }

}
