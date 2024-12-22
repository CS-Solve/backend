package com.comssa.persistence.question.repository.queryDslImpl;


import com.comssa.persistence.question.domain.common.QuestionCategory;
import com.comssa.persistence.question.domain.common.QuestionLevel;
import com.comssa.persistence.question.domain.major.MajorMultipleChoiceQuestion;
import com.comssa.persistence.question.domain.major.QMajorMultipleChoiceQuestion;
import com.comssa.persistence.question.repository.QuestionQueryDslMaker;
import com.comssa.persistence.question.repository.booleanBuilder.LevelsAndCategoryBooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MajorMultipleChoiceQuestionRepository
	extends QuestionQueryDslMaker<MajorMultipleChoiceQuestion>
	implements LevelsAndCategoryBooleanBuilder {
	private final QMajorMultipleChoiceQuestion question = QMajorMultipleChoiceQuestion.majorMultipleChoiceQuestion;

	public MajorMultipleChoiceQuestionRepository(JPAQueryFactory jpaQueryFactory) {
		super(jpaQueryFactory);
	}

	public List<MajorMultipleChoiceQuestion> findAllWithCategoriesAndLevelsAndIfApproved(
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

	public List<MajorMultipleChoiceQuestion> findAllOrderByIfApprovedAsc() {
		return getQuery(question)
			.orderBy(question.ifApproved.asc())
			.fetch();

	}
}
