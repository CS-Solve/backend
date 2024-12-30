package com.comssa.persistence.question.repository.querydsl.impl;

import com.comssa.persistence.question.domain.common.QQuestion;
import com.comssa.persistence.question.domain.common.QuestionCategory;
import com.comssa.persistence.question.domain.common.QuestionLevel;
import com.comssa.persistence.question.domain.major.MajorDescriptiveQuestion;
import com.comssa.persistence.question.domain.major.QMajorDescriptiveQuestion;
import com.comssa.persistence.question.repository.querydsl.MajorQuestionSearchFilter;
import com.comssa.persistence.question.repository.querydsl.QueryDslJpaQueryMaker;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MajorDescriptiveDslRepository
	extends QueryDslJpaQueryMaker<MajorDescriptiveQuestion>
	implements MajorQuestionSearchFilter<MajorDescriptiveQuestion> {
	private final QMajorDescriptiveQuestion question = QMajorDescriptiveQuestion.majorDescriptiveQuestion;

	public MajorDescriptiveDslRepository(JPAQueryFactory jpaQueryFactory) {
		super(jpaQueryFactory);
	}


	@Override
	public List<MajorDescriptiveQuestion> findAllCategoriesAndLevelsAndIfApproved(
		List<QuestionCategory> questionCategories,
		List<QuestionLevel> questionLevels,
		boolean approved) {
		return selectWhereCategoriesAndLevelsAndIfApproved(questionCategories, questionLevels, approved)
			.fetch();
	}

	@Override
	public JPAQuery<MajorDescriptiveQuestion> getQuestion() {
		return null;
	}

	@Override
	public QQuestion getQuestionQClass() {
		return null;
	}

	public List<MajorDescriptiveQuestion> findAllOrderByIfApprovedAsc() {
		return getQuery(question)
			// 정렬 기준
			.orderBy(question.ifApproved.asc())
			.fetch();
	}
}
