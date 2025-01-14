package com.comssa.persistence.question.repository.querydsl.impl;


import com.comssa.persistence.question.domain.common.QQuestion;
import com.comssa.persistence.question.domain.common.QuestionCategory;
import com.comssa.persistence.question.domain.common.QuestionLevel;
import com.comssa.persistence.question.domain.major.MajorMultipleChoiceQuestion;
import com.comssa.persistence.question.domain.major.QMajorMultipleChoiceQuestion;
import com.comssa.persistence.question.repository.querydsl.QueryDslJpaQueryMaker;
import com.comssa.persistence.question.repository.querydsl.query.MajorQuestionSearchQuery;
import com.comssa.persistence.question.repository.querydsl.query.QuestionApiTransmissionQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MajorMultipleChoiceQuestionDslRepository
        extends QueryDslJpaQueryMaker<MajorMultipleChoiceQuestion>
        implements QuestionApiTransmissionQuery<MajorMultipleChoiceQuestion>,
        MajorQuestionSearchQuery<MajorMultipleChoiceQuestion> {

    private final QMajorMultipleChoiceQuestion question = QMajorMultipleChoiceQuestion.majorMultipleChoiceQuestion;

    public MajorMultipleChoiceQuestionDslRepository(JPAQueryFactory jpaQueryFactory) {
        super(jpaQueryFactory);
    }

    @Override
    public List<MajorMultipleChoiceQuestion> findAllCategoriesAndLevelsAndIfApproved(
            List<QuestionCategory> questionCategories,
            List<QuestionLevel> questionLevels,
            boolean approved) {
        return selectWhereCategoriesAndLevelsAndIfApproved(
                questionCategories,
                questionLevels,
                approved
        )
                .distinct()
                .leftJoin(question.questionChoices).fetchJoin()
                .fetch();
    }

    @Override
    public List<MajorMultipleChoiceQuestion> findAllWhereCategories(
            List<QuestionCategory> questionCategories
    ) {
        return selectWhereCategories(questionCategories)
                .distinct()
                .leftJoin(question.questionChoices).fetchJoin()
                .fetch();
    }

    @Override
    public JPAQuery<MajorMultipleChoiceQuestion> getQuestion() {
        return getQuery(question);
    }

    @Override
    public QQuestion getQuestionQClass() {
        return question._super;
    }

    @Override
    public List<MajorMultipleChoiceQuestion> findAllOrderByIfApprovedAsc() {
        return selectOrderByIfApprovedAsc()
                .distinct()
                .leftJoin(question.questionChoices).fetchJoin()
                .fetch();
    }


}
