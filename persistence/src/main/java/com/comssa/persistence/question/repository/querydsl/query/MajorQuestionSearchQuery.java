package com.comssa.persistence.question.repository.querydsl.query;

import com.comssa.persistence.question.domain.common.Question;
import com.comssa.persistence.question.domain.common.QuestionCategory;
import com.comssa.persistence.question.domain.common.QuestionLevel;
import com.comssa.persistence.question.repository.querydsl.query.booleanbuilder.LevelsAndCategoryBooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;

import java.util.List;

public interface MajorQuestionSearchQuery<T extends Question>
	extends LevelsAndCategoryBooleanBuilder, QuestionQuerySupport<T> {

	default List<T> findAllCategoriesAndLevelsAndIfApproved(
		List<QuestionCategory> questionCategories,
		List<QuestionLevel> questionLevels,
		boolean approved
	) {
		return selectWhereCategoriesAndLevelsAndIfApproved(questionCategories, questionLevels, approved)
			.fetch();
	}

	default List<T> findAllOrderByIfApprovedAsc() {
		return selectOrderByIfApprovedAsc()
			.fetch();
	}

	default JPAQuery<T> selectOrderByIfApprovedAsc() {
		return getQuestion()
			.orderBy(getQuestionQClass().ifApproved.asc());
	}

	default JPAQuery<T> selectWhereCategoriesAndLevelsAndIfApproved(
		List<QuestionCategory> questionCategories,
		List<QuestionLevel> questionLevels,
		boolean approved) {
		return getQuestion()
			.where(
				whereCategoriesAndLevels(
					getQuestionQClass(),
					questionCategories,
					questionLevels)
					.and(getQuestionQClass().ifApproved.eq(approved))
			);
	}
}
