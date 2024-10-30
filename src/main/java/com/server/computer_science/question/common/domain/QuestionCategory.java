package com.server.computer_science.question.common.domain;

public enum QuestionCategory {
    DATABASE("데이터베이스",true),
    NETWORK("네트워크",true),
    OPERATING_SYSTEM("운영체제",true),
    COMPUTER_ARCHITECTURE("컴퓨터 구조",true),
    DATA_STRUCTURE("자료 구조",true),
    ALGORITHM("알고리즘",false),
    OOP("객체 지향",false),
    DESIGN_PATTERN("디자인 패턴",false),
    JAVA("Java",true),
    SOFTWARE_DESIGN("소프트웨어 설계", true),
    SOFTWARE_DEVELOPMENT("소프트웨어 개발",true),
    DATABASE_DESIGN("데이터베이스 구축",true),
    PROGRAMMING_LANGUAGE("프로그래밍 언어 활용",true),
    INFORMATION_SECURITY("정보시스템 구축관리",true);


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
