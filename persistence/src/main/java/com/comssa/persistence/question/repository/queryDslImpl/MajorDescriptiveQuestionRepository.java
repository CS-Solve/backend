package com.comssa.persistence.question.repository.queryDslImpl;

import com.comssa.persistence.question.domain.common.QuestionCategory;
import com.comssa.persistence.question.domain.common.QuestionLevel;
import com.comssa.persistence.question.domain.major.MajorDescriptiveQuestion;
import com.comssa.persistence.question.domain.major.QMajorDescriptiveQuestion;
import com.comssa.persistence.question.repository.QuestionQueryDslMaker;
import com.comssa.persistence.question.repository.booleanBuilder.LevelsAndCategoryBooleanBuilder;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MajorDescriptiveQuestionRepository
	extends QuestionQueryDslMaker<MajorDescriptiveQuestion>
	implements LevelsAndCategoryBooleanBuilder {
	private final QMajorDescriptiveQuestion q = QMajorDescriptiveQuestion.majorDescriptiveQuestion;

	public MajorDescriptiveQuestionRepository(JPAQueryFactory jpaQueryFactory) {
		super(jpaQueryFactory);
	}

	public List<MajorDescriptiveQuestion> findWithCategoriesAndLevelsAndIfApproved(
		List<QuestionCategory> questionCategories,
		List<QuestionLevel> questionLevels,
		boolean approved) {

		return getQuery(q, withCategoriesAndLevels(
			q._super,
			questionCategories,
			questionLevels,
			approved
		))
			.fetch();
	}

	public List<MajorDescriptiveQuestion> findAllSortedByIfApproved() {
		return getQuery(q, new BooleanBuilder())
			// 정렬 기준
			.orderBy(q.ifApproved.asc())
			.fetch();
	}
}
