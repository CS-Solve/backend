package com.comssa.persistence.question.repository.querydsl;

import com.comssa.persistence.question.domain.common.Question;
import com.comssa.persistence.question.repository.jpa.QuestionCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 이 인터페이스는 Question 테이블과 관련된 모든 작업을 처리하는 JpaRepository 기반의 인터페이스입니다.
 * - 삭제, 수정, 댓글 조회 등 전체 Question 테이블에 공통적으로 적용되는 작업을 수행합니다.
 * - 반면, MajorMultipleChoiceQuestion 등 특정 서브타입만을 조회하거나 처리하는 작업은
 * 개별 JpaRepository 대신 QueryDSL을 활용한 별도 구현체를 통해 처리합니다.
 */
@Repository
public interface QuestionRepository<T extends Question> extends JpaRepository<T, Long>, QuestionCustomRepository<T> {

}
