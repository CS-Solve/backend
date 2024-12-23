package com.comssa.persistence.question.repository.querydsl;


import com.comssa.persistence.question.domain.common.QuestionCategory;
import com.comssa.persistence.question.domain.common.QuestionLevel;
import com.comssa.persistence.question.domain.major.MajorMultipleChoiceQuestion;
import com.comssa.persistence.question.domain.major.QMajorMultipleChoiceQuestion;
import com.comssa.persistence.question.repository.querydsl.booleanBuilder.LevelsAndCategoryBooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MajorMultipleChoiceQuestionRepository
	extends QueryDslJpaQueryMaker<MajorMultipleChoiceQuestion>
	implements LevelsAndCategoryBooleanBuilder {
	private final QMajorMultipleChoiceQuestion question = QMajorMultipleChoiceQuestion.majorMultipleChoiceQuestion;

	public MajorMultipleChoiceQuestionRepository(JPAQueryFactory jpaQueryFactory) {
		super(jpaQueryFactory);
	}

	public List<MajorMultipleChoiceQuestion> findAllWhereCategoriesAndLevelsAndIfApproved(
		List<QuestionCategory> questionCategories,
		List<QuestionLevel> questionLevels,
		boolean approved
	) {

		return getQuery(question, whereCategoriesAndLevels(
			question._super,
			questionCategories,
			questionLevels
		).and(question.ifApproved.eq(approved)))
			.distinct()
			.leftJoin(question.questionChoices).fetchJoin()
			.fetch();
	}

	public List<MajorMultipleChoiceQuestion> findAllWhereQuestionCategories(
		List<QuestionCategory> questionCategories
	) {
		return getQuery(
			question,
			whereCategoriesAndLevels(question._super, questionCategories, null))
			.distinct()
			.leftJoin(question.questionChoices).fetchJoin()
			.fetch();
	}

	public List<MajorMultipleChoiceQuestion> findAllOrderByIfApprovedAsc() {
		return getQuery(question)
			.distinct()
			.leftJoin(question.questionChoices).fetchJoin()
			.orderBy(question.ifApproved.asc())
			.fetch();
	}


}
