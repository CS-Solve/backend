package com.comssa.persistence.question.repository.queryDslImpl;

import com.comssa.persistence.question.domain.common.QuestionCategory;
import com.comssa.persistence.question.domain.common.QuestionLevel;
import com.comssa.persistence.question.domain.major.MajorDescriptiveQuestion;
import com.comssa.persistence.question.domain.major.QMajorDescriptiveQuestion;
import com.comssa.persistence.question.repository.QuestionQueryDslMaker;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MajorDescriptiveQuestionQueryDslMaker extends QuestionQueryDslMaker<MajorDescriptiveQuestion> {
	private final QMajorDescriptiveQuestion q = QMajorDescriptiveQuestion.majorDescriptiveQuestion;

	public MajorDescriptiveQuestionQueryDslMaker(JPAQueryFactory jpaQueryFactory) {
		super(jpaQueryFactory);
	}

	public List<MajorDescriptiveQuestion> findWithCategoriesAndLevelsAndIfApproved(
		List<QuestionCategory> questionCategories,
		List<QuestionLevel> questionLevels,
		boolean approved) {


		// 2) BooleanBuilder를 통한 동적 조건 생성
		BooleanBuilder condition = new BooleanBuilder();

		// (2-1) 카테고리 조건
		if (questionCategories != null && !questionCategories.isEmpty()) {
			condition.and(q.questionCategory.in(questionCategories));
		}
		// (2-2) 레벨 조건
		if (questionLevels != null && !questionLevels.isEmpty()) {
			condition.and(q.questionLevel.in(questionLevels));
		}
		// (2-3) 승인 여부 조건
		condition.and(q.ifApproved.eq(approved));

		return getQuery(q, condition)
			.fetch();
	}

	public List<MajorDescriptiveQuestion> findAllSortedByIfApproved() {
		return getQuery(q, new BooleanBuilder())
			// 정렬 기준
			.orderBy(q.ifApproved.asc())
			.fetch();
	}
}
