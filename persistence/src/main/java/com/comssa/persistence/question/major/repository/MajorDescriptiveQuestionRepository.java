package com.comssa.persistence.question.major.repository;

import com.comssa.persistence.question.common.domain.QuestionCategory;
import com.comssa.persistence.question.common.domain.QuestionLevel;
import com.comssa.persistence.question.major.domain.common.MajorDescriptiveQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MajorDescriptiveQuestionRepository extends JpaRepository<MajorDescriptiveQuestion, Long> {
	/**
	 * 허용된 문제들만 조회 (ifApproved가 true인 경우)
	 */
	@Query("SELECT DISTINCT md FROM MajorDescriptiveQuestion md "
		+ "WHERE md.questionCategory IN :questionCategories "
		+ "AND md.questionLevel IN :questionLevels "
		+ "AND md.ifApproved = true")
	List<MajorDescriptiveQuestion> findWithCategoriesAndLevelsAndIfApproved(
		@Param("questionCategories") List<QuestionCategory> questionCategories,
		@Param("questionLevels") List<QuestionLevel> questionLevels);

	@Query("SELECT DISTINCT md FROM MajorDescriptiveQuestion md "
		+ "ORDER BY md.ifApproved")
	List<MajorDescriptiveQuestion> findAllSortedByIfApproved();
}
