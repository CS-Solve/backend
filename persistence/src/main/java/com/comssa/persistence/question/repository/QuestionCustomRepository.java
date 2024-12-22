package com.comssa.persistence.question.repository;


import com.comssa.persistence.question.domain.common.Question;
import com.comssa.persistence.question.domain.common.QuestionCategory;
import com.comssa.persistence.question.domain.common.QuestionLevel;

import java.util.List;
import java.util.Optional;

public interface QuestionCustomRepository<T extends Question> {
	Optional<T> findByIdFetchCommentsOrderByCreatedAtDesc(Long questionId);

	List<T> findWithCategoriesAndLevelsAndIfApproved(
		List<QuestionCategory> questionCategories,
		List<QuestionLevel> questionLevels,
		boolean approved
	);
}
