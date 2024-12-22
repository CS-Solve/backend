package com.comssa.persistence.question.repository.booleanBuilder;

import com.comssa.persistence.question.domain.common.QQuestion;
import com.comssa.persistence.question.domain.common.QuestionCategory;
import com.comssa.persistence.question.domain.common.QuestionLevel;
import com.querydsl.core.BooleanBuilder;

import java.util.List;


public interface LevelsAndCategoryBooleanBuilder {
	default BooleanBuilder withCategoriesAndLevels(
		QQuestion question,
		List<QuestionCategory> questionCategories,
		List<QuestionLevel> questionLevels,
		boolean approved
	) {
		BooleanBuilder condition = new BooleanBuilder();
		// 카테고리 조건
		if (questionCategories != null && !questionCategories.isEmpty()) {
			condition.and(question.questionCategory.in(questionCategories));
		}
		//  레벨 조건
		if (questionLevels != null && !questionLevels.isEmpty()) {
			condition.and(question.questionLevel.in(questionLevels));
		}
		// 승인 여부 조건
		condition.and(question.ifApproved.eq(approved));

		return condition;
	}
}
