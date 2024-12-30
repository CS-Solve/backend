package com.comssa.persistence.question.repository.querydsl;

import com.comssa.persistence.question.domain.common.Question;
import com.comssa.persistence.question.domain.common.QuestionCategory;
import com.comssa.persistence.question.domain.common.QuestionLevel;
import com.querydsl.jpa.impl.JPAQuery;

import java.util.List;

public interface MajorQuestionSearchFilter<T extends Question>
	extends LevelsAndCategoryBooleanBuilder, CommonQuestionFilter<T> {

	List<T> findAllCategoriesAndLevelsAndIfApproved(
		List<QuestionCategory> questionCategories,
		List<QuestionLevel> questionLevels,
		boolean approved
	);


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
