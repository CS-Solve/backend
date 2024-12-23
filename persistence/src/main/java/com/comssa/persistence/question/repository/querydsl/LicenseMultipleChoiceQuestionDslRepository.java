package com.comssa.persistence.question.repository.querydsl;


import com.comssa.persistence.question.domain.common.QuestionCategory;
import com.comssa.persistence.question.domain.license.LicenseMultipleChoiceQuestion;
import com.comssa.persistence.question.domain.license.QLicenseMultipleChoiceQuestion;
import com.comssa.persistence.question.repository.querydsl.booleanBuilder.LevelsAndCategoryBooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LicenseMultipleChoiceQuestionDslRepository
	extends QueryDslJpaQueryMaker<LicenseMultipleChoiceQuestion>
	implements LevelsAndCategoryBooleanBuilder {

	public LicenseMultipleChoiceQuestionDslRepository(JPAQueryFactory jpaQueryFactory) {
		super(jpaQueryFactory);
	}

	private final QLicenseMultipleChoiceQuestion question = QLicenseMultipleChoiceQuestion.licenseMultipleChoiceQuestion;

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

	public List<LicenseMultipleChoiceQuestion> findAllWhereQuestionCategories(
		List<QuestionCategory> questionCategories
	) {
		return getQuery(
			question,
			whereCategoriesAndLevels(question._super, questionCategories, null))
			.distinct()
			.leftJoin(question.questionChoices).fetchJoin()
			.fetch();
	}
}
