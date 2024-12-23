package com.comssa.persistence.question.repository.querydsl;

import com.comssa.persistence.question.domain.common.QuestionCategory;
import com.comssa.persistence.question.domain.common.QuestionLevel;
import com.comssa.persistence.question.domain.major.MajorDescriptiveQuestion;
import com.comssa.persistence.question.domain.major.QMajorDescriptiveQuestion;
import com.comssa.persistence.question.repository.querydsl.booleanbuilder.LevelsAndCategoryBooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MajorDescriptiveDslRepository
	extends QueryDslJpaQueryMaker<MajorDescriptiveQuestion>
	implements LevelsAndCategoryBooleanBuilder {
	private final QMajorDescriptiveQuestion question = QMajorDescriptiveQuestion.majorDescriptiveQuestion;

	public MajorDescriptiveDslRepository(JPAQueryFactory jpaQueryFactory) {
		super(jpaQueryFactory);
	}

	public List<MajorDescriptiveQuestion> findWithCategoriesAndLevelsAndIfApproved(
		List<QuestionCategory> questionCategories,
		List<QuestionLevel> questionLevels,
		boolean approved) {

		return getQuery(question, whereCategoriesAndLevels(
			question._super,
			questionCategories,
			questionLevels)
			.and(question.ifApproved.eq(approved)))
			.fetch();
	}

	public List<MajorDescriptiveQuestion> findAllOrderByIfApprovedAsc() {
		return getQuery(question)
			// 정렬 기준
			.orderBy(question.ifApproved.asc())
			.fetch();
	}

}
