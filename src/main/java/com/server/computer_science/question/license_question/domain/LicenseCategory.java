package com.server.computer_science.question.license_question.domain;

public enum LicenseCategory {
    SQLD("SQLD"),
    ENGINEER("정보처리기사");

    private final String korean;

    LicenseCategory(String korean) {
        this.korean = korean;
    }

    public String getKorean() {
        return korean;
    }

}
