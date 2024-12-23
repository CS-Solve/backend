package com.comssa.persistence.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
@EnableJpaAuditing
@EntityScan(basePackages = "com.comssa.persistence")
@EnableJpaRepositories(basePackages = "com.comssa.persistence")
public class JpaConfig {

	@PersistenceContext
	private EntityManager entityManager;

	@Bean
	public JPAQueryFactory jpaQueryFactory() {
		return new JPAQueryFactory(entityManager);
	}


//	@Bean
//	public QuestionCustomRepositoryImpl<MajorDescriptiveQuestion> majorDescriptiveQuestionCustomRepository(
//		JPAQueryFactory queryFactory) {
//		return new QuestionCustomRepositoryImpl<>(queryFactory, majorDescriptiveChoiceEntityPath());
//	}
//
//	@Bean
//	public QuestionCustomRepositoryImpl<MajorMultipleChoiceQuestion> majorMultipleChoiceQuestionCustomRepository(
//		JPAQueryFactory queryFactory) {
//		return new QuestionCustomRepositoryImpl<>(queryFactory, majorMultipleChoiceEntityPath());
//	}
//
//	@Bean
//	public QuestionCustomRepositoryImpl<LicenseMultipleChoiceQuestion> licenseMultipleChoiceQuestionCustomRepository(
//		JPAQueryFactory queryFactory) {
//		return new QuestionCustomRepositoryImpl<>(queryFactory, licenseMultipleChoiceEntityPath());
//	}
//
//
//	@Bean
//	public EntityPathBase<MajorDescriptiveQuestion> majorDescriptiveChoiceEntityPath() {
//		return QMajorDescriptiveQuestion.majorDescriptiveQuestion;
//	}
//
//	@Bean
//	public EntityPathBase<MajorMultipleChoiceQuestion> majorMultipleChoiceEntityPath() {
//		return QMajorMultipleChoiceQuestion.majorMultipleChoiceQuestion;
//	}
//
//	@Bean
//	public EntityPathBase<LicenseMultipleChoiceQuestion> licenseMultipleChoiceEntityPath() {
//		return QLicenseMultipleChoiceQuestion.licenseMultipleChoiceQuestion;
//	}


}

