package com.comssa.persistence.question.repository.querydsl;

import com.comssa.persistence.question.domain.common.QQuestion;
import com.comssa.persistence.question.domain.common.QuestionCategory;
import com.comssa.persistence.question.domain.common.QuestionLevel;
import com.querydsl.core.BooleanBuilder;

import java.util.List;


public interface LevelsAndCategoryBooleanBuilder {
	/**
	 * @param question 호출부에선 QQuestion 그대로 삽입하거나
	 *                 실제 상속받는 관계일 떄는 Q*Question._super를 인자로 넣어 호출한다
	 */
	default BooleanBuilder whereCategoriesAndLevels(
		QQuestion question,
		List<QuestionCategory> questionCategories,
		List<QuestionLevel> questionLevels
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

		return condition;
	}
}
