package com.comssa.persistence.question.repository.queryDslImpl;


import com.comssa.persistence.comment.domain.QComment;
import com.comssa.persistence.question.domain.common.QQuestion;
import com.comssa.persistence.question.domain.common.Question;
import com.comssa.persistence.question.repository.QuestionCustomRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class QuestionCustomRepositoryImpl implements QuestionCustomRepository {
	private final JPAQueryFactory queryFactory;

	@Override
	public Optional<Question> findByIdFetchCommentsOrderByCreatedAtDesc(Long questionId) {
		QQuestion question = QQuestion.question;
		QComment comment = QComment.comment;

		Question result = queryFactory.selectFrom(question)
			//FetchJoin으로 questions.comment를 가져온 후에 comment에 매핑한다
			//"LEFT JOIN FETCH question.comments comment "
			.leftJoin(question.comments, comment).fetchJoin()
			.where(question.id.eq(questionId))
			.orderBy(comment.createdAt.desc())
			.fetchOne(); //하나의 엔티티만 가져옴
		return Optional.ofNullable(result);
	}
}
