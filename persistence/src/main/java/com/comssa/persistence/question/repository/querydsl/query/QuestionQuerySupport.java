package com.comssa.persistence.question.repository.querydsl.query;

import com.comssa.persistence.question.domain.common.QQuestion;
import com.comssa.persistence.question.domain.common.Question;
import com.querydsl.jpa.impl.JPAQuery;

public interface QuestionQuerySupport<T extends Question> {
	//구현체에서 Select할 테이블을 따로 구현
	JPAQuery<T> getQuestion();

	//구현체에서 QQuestion을 반환
	QQuestion getQuestionQClass();
}
