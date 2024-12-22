package com.comssa.persistence.question.repository.queryDslImpl;


import com.comssa.persistence.question.domain.major.MajorMultipleChoiceQuestion;
import com.comssa.persistence.question.domain.major.QMajorDescriptiveQuestion;
import com.comssa.persistence.question.repository.QuestionQueryDslMaker;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Service;

@Service
public class MajorMultipleChoiceQuestionRepository extends QuestionQueryDslMaker<MajorMultipleChoiceQuestion> {
	private final QMajorDescriptiveQuestion q = QMajorDescriptiveQuestion.majorDescriptiveQuestion;

	public MajorMultipleChoiceQuestionRepository(JPAQueryFactory jpaQueryFactory) {
		super(jpaQueryFactory);
	}


}
