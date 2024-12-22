package com.comssa.persistence.question.repository.querydsl;


import com.comssa.persistence.comment.domain.QComment;
import com.comssa.persistence.question.domain.common.QQuestion;
import com.comssa.persistence.question.domain.common.Question;
import com.comssa.persistence.question.repository.jpa.QuestionCustomRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.Optional;


public class QuestionCustomRepositoryImpl implements QuestionCustomRepository<Question> {
	private final JPAQueryFactory queryFactory;
	private final QComment comment;

	public QuestionCustomRepositoryImpl(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;

		comment = QComment.comment;
	}

	/*
	댓글을 위해 문제를 가져올 땐
	생성 순서에 맞게 가져온다.
	 */
	@Override
	public Optional<Question> findByIdFetchCommentsOrderByCreatedAtDesc(Long questionId) {
		QQuestion question = QQuestion.question;
		Question result = queryFactory
			.selectFrom(question)
			.leftJoin(question.comments, comment).fetchJoin() // 동적 경로 사용
			.where(question.id.eq(questionId)) // 동적 경로 사용
			.orderBy(comment.createdAt.desc())
			.fetchOne();

		return Optional.ofNullable(result);
	}
}
