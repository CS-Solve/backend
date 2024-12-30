package com.comssa.persistence.question.repository.querydsl.impl;


import com.comssa.persistence.question.domain.common.QQuestion;
import com.comssa.persistence.question.domain.common.QuestionCategory;
import com.comssa.persistence.question.domain.license.LicenseMultipleChoiceQuestion;
import com.comssa.persistence.question.domain.license.QLicenseMultipleChoiceQuestion;
import com.comssa.persistence.question.repository.querydsl.ExternalQuestionFilter;
import com.comssa.persistence.question.repository.querydsl.QueryDslJpaQueryMaker;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LicenseMultipleChoiceQuestionDslRepository
	extends QueryDslJpaQueryMaker<LicenseMultipleChoiceQuestion>
	implements ExternalQuestionFilter<LicenseMultipleChoiceQuestion> {

	public LicenseMultipleChoiceQuestionDslRepository(JPAQueryFactory jpaQueryFactory) {
		super(jpaQueryFactory);
	}

	private final QLicenseMultipleChoiceQuestion question = QLicenseMultipleChoiceQuestion
		.licenseMultipleChoiceQuestion;

	public List<LicenseMultipleChoiceQuestion> findAllWhereLicenseSessionId(
		Long licenseId
	) {
		return getQuery(question)
			.where(
				question.licenseSession.id.eq(licenseId)
			)
			.distinct()
			.leftJoin(question.questionChoices).fetchJoin()
			.orderBy(question.ifApproved.asc())
			.fetch();
	}

	public List<LicenseMultipleChoiceQuestion> findAllWhereLicenseSessionIdAndIfApproved(
		Long licenseId,
		boolean ifApproved
	) {
		return getQuery(question)
			.where(
				question.licenseSession.id.eq(licenseId)
					.and(question.ifApproved.eq(ifApproved))
			)
			.distinct()
			.leftJoin(question.questionChoices).fetchJoin()
			.fetch();
	}

	@Override
	public List<LicenseMultipleChoiceQuestion> findAllWhereCategories(
		List<QuestionCategory> questionCategories
	) {
		return selectWhereCategories(questionCategories)
			.leftJoin(question.questionChoices).fetchJoin()
			.fetch();
	}


	@Override
	public QQuestion getQuestionQClass() {
		return question._super;
	}

	@Override
	public JPAQuery<LicenseMultipleChoiceQuestion> getQuestion() {
		return getQuery(question);
	}
}
