package com.comssa.persistence.question.repository.jpa;

import com.comssa.persistence.question.domain.common.Question;
import com.comssa.persistence.question.repository.querydsl.QuestionCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 이 인터페이스는 Question 테이블과 관련된 모든 작업을 처리하는 JpaRepository 기반의 인터페이스입니다.
 * - 삭제, 수정, 전체 Question 중 댓글 조회 등 전체  Question 테이블에 공통적으로 적용되는 작업을 수행합니다. (전체 테이블에서 ID기준으로 조회)
 * <p>
 * - Question을 조회할 때도 JPA의 기본 메소드(findAll, findById)가 아니라
 * 직접 쿼리를 구현해야하는 부분(Jpa가 변수 이름으로 자동 생성되는 메소드 포함)은 QueryDsl(QuestionCustomRepository)을 extends하여 사용합니다
 * <p>
 * <p>
 * <p>
 * 하지만,
 * 1) Question이 아닌 서브타입을 2) JPA 기본 메소드가 아니라 특정 조건을 통해 (fetch Join 등이 추가됨)조회하거나 처리하는 작업은
 * JpaRepository에 아예 의존하지않고 QueryDSL을 활용한 별도 구현체를 통해 처리합니다.
 */
@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>, QuestionCustomRepository {

}
