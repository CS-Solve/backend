package com.comssa.persistence.question.repository;


import com.comssa.persistence.question.domain.common.Question;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public abstract class QuestionQueryDslMaker<T extends Question> {
	protected final JPAQueryFactory jpaQueryFactory;


	public JPAQuery<T> getQuery(EntityPathBase<T> entityPathBase,
								Predicate condition) {
		return jpaQueryFactory.selectFrom(entityPathBase)
			.where(condition);
	}

	public JPAQuery<T> getQuery(EntityPathBase<T> entityPathBase) {
		return jpaQueryFactory.selectFrom(entityPathBase);
	}
}
