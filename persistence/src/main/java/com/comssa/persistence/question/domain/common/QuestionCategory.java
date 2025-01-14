package com.comssa.persistence.question.domain.common;

public enum QuestionCategory {
    DATABASE("데이터베이스", true),
    NETWORK("네트워크", true),
    OPERATING_SYSTEM("운영체제", true),
    COMPUTER_ARCHITECTURE("컴퓨터 구조", true),
    DATA_STRUCTURE("자료 구조", true),
    ALGORITHM("알고리즘", false),
    OOP("객체 지향", false),
    DESIGN_PATTERN("디자인 패턴", false),
    JAVA("Java", true),
    SOFTWARE_DESIGN("소프트웨어 설계", false),
    SOFTWARE_DEVELOPMENT("소프트웨어 개발", false),
    DATABASE_DESIGN("데이터베이스 구축", false),
    PROGRAMMING_LANGUAGE("프로그래밍 언어 활용", false),
    INFORMATION_SECURITY("정보시스템 구축관리", false),
    DATA_MODELING("데이터 모델링에 대한 이해", false),
    SQL_UTILIZE("SQL 기본 및 활용", false),
    DATA_UNDERSTANDING("데이터의 이해", false),
    DATA_ANALYSIS_PLANNING("데이터 분석 기회", false),
    DATA_ANALYSIS("데이터 분석", false),
    AWS_SAA("Solutions Architect Associate", false);

    private final String korean;
    private final boolean canBeShownInMajor;

    QuestionCategory(String korean, boolean canBeShownInMajor) {
        this.korean = korean;
        this.canBeShownInMajor = canBeShownInMajor;
    }

    public String getKorean() {
        return korean;
    }

    public boolean isCanBeShownInMajor() {
        return canBeShownInMajor;
    }
}
