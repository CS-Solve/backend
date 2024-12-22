package com.comssa.persistence.question.repository;


import com.comssa.persistence.question.domain.common.QuestionCategory;
import com.comssa.persistence.question.domain.major.MajorMultipleChoiceQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MajorMultipleChoiceQuestionJpaRepository extends JpaRepository<MajorMultipleChoiceQuestion, Long> {

	@Query("SELECT DISTINCT nq FROM MajorMultipleChoiceQuestion nq "
		+ "LEFT JOIN FETCH nq.questionChoices "
		+ "ORDER BY nq.ifApproved")
	List<MajorMultipleChoiceQuestion> findFetchChoicesSortedByIfApproved();


	@Query("SELECT DISTINCT nq FROM MajorMultipleChoiceQuestion nq "
		+ "LEFT JOIN FETCH nq.questionChoices "
		+ "WHERE nq.questionCategory IN :questionCategories ")
	List<MajorMultipleChoiceQuestion> findAllByQuestionCategoriesFetchChoices(
		@Param("questionCategories") List<QuestionCategory> questionCategories
	);
}
