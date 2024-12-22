package com.comssa.persistence.question.repository.queryDslImpl;


import com.comssa.persistence.comment.domain.QComment;
import com.comssa.persistence.question.domain.common.QQuestion;
import com.comssa.persistence.question.domain.common.Question;
import com.comssa.persistence.question.domain.common.QuestionCategory;
import com.comssa.persistence.question.domain.common.QuestionLevel;
import com.comssa.persistence.question.repository.QuestionCustomRepository;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.List;
import java.util.Optional;


public class QuestionCustomRepositoryImpl<T extends Question> implements QuestionCustomRepository<T> {
	private final JPAQueryFactory queryFactory;
	private PathBuilder<T> questionPath;
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
	public Optional<T> findByIdFetchCommentsOrderByCreatedAtDesc(Long questionId) {
		QQuestion question = QQuestion.question;
		Question result = queryFactory
			.selectFrom(question)
			.leftJoin(question.comments, comment).fetchJoin() // 동적 경로 사용
			.where(question.id.eq(questionId)) // 동적 경로 사용
			.orderBy(comment.createdAt.desc())
			.fetchOne();
//		T result = queryFactory.selectFrom(question)
//			.leftJoin(questionPath.get("comments")).fetchJoin() // 동적 경로 사용
//			.where(questionPath.get("id").eq(questionId)) // 동적 경로 사용
//			.orderBy(comment.createdAt.desc())
//			.fetchOne();
		return Optional.ofNullable((T) result);
	}

//	@Override
//	public List<T> findWithCategoriesAndLevelsAndIfApproved(
//		List<QuestionCategory> questionCategories,
//		List<QuestionLevel> questionLevels,
//		boolean approved) {
//		return List.of();
//	}

	@Override
	public List<T> findWithCategoriesAndLevelsAndIfApproved(
		List<QuestionCategory> questionCategories,
		List<QuestionLevel> questionLevels,
		boolean approved) {
		QQuestion question = QQuestion.question;


		return (List<T>) queryFactory.selectFrom(question)
			.where(
				question.questionCategory.in(questionCategories)
					.and(question.questionLevel.in(questionLevels)
						.and(question.ifApproved.eq(approved))
					)
			)
			.orderBy(question.ifApproved.asc())
			.fetch();
	}
}
