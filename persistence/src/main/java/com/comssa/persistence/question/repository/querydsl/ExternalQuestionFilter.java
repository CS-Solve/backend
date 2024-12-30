package com.comssa.persistence.question.repository.querydsl;

import com.comssa.persistence.question.domain.common.Question;
import com.comssa.persistence.question.domain.common.QuestionCategory;
import com.querydsl.jpa.impl.JPAQuery;

import java.util.List;

public interface ExternalQuestionFilter<T extends Question>
	extends LevelsAndCategoryBooleanBuilder, CommonQuestionFilter<T> {
	// 구현체에서 구현해야하는 통일된 메소드 이름
	List<T> findAllWhereCategories(List<QuestionCategory> questionCategory);

	default JPAQuery<T> selectWhereCategories(List<QuestionCategory> questionCategories) {
		return getQuestion()
			.where(
				whereCategoriesAndLevels(
					getQuestionQClass(),
					questionCategories,
					null)
			);
	}

}
