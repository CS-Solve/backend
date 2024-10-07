package com.server.computer_science.question.common;

public enum QuestionCategory {
    DATABASE("데이터베이스",true),
    NETWORK("네트워크",true),
    OPERATING_SYSTEM("운영체제",true),
    COMPUTER_ARCHITECTURE("컴퓨터 구조",true),
    DATA_STRUCTURE("자료 구조",true),
    ALGORITHM("알고리즘",false),
    OOP("객체 지향",false),
    DESIGN_PATTERN("디자인 패턴",false),
    JAVA("Java",false);
    private final String korean;
    private final boolean canBeShow;

    QuestionCategory(String korean, boolean canBeShow) {
        this.korean = korean;
        this.canBeShow = canBeShow;
    }

    public String getKorean() {
        return korean;
    }

    public boolean isCanBeShow() {
        return canBeShow;
    }
}
