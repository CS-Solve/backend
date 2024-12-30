package com.comssa.persistence.question.repository.querydsl;


import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;


/**
 * select 기준 테이블과 where 조건을 통해 JpaQuery를 만들어준다.
 * EntityPathBase 제네릭을 사용함으로 특정 Q클래스에 국한되지 않고
 * 자유롭게 Table과 where조건 문을 통해 JpaQuery를 생성할 수 있습니다.
 * <p>
 * 현재 주된 사용이유는 Qquestion, QMajorQuestion등을 조회할 때
 * 1. 특정 테이블만 조회해야함 (select from 할 테이블을 선택할 수 있어야함)
 * 2. 재사용하기 위함
 * 이다.
 */
@RequiredArgsConstructor
public abstract class QueryDslJpaQueryMaker<T> {
	protected final JPAQueryFactory jpaQueryFactory;

	public JPAQuery<T> getQuery(EntityPathBase<T> entityPathBase) {
		return jpaQueryFactory.selectFrom(entityPathBase);
	}
}
